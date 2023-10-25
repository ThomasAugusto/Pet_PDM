package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadastroPetActivity extends AppCompatActivity {

    List<Pet> listaPet;
    List<String> listaRaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);
        setTitle("Cadastro Pet");

        listaPet = (List<Pet>) getIntent().
        getSerializableExtra("lista_pet");
        listaRaca = (List<String>) getIntent().
                getSerializableExtra("lista_raca");


    }

    public void salvarPet(View view){

        EditText nome = findViewById(R.id.editTextNomeDog);
        EditText peso = findViewById(R.id.editTextPesoDog);
        Spinner spinnerRaca = (Spinner) findViewById(R.id.spinnerRacas);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listaRaca);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerRaca.setAdapter(dataAdapter);

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
        pet.setNome(nomeString);
        pet.setPeso(Double.parseDouble(pesoString));

        listaPet.add(pet);
        Toast.makeText(this,"sucesso no cadastro do pet",
                        Toast.LENGTH_LONG)
                .show();
        nome.setText("");
        peso.setText("");


        for(Pet cachorro : listaPet) {
            Log.d("PET", cachorro.getNome());
            Log.d("PET", cachorro.getPeso() + "");
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