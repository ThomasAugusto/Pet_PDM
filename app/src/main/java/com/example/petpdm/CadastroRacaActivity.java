package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class CadastroRacaActivity extends AppCompatActivity {

    List<Pet> listaRaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_raca);
        setTitle("Cadastro raça");

        listaRaca = (List<Pet>) getIntent().
                getSerializableExtra("lista_raca");

    }
    public void cadastrarRaca(View view){
        EditText raca = findViewById(R.id.editTextNomeRaca);
        String racaString = raca.getText().toString();
        DB_Racas db_racas;
        db_racas = new DB_Racas(this);


        if(racaString.equals("")){
            Toast.makeText(this,"favor digitar algum valor",Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if(racaString.length() <= 2){
            Toast.makeText(this,"nome tem que ter mais de 3 char",Toast.LENGTH_LONG).show();
            return;
        }
        Boolean checkracas = db_racas.checkracas(racaString);
        if(checkracas){
            Toast.makeText(this,"nome de raça ja existe!",Toast.LENGTH_LONG).show();
            return;
        }
        Boolean insert = db_racas.insertData(racaString);
        if(insert==false){
            Toast.makeText(this,"falha no cadastro!",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this,"sucesso no cadastro de raça",Toast.LENGTH_LONG).show();

        Bundle bundle = new Bundle();
        bundle.putSerializable("lista_raca", (Serializable) listaRaca);
        Intent retorno = new Intent();
        retorno.putExtras(bundle);
        setResult(1,retorno);
        finish(); //fecha minha activity
    }
}