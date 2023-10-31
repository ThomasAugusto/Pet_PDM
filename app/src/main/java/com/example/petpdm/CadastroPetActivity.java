package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class CadastroPetActivity extends AppCompatActivity {

    List<Pet> listaPet;
    List<Pet> listaRaca;
    DB_Racas db_racas;
    DB_Pets db_pets;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);
        setTitle("Cadastro Pet");
        db_pets = new DB_Pets(this);
        db_racas = new DB_Racas(this);
        listaRaca = db_racas.getRacaList();
        button = findViewById(R.id.button);


        String[] nomesRaca = new String[listaRaca.size()];
        for(int i=0; i < listaRaca.size(); i++) {
            nomesRaca[i] = "Raça: " + listaRaca.get(i).getRaca();
        }

        Spinner spinner = findViewById(R.id.spinnerRacas);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nomesRaca);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

    }

    public void salvarPet(View view) {
        EditText nome = findViewById(R.id.editTextNomeDog);
        EditText peso = findViewById(R.id.editTextPesoDog);
        Spinner spinner = findViewById(R.id.spinnerRacas);

        String nomeString = nome.getText().toString();
        double pesoDouble = Double.parseDouble(String.valueOf(peso));
        String raca = (String) spinner.getSelectedItem();

        if(nomeString.equals("") || pesoDouble>0){
            Toast.makeText(this,"favor digitar algum valor", Toast.LENGTH_LONG).show();
            return;
        }
        if(nomeString.length() <= 2){
            Toast.makeText(this,"nome tem que ter mais de 3 char",Toast.LENGTH_LONG).show();
            return;
        }
        Boolean insert = db_pets.insertData(nomeString,pesoDouble,raca);
        if(insert==false){
            Toast.makeText(this,"falha no cadastro!",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this,"sucesso no cadastro do pet",Toast.LENGTH_LONG).show();

        Pet pet = new Pet();
        pet.setNome(nomeString);
        pet.setPeso(Double.parseDouble(String.valueOf(pesoDouble)));
        pet.setRaca(raca);

        listaPet.add(pet);


        nome.setText("");
        peso.setText("");


        for(Pet cachorro : listaPet) {
            Log.d("PET", cachorro.getNome());
            Log.d("PET", cachorro.getPeso() + "");
            Log.d("PET", "---------------");
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("lista_pet", (Serializable) listaPet);
        Intent retorno = new Intent();
        retorno.putExtras(bundle);
        setResult(2,retorno);
        finish(); // fecho minha active

    }
}