package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Aula7Ex2Activity extends AppCompatActivity {

    private EditText txtCEP;
    private TextView txtLogradouro;
    private TextView txtComplemento;
    private TextView txtBairro;
    private TextView txtLocalidade;
    private TextView txtUf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula7_ex2);

        txtCEP = findViewById(R.id.txtCEP);
        txtLogradouro = findViewById(R.id.txtLogradouro);
        txtComplemento = findViewById(R.id.txtComplemento);
        txtBairro = findViewById(R.id.txtBairro);
        txtLocalidade = findViewById(R.id.txtLocalidade);
        txtUf = findViewById(R.id.txtUf);

    }

    public void onClickBuscar(View view) {
        String cep = txtCEP.getText().toString();
        HttpAsyncTask task = new HttpAsyncTask(Aula7Ex2Activity.this);
        task.execute("https://viacep.com.br/ws/" + cep + "/json");
        try {
            String result = task.get();
            JSONObject obj = new JSONObject(result);
            txtLogradouro.setText(obj.getString("logradouro"));
            txtComplemento.setText(obj.getString("complemento"));
            txtBairro.setText(obj.getString("bairro"));
            txtLocalidade.setText(obj.getString("localidade"));
            txtUf.setText(obj.getString("uf"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}