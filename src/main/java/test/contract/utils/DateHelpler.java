package test.contract.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DateHelpler {

    private static final DateTimeFormatter formatterExt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatterSqlTimestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter formatterExtTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static LocalDate getPreviousMonth(LocalDate localDate){
        localDate = localDate.withDayOfMonth(1);
        localDate = localDate.minus(1, ChronoUnit.MONTHS);
        return localDate.withDayOfMonth(localDate.lengthOfMonth());
    }

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

    public static String localDateToString(LocalDate localDate){
        return localDateToString(localDate,true);
    }

    public static String localDateTimeToString(LocalDateTime localDate){
        if(localDate!=null){
            return localDate.format(formatterExtTime);
        }
        return null;
    }

    public static String localDateToString(LocalDate localDate,Boolean isFirstDay){
        if(localDate!=null){
            if(isFirstDay) {
                return localDate.format(formatterExt);
            }
            else{
                return localDate.getMonth().maxLength()+(localDate.format(formatterExt).substring(2));
            }
        }
        return null;
    }

    public static String localDateToStringFirstAndLastDay(LocalDate localDate,Boolean isFirstDay){
        String date = "";
        if(isFirstDay){
            date="01 "+getMountString(localDate.getMonth().getValue(),true)+" "+localDate.getYear();
        }
        else {
            date=localDate.getMonth().maxLength()+" "+getMountString(localDate.getMonth().getValue(),true)+" "+localDate.getYear();
        }
        return date;
    }

    public static String localDateToStringMonthAndYear(LocalDate localDate){
        String date = "";
        if(localDate!=null){
            date+=getMountString(localDate.getMonth().getValue(),false)+" "+localDate.getYear();
        }
        return date;
    }

    public static LocalDate sqlTimestampToLocalDate(Timestamp date){
        if(date!=null) {
            String dateStr = date.toString().substring(0, date.toString().indexOf(" "));
            return LocalDate.parse(dateStr, formatterSqlTimestamp);
        }
        else{
            return null;
        }
    }

    public static List<LocalDate> getDateByPeriod(String period,List<LocalDate> periodsDate){
        if(periodsDate.size()>0) {
            periodsDate.clear();
        }
        //HashMap<String,LocalDate> map = new HashMap<>();
        LocalDate fromDate = formatDateExt(period);
        LocalDate toDate = LocalDate.of(fromDate.getYear(),fromDate.getMonth(),fromDate.getMonth().length(fromDate.isLeapYear()));
        periodsDate.add(fromDate);
        periodsDate.add(toDate);
        //map.put("dateFrom",fromDate);
        //map.put("dateTo",toDate);
        return periodsDate;
    }

    /*
        Если вы видите это напишите!!! rp это родительный падеж )))))))))))
     */
    private static String getMountString(int month,Boolean rp){
        String date = "";
        switch (month){
            case 1 :
                if(rp)
                    date+="Января";
                else
                    date+="Январь";
                break;
            case 2 :
                if(rp)
                    date+="Февраля";
                else
                    date+="Февраль";
                break;
            case 3 :
                if(rp)
                    date+="Марта";
                else
                    date+="Март";
                break;
            case 4 :
                if(rp)
                    date+="Апреля";
                else
                    date+="Апрель";
                break;
            case 5 :
                if(rp)
                    date+="Мая";
                else
                    date+="Май";
                break;
            case 6 :
                if(rp)
                    date+="Июня";
                else
                    date+="Июнь";
                break;
            case 7 :
                if(rp)
                    date+="Июля";
                else
                    date+="Июль";
                break;
            case 8 :
                if(rp)
                    date+="Августа";
                else
                    date+="Август";
                break;
            case 9 :
                if(rp)
                    date+="Сентября";
                else
                    date+="Сентябрь";
                break;
            case 10 :
                if(rp)
                    date+="Октября";
                else
                    date+="Октябрь";
                break;
            case 11 :
                if(rp)
                    date+="Ноября";
                else
                    date+="Ноябрь";
                break;
            case 12 :
                if(rp)
                    date+="Декабря";
                else
                    date+="Декабрь";
                break;
        }
        return date;
    }

}
