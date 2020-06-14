package cn.zeroce.design.chengwen.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtil {

    /**
     * LocalDateToDate
     * @param localDate
     * @return
     */
    public static Date LocalDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zonedDateTime.toInstant());
        return date;
    }

    /**
     * DateToLocalDate
     * @param date
     * @return
     */
    public static LocalDate DateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    /**
     * DateFormattoDBdate
     * @param date
     * @return
     */
    public static Date DateFormattoDBdate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.format(date);
        return date;
    }
}
