package Util;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Pefoce on 22/06/2017.
 */

public class TempoUtil
{
    private static LinearLayout llPicker;

    public static boolean checkValues(LinearLayout ll)
    {
        llPicker = ll;

        EditText edtDia= null,edtMes=null,edtAno=null;

        for (int i = 0; i < llPicker.getChildCount(); i++)
        {
            View v = llPicker.getChildAt(i);
            if(v.getClass().equals(AppCompatEditText.class))
            {
                if(i == 0)
                    edtDia = (AppCompatEditText) v;

                if(i == 2)
                    edtMes = (AppCompatEditText) v;

                if(i == 4)
                    edtAno = (AppCompatEditText) v;
            }
        }

        if(edtMes.getText().toString().isEmpty() &&
                edtDia.getText().toString().isEmpty() &&
                edtAno.getText().toString().isEmpty() )
return true;
                try
                {
                    int dia,mes,ano;
                    dia = Integer.valueOf(edtDia.getText().toString());
                    mes = Integer.valueOf(edtMes.getText().toString());
                    ano = Integer.valueOf(edtAno.getText().toString());


                    Calendar c = Calendar.getInstance();

                    c.setLenient(false);
                    c.set(ano, mes-1, dia);
                    c.getTime();
                    return true;
                }
                catch (Exception e)
                {
                    return false;
                }

    }

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

//        Calendar c = Calendar.getInstance();
//
//        c.setTime(date);
//
//        if(c.YEAR<1850)
//
//            return null;
//        if(c.MONTH>12 || c.MONTH<0)
//            return null;
//        if(c.DAY_OF_MONTH>31 || c.DAY_OF_MONTH<0)
//            return null;
//        c.setLenient();
//
//        if(date.after(Calendar.getInstance().getTime()))
//            return null;


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
