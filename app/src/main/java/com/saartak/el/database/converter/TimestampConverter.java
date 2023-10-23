package com.saartak.el.database.converter;


import android.text.TextUtils;

import androidx.room.TypeConverter;

import com.saartak.el.constants.AppConstant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConverter {
//    static DateFormat df = new SimpleDateFormat(AppConstant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS);
    static DateFormat df = new SimpleDateFormat(AppConstant.DATE_FORMAT_YYYY_MM_DD);

    @TypeConverter
    public static Date toDate(String value) {
        try {
            if (!TextUtils.isEmpty(value)) {
                try {
                    return df.parse(value);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            } else {
                return null;
            }
        }catch (Exception ex){
//            ex.printStackTrace();
            return null;
        }
    }
}
