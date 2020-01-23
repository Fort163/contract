package test.contract.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DateHelpler {

    private static final DateTimeFormatter formatterExt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatterExtTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");


    public static LocalDate formatDateExt(String dateStr){
        if(dateStr==null||dateStr.trim().equals("")){
            return null;
        }
        return LocalDate.parse(dateStr,formatterExt);
    }

    public static LocalDateTime formatDateTimeExt(String dateStr){
        if(dateStr==null||dateStr.trim().equals("")){
            return null;
        }
        else {
            return LocalDateTime.parse(dateStr, formatterExtTime);
        }
    }

    public static String localDateTimeToString(LocalDateTime localDate){
        if(localDate!=null){
            return localDate.format(formatterExtTime);
        }
        return null;
    }

    public static String localDateToString(LocalDate localDate){
        if(localDate!=null){
            return localDate.format(formatterExt);
        }
        return null;
    }

}
