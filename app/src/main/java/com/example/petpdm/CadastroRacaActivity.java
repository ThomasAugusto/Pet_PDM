package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class CadastroRacaActivity extends AppCompatActivity {

    List<String> listaRaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_raca);
        setTitle("Cadastro raça");

        listaRaca = (List<String>) getIntent().
                getSerializableExtra("lista_raca");
    }
    public void salvarRaca(View view){
        EditText raca = findViewById(R.id.editTextNomeRaca);
        String racaString = raca.getText().toString();

        if(racaString.equals("")){
            Toast.makeText(this,"favor digitar algum valor",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if(racaString.length() <= 2){
            Toast.makeText(this,"nome tem que ter mais de 3 char",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("lista_raca", (Serializable) listaRaca);
        Intent retorno = new Intent();
        retorno.putExtras(bundle);
        setResult(2,retorno);
        finish(); //fecha minha activity
    }
}