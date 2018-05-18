package Util;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Pefoce on 22/06/2017.
 */

public class TempoUtil
{

    public static void setTime(final TextView textView, Context ctx)
    {
        int hour, minute;

        try
        {
            String[] parts = textView.getText().toString().split(":");

            hour = Integer.valueOf(parts[0]);

            minute = Integer.valueOf(parts[1]);
        } catch (Exception e)
        {
            hour = 0;
            minute = 0;
        }
        TimePickerDialog timePickerDialog = new TimePickerDialog(ctx,

                new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute)
                    {
                        textView.setText(integerToHour(minute,hourOfDay));
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    public static void setDate(final TextView textView, Context ctx)
    {
        int mYear, mMonth, mDay;
        try
        {
            String[] parts = textView.getText().toString().split("/");

            mDay = Integer.valueOf(parts[0]);

            mMonth = Integer.valueOf(parts[1]);

            mYear = Integer.valueOf(parts[2]);
        } catch (Exception e)
        {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        }


        DatePickerDialog datePickerDialog = new DatePickerDialog(ctx,
                new DatePickerDialog.OnDateSetListener()
                {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth)
                    {
                        textView.setText(integerToDate(dayOfMonth,monthOfYear,year));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }


    public static Date stringToDate(String value)
    {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try
        {
            date = format.parse(value);
        } catch (ParseException e)
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
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        return date;
    }

    public static String getDataExtenso(Date d)
    {
        Locale local = new Locale("pt", "BR");
        DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
        String dataFormatada = formato.format(d);

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(d);

        return dataFormatada;
    }

    public static boolean DataAntesDe(String time, String endtime)
    {

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try
        {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if (date1.before(date2))
            {
                return true;
            } else
            {

                return false;
            }
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean HoraAntesDe(String time, String endtime)
    {

        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try
        {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if (date1.before(date2))
            {
                return true;
            } else
            {

                return false;
            }
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static String Parametrize(int value)
    {
        if (value > 9)
            return String.valueOf(value);
        if (value < 10)
            return "0" + value;
        return "";
    }

    public static String integerToHour(int minute,int hour)
    {
        String resultHour = "";

        resultHour += Parametrize(hour);

        resultHour += ":";

        resultHour += Parametrize(minute);

        return resultHour;
    }


    public static String integerToDate(int day, int month,int year)
    {
        String resultDate = "";

        resultDate += Parametrize(day);

        resultDate += "/";

        resultDate += Parametrize(++month);

        resultDate += "/";

        resultDate += year;

        return resultDate;
    }

}
