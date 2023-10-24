package com.example.petpdm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    List<Pet> listaPet ;
    List<String> listaRaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("DashBoard");

        // vamos pegar os dados enviado pela activity pai
        String login = (String) getIntent().getSerializableExtra("login");
        Integer idade = (Integer) getIntent().getSerializableExtra("idade");


        EditText olaLogin = findViewById(R.id.editTextTextOlaLogin);
        olaLogin.setText("Olá " +  login);

        Toast.makeText(this,"Olá " + login,Toast.LENGTH_LONG).show();

        listaPet = new ArrayList<Pet>();
        listaRaca = new ArrayList<String>();
    }

    public void abrirCadastroPet(View view){
        Intent intentAbriCadastro = new Intent(this,
                            CadastroPetActivity.class);

        intentAbriCadastro.putExtra("lista_pet", (ArrayList<Pet>) this.listaPet);
        startActivityForResult(intentAbriCadastro,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 2) {// cadastro pet e retorna a lista de pet
            listaPet = (List<Pet>) data.getSerializableExtra("Lista_pet");
        }
    }

    public void abrirListagemPet(View view) {
        Intent intentListagem = new Intent(this,
                ListagemPetActivity.class);

        intentListagem.putExtra("lista_pet", (ArrayList<Pet>)this.listaPet);

        startActivity(intentListagem);

    }
    public void abrirCadastroRaca(View view) {
        Intent intentCadastroRaca = new Intent(this,
                CadastroRacaActivity.class);

        intentCadastroRaca.putExtra("lista_raca", (ArrayList<String>) this.listaRaca);
        startActivityForResult(intentCadastroRaca,2);


    }
}