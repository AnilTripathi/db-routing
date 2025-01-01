package com.db.routing.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeExample {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String formattedDate = sdf.format(new Date());
        System.out.println(formattedDate);
    }
}
