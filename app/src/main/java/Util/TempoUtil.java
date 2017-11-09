package Util;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pefoce on 22/06/2017.
 */

public class TempoUtil {

    public static void setTime(final TextView textView, Context ctx)
    {
        int hour = 0, minute = 0;
        TimePickerDialog timePickerDialog = new TimePickerDialog(ctx,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        textView.setText(hourOfDay + ":" + minute);
                        if(minute == 0)
                        {
                            textView.setText(hourOfDay + ":" + minute+"0");
                        }
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    public static void setDate(final TextView textView,Context ctx)
    {
        int mYear,mMonth,mDay;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(ctx,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        textView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }


    public static Date stringToDate(String value)
    {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try
        {
             date = format.parse(value);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return date;
    }

    public static Date stringToTime(String value)
    {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = null;
        try
        {
            date = format.parse(value);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return date;
    }

    public static boolean HoraAntesDe(String time, String endtime) {

        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if(date1.before(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }


}
