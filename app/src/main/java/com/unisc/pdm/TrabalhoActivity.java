package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class TrabalhoActivity extends AppCompatActivity {

    private TextView txtDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalho);


        txtDados = findViewById(R.id.txtDados);

        HttpAsyncTask task = new HttpAsyncTask();
        task.execute("https://ghelfer.000webhostapp.com/webservice.php");
    }

    public void acessoClick(View view) {
        HttpAsyncTaskSend task = new HttpAsyncTaskSend();
        task.execute("https://ghelfer.000webhostapp.com/webservice2.php");
    }


    class HttpAsyncTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(TrabalhoActivity.this);
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

            txtDados.setText(result);

        }
    }



    class HttpAsyncTaskSend extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(TrabalhoActivity.this);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                ContentValues values = new ContentValues();
                values.put("nome", "Android");
                values.put("idade", "45");
                values.put("cidade", "Vera Cruz");
                OutputStream out = con.getOutputStream();
                BufferedWriter writer =  new BufferedWriter(new OutputStreamWriter(out));
                writer.write(getFormData(values));
                writer.flush();
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

        private String getFormData(ContentValues values) {
            try {
                StringBuilder sb = new StringBuilder();
                boolean first = true;
                for (Map.Entry<String, Object> entry : values.valueSet()) {
                    if (first)
                        first = false;
                    else
                        sb.append("&");
                    sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                }
                return sb.toString();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();

            txtDados.setText(result);

        }
    }
}
