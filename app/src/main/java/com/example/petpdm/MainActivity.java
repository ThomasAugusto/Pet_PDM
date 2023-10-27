package com.example.petpdm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Login> listaLogin = new ArrayList<>();
    DB_Users DBUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
        DBUsers = new DB_Users(this);
        listaLogin = new  ArrayList<>();
    }

    public void mostrarMenu(View view) {

        // eu pego os componentes do layout
        EditText login = findViewById(R.id.editTextLogin);
        Button botaoLogin = findViewById(R.id.buttonLogar);
        EditText senha = findViewById(R.id.editTextSenha);

        String loginString = login.getText().toString();
        String senhaString = senha.getText().toString();

        boolean encontrou = DBUsers.checkpassword(loginString,senhaString);

        if (encontrou) {
            // abrindo uma nova activity
            Bundle bundle = new Bundle();
            bundle.putString("login", login.getText().toString());

            Intent intent = new Intent(this, DashboardActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Errou o usuário ou senha",Toast.LENGTH_LONG).show();
        }

    }

    public void TelaDeCadastro(View view) {
        Intent intentTelaCadastro = new Intent(this, CadastroUserActivity.class);
        intentTelaCadastro.putExtra("lista_login", (Serializable) listaLogin);
        startActivityForResult(intentTelaCadastro, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 2) {// cadastro pet e retorna a lista de pet
            listaLogin = (List<Login>) data.getSerializableExtra("lista_login");
        }
    }
}