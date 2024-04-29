package api.usuarios.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private static final String PATTERN_DATE = "yyyy-MM-dd hh:mm:ss";

//    public static String formatDate(Date date) {
//        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
//        return sdf.format(date);
//    }

    public static String getZoneTimeWithClock() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX'['VV']'");
        return zonedDateTime.format(formatter);
    }
}
