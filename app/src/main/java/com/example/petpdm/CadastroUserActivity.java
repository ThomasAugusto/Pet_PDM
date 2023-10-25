package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadastroUserActivity extends AppCompatActivity {


    List<Login> listaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);
        setTitle("Cadastro de usuário");

        // pegar a lista
        listaLogin = (List<Login>) getIntent().
                getSerializableExtra("lista_login");
    }

    // criar funcao para pegar os daods da tela e salvar na lista e chamar metodo setResult com o finish.

    public void CadastrarUsuario(View view) {
        EditText login = findViewById(R.id.editTextNewUser);
        EditText senha = findViewById(R.id.editTextNewPassword);
        EditText repetiSenha = findViewById(R.id.editTextRepetiSenha);

        String loginString = login.getText().toString();
        String senhaString = senha.getText().toString();
        String repetiSenhaString = repetiSenha.getText().toString();

        if(loginString.equals("") || senhaString.equals("")){
            Toast.makeText(this,"favor digitar algum valor",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if(!senhaString.equals(repetiSenhaString)){
            Toast.makeText(this,"Favor digitar senhas iguais",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if(senhaString.length() <= 3){
            Toast.makeText(this,"A senha deve ter no mínimo quatro dígitos!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        Login usuario = new Login();
        usuario.setLogin(loginString);
        usuario.setSenha(senhaString);

        listaLogin.add(usuario);
        Toast.makeText(this,"sucesso no cadastro do usuario",
                        Toast.LENGTH_LONG)
                .show();
        login.setText("");
        senha.setText("");
        repetiSenha.setText("");

        for(Login user : listaLogin) {
            Log.d("USER", user.getLogin());
            Log.d("USER", user.getSenha() + "");
            Log.d("USER", "---------------");
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("lista_login", (Serializable) listaLogin);
        Intent retorno = new Intent();
        retorno.putExtras(bundle);
        setResult(2,retorno);
        finish(); //fecha minha activity
    }
}