package com.unisc.pdm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpAsyncTask extends AsyncTask<String, Void, String> {

    ProgressDialog dialog;
    Context ctx;

    public HttpAsyncTask(Context ctx) {
            this.ctx = ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(ctx);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            if (status==200) {
                InputStream stream = new BufferedInputStream(con.getInputStream());
                BufferedReader buff = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();
                String str = "";
                while ((str = buff.readLine()) != null) {
                    builder.append(str);
                }
                con.disconnect();
                return builder.toString();
            }
        }
        catch (Exception ex) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        dialog.dismiss();
        }

}

