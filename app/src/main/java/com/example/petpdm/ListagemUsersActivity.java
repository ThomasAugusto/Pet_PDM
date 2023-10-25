package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

public class ListagemUsersActivity extends AppCompatActivity {
    List<Login> listaLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_users);
        setTitle("Listagem user");

        listaLogin = (List<Login>) getIntent().
                getSerializableExtra("lista_login");

        StringBuilder users = new StringBuilder();

    }

}