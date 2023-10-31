package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListagemPetActivity extends AppCompatActivity {

    DB_Pets db_pets;
    List<Pet> listaPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_pet);

        db_pets = new DB_Pets(this);
        listaPets = db_pets.getPetList();

        String[] petslist = new String[listaPets.size()];
        for(int i=0; i < listaPets.size(); i++){
            petslist[i] = "Raça: " + listaPets.get(i).getRaca();
        }
        ArrayAdapter<String>adapterpet =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,petslist);
        ListView listView = findViewById(R.id.ListViewPets);
        listView.setAdapter(adapterpet);
    }
}