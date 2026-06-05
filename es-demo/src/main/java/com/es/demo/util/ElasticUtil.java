package com.es.demo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ElasticUtil {
    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("MM_yyyy");

    public static String getRollingIndex(String index, Date createdOn) {
        YearMonth yearMonth = YearMonth.from(createdOn.toInstant().atZone(ZoneId.of("Asia/Kolkata")));
        return index + "_" + yearMonth.format(MONTH_FORMAT);
    }
}
