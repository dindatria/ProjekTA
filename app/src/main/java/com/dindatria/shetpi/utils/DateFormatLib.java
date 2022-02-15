package com.dindatria.shetpi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatLib {

    public String currentDay(){
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();

        return dateFormat.format(date);
    }
    public String currentMonth(){
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();

        return dateFormat.format(date);
    }
    public String currentYear(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }
}
