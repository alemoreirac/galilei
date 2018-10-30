package Util;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Pefoce on 24/08/2017.
 */

public class FileUtil
{


    public static void copyFolder(File source, File destination)
    {
        if (source.isDirectory())
        {
            if (!destination.exists())
            {
                destination.mkdirs();
            }

            String files[] = source.list();

            for (String file : files)
            {
                File srcFile = new File(source, file);
                File destFile = new File(destination, file);

                copyFolder(srcFile, destFile);
            }
        }
        else
        {
            InputStream in = null;
            OutputStream out = null;

            try
            {
                in = new FileInputStream(source);
                out = new FileOutputStream(destination);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = in.read(buffer)) > 0)
                {
                    out.write(buffer, 0, length);
                }
            }
            catch (Exception e)
            {
                try
                {
                    in.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                try
                {
                    out.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

public static void deleteAll(String path)
{
    File dir = new File(Environment.getExternalStorageDirectory()+path);
    if (dir.isDirectory())
    {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++)
        {
            System.out.print("File Deleted: "+children.toString());
            //Log.i();
            new File(dir, children[i]).delete();
        }
    }
}

public static byte[] toByteArray(File f)
{
    int size = (int) f.length();
    byte[] bytes = new byte[size];
    try {
        BufferedInputStream buf = new BufferedInputStream(new FileInputStream(f));
        buf.read(bytes, 0, bytes.length);
        buf.close();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return bytes;
}


}
