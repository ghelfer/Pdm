package com.unisc.pdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

public class Aula5SlidesActivity extends AppCompatActivity {

    private Spinner categoria1, categoria2;
    String [] array_gasto = {"Água","Luz","Telefone","Outros"};

    private ListView listView;
    String [] gastos = {"Passagem","Alimentação","Estadia","Outros"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula5_slides);

        categoria1 = findViewById(R.id.categoria1);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.categoria_gasto,
                R.layout.meu_spinner_item);
        categoria1.setAdapter(adapter1);

        categoria1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"Posicao=" + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        categoria2 = findViewById(R.id.categoria2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, array_gasto);
        categoria2.setAdapter(adapter2);

        categoria2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"clicado: " + array_gasto[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_meu_item_1, Arrays.asList(gastos));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String valor = gastos[position];
                Toast.makeText(getApplicationContext(),"clicado: " + valor, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meumenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemNew:
                Intent intent = new Intent(Aula5SlidesActivity.this, Aula5ListActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemAdd:
                Intent t = new Intent(Aula5SlidesActivity.this, TestActivity.class);
                startActivity(t);
                Toast.makeText(this, "item add clicado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemSave:
                Intent j = new Intent(Aula5SlidesActivity.this, OSMActivity.class);
                startActivity(j);
                Toast.makeText(this, "item save clicado", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
