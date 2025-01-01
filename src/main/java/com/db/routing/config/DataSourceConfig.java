package com.db.routing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableJpaRepositories(basePackages = "com.db.routing.*")
@EnableTransactionManagement
public class DataSourceConfig {

   
    @Bean(name = "readWriteDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource readWriteDataSource() {
        String connectionUrl=System.getenv("spring.datasource.url");
        log.info("Creating read-write datasource connectionUrl={}",connectionUrl);
        return connectionPoolDataSource(DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url(connectionUrl)
                .username(System.getenv("username"))
                .password(System.getenv("password"))
                .build());
    }

    @Bean(name = "readOnlyDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource readOnlyDataSource() {
        String connectionUrl=System.getenv("spring.datasource.readonly.url");
        log.info("Creating read-only connectionUrl={}",connectionUrl);
        return connectionPoolDataSource(DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url(connectionUrl)
                .username(System.getenv("username"))
                .password(System.getenv("password"))
                .build());
    }

    @Bean(name = "routeDataSource")
    public DataSource routeDataSource(
            @Qualifier("readWriteDataSource") DataSource readWriteDataSource,
            @Qualifier("readOnlyDataSource") DataSource readOnlyDataSource) {
        TransactionRoutingDataSource routingDatasource = new TransactionRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.MASTER, readWriteDataSource());
        dataSourceMap.put(DataSourceType.READ_REPLICA, readOnlyDataSource());
        routingDatasource.setTargetDataSources(dataSourceMap);
        routingDatasource.setDefaultTargetDataSource(dataSourceMap.get(DataSourceType.MASTER));
        return routingDatasource;
    }

    @Bean(name = "lazyDataSource")
    @DependsOn({"readWriteDataSource", "readOnlyDataSource", "routeDataSource"})
    public DataSource lazyDataSource(@Qualifier("routeDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("lazyDataSource") DataSource lazyDataSource) throws SQLException {
        Properties properties = new Properties();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.connection.provider_disables_autocommit", true);
        em.setDataSource(lazyDataSource);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPackagesToScan("com.db.routing.*");
        em.setJpaProperties(properties);
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory")EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private HikariConfig hikariConfig(DataSource dataSource) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDataSource(dataSource);
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("useServerPrepStmts", "true");
		hikariConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
		hikariConfig.addDataSourceProperty("cacheResultSetMetadata", "false");
		hikariConfig.addDataSourceProperty("cacheServerConfiguration", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariConfig.setMinimumIdle(2);
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setIdleTimeout(30000);
		hikariConfig.setMaxLifetime(60000);
		hikariConfig.setConnectionTimeout(30000);
		hikariConfig.setTransactionIsolation("TRANSACTION_READ_UNCOMMITTED");

        hikariConfig.setAutoCommit(false);
        return hikariConfig;
    }

    private HikariDataSource connectionPoolDataSource(DataSource dataSource) {
        return new HikariDataSource(hikariConfig(dataSource));
    }
}
