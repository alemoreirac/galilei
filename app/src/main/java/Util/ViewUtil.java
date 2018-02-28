package Util;

import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.util.List;

import Model.Vida.Lesao;

/**
 * Created by Pefoce on 31/07/2017.
 */

public class ViewUtil
{
    public static void modifyAll(RelativeLayout relativeLayout, boolean opcao)
    {
        for (int i = 0; i < relativeLayout.getChildCount(); i++)
        {
            View child = relativeLayout.getChildAt(i);
            child.setEnabled(opcao);
        }
    }

    public static void nullifyAll(RelativeLayout relativeLayout)
    {
        for (int i = 0; i < relativeLayout.getChildCount(); i++)
        {
            View child = relativeLayout.getChildAt(i);

            if (child.getClass() == RelativeLayout.class)
            {
                nullifyAll((RelativeLayout) child);
                child = null;
            }
        }
    }


    public static void modifyAllRecursive(RelativeLayout relativeLayout, boolean opcao)
    {
        for (int i = 0; i < relativeLayout.getChildCount(); i++)
        {
            View child = relativeLayout.getChildAt(i);
            child.setEnabled(opcao);
            if (child.getClass() == RelativeLayout.class ||child.getClass() == LinearLayout.class)
            {
                modifyAllRecursive((RelativeLayout) child, opcao);
            }
        }
    }

    public static void disableNext(RelativeLayout relativeLayout, boolean opcao)
    {
        for (int i = 0; i < relativeLayout.getChildCount(); i++)
        {
            View child = relativeLayout.getChildAt(i);

            if(child.getClass()== EditText.class)
                ((EditText)child).setImeOptions(EditorInfo.IME_ACTION_DONE);

            if (child.getClass() == RelativeLayout.class ||child.getClass() == LinearLayout.class)
                modifyAllRecursive((RelativeLayout) child, opcao);

        }
    }



    public static void ColorirCamposLesoes(RelativeLayout relativeLayout, List<Lesao> lesoes, int color)
    {
        for (int i = 0; i < relativeLayout.getChildCount(); i++)
        {
            try
            {
                TextView txv = (TextView) relativeLayout.getChildAt(i);
                txv.setTextColor(color);

            } catch (Exception e)
            {
            }
        }

        for (Lesao l : lesoes)
        {
            for (int i = 0; i < relativeLayout.getChildCount(); i++)
            {
                try
                {
                    TextView txv = (TextView) relativeLayout.getChildAt(i);
                    if (txv.getText().toString() == l.getSecaoLesao().getValor())
                        txv.setTextColor(Color.RED);
                } catch (Exception e)
                {
                }
            }

        }
    }
}
