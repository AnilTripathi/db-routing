package com.db.routing.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        log.info("convertToDatabaseColumn zonedDateTime={}", zonedDateTime);
        return zonedDateTime == null ? null : Timestamp.valueOf(zonedDateTime.toLocalDateTime());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        log.info("convertToEntityAttribute zonedDateTime={}", sqlTimestamp);
        return sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime().atZone(ZoneId.systemDefault());
    }
}

