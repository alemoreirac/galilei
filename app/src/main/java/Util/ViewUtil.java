package Util;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.pefoce.peritolocal.R;

/**
 * Created by Pefoce on 31/07/2017.
 */

public class ViewUtil
{
    public static void modifyAll(RelativeLayout relativeLayout,boolean opcao)
    {
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            View child = relativeLayout.getChildAt(i);
            child.setEnabled(opcao);
        }
    }
}
