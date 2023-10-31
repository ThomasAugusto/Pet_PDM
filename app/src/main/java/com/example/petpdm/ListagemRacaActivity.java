package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListagemRacaActivity extends AppCompatActivity {
    DB_Racas db_racas;
    List<Pet> listaRaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_raca);

        db_racas = new DB_Racas(this);
        listaRaca = db_racas.getRacaList();

        String[] nomesRaca = new String[listaRaca.size()];
        for(int i=0; i < listaRaca.size(); i++){
            nomesRaca[i] = "Raça: " + listaRaca.get(i).getRaca();
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomesRaca);

        ListView ListViewPets = findViewById(R.id.listview);
        ListViewPets.setAdapter(adapter);
    }
}
