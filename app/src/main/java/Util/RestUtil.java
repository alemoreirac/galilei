package Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Pefoce on 02/08/2018.
 */

public class RestUtil
{
    private static int versaoLocal = -1;
    private static int versaoServidor = -1;




    public static void checkVersao(Context context)
    {
        SharedPreferences sharedpreferences = context.getSharedPreferences("versao", Context.MODE_PRIVATE);

        versaoLocal = sharedpreferences.getInt("versao", -1);

            @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, String> asyncTask2 = new AsyncTask<Void, Void, String>()
            {
                @Override
                protected String doInBackground(Void... params)
                {
                    try
                    {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://189.90.160.48:7070/galileiWebService/cliente/versao")
                                .build();

                        Response response = client.newCall(request).execute();

                        versaoServidor = Integer.valueOf(response.body().toString());

                        if(versaoLocal < versaoServidor)
                        {
                            //TODO: Atualizar valor de tudo
                        }

                        return response.body().string();

                    } catch (Exception e)
                    {
                        return e.toString();
                    }
                }

                @Override
                protected void onPostExecute(String s)
                {
                    super.onPostExecute(s);
                    if (s != null)
                    {

                    }
                }
            };

            asyncTask2.execute();

    }
}
