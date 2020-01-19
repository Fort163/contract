package test.contract.utils;

import java.time.LocalDateTime;

public class ExceptionWrapper {
    private String message;
    private String dateTime;

    public ExceptionWrapper(String message, String dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public ExceptionWrapper(String message) {
        this(message, DateHelpler.localDateTimeToString(LocalDateTime.now()));
    }

    public static ExceptionWrapper create(String message) {
        return new ExceptionWrapper(message);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
