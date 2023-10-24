package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

public class ListagemPetActivity extends AppCompatActivity {

    List<Pet> listaPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_pet);
        setTitle("Listagem PET");

        listaPet = (List<Pet>) getIntent().
                getSerializableExtra("lista_pet");

        EditText listagem = findViewById(R.id.
                        editTextTextMultiLineListagem);

        StringBuilder mensagem = new StringBuilder();

        for(Pet cachorro : listaPet){
            mensagem
                    .append("Nome: ")
                    .append(cachorro.nome)
                    .append("\n");

            mensagem
                    .append("Peso: ")
                    .append(cachorro.peso)
                    .append("\n");
            mensagem.append("*********\n");
        }
        listagem.setText(mensagem.toString());
    }
}