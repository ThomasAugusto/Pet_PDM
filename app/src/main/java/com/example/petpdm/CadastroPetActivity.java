package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadastroPetActivity extends AppCompatActivity {

    List<Pet> listaPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);
        setTitle("Cadastro Pet");

        listaPet = (List<Pet>) getIntent().
        getSerializableExtra("lista_pet");


    }

    public void salvarPet(View view){

        EditText nome = findViewById(R.id.editTextNomeDog);
        EditText peso = findViewById(R.id.editTextPesoDog);

        String nomeString = nome.getText().toString();
        String pesoString = peso.getText().toString();

        if(nomeString.equals("") || pesoString.equals("")){
            Toast.makeText(this,"favor digitar algum valor",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if(nomeString.length() <= 2){
            Toast.makeText(this,"nome tem que ter mais de 3 char",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        Pet pet = new Pet();
        pet.nome = nomeString;
        pet.peso = Double.parseDouble(pesoString);

        listaPet.add(pet);
        Toast.makeText(this,"sucesso no cadastro do pet",
                        Toast.LENGTH_LONG)
                .show();
        nome.setText("");
        peso.setText("");


        for(Pet cachorro : listaPet) {
            Log.d("PET", cachorro.nome);
            Log.d("PET", cachorro.peso + "");
            Log.d("PET", "---------------");
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("Lista_pet", (Serializable) listaPet);
        Intent retorno = new Intent();
        retorno.putExtras(bundle);
        setResult(2,retorno);
        finish(); //fecha minha activity


    }
}