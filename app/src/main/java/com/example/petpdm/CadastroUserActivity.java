package com.example.petpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class CadastroUserActivity extends AppCompatActivity {


    List<Login> listaLogin;
    DB_Users DBUsers;

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
        EditText username = findViewById(R.id.editTextNewUser);
        EditText password = findViewById(R.id.editTextNewPassword);
        EditText repassword = findViewById(R.id.editTextRepetiSenha);
        DBUsers = new DB_Users(this);

        String user = username.getText().toString();
        String pass = password.getText().toString();
        String repass = repassword.getText().toString();

        if(user.length() <= 0 || password.length() <= 0 || repassword.length() <= 0){
            Toast.makeText(this,"favor digitar algum valor",Toast.LENGTH_LONG).show();
            return;
        }
        if(!pass.equals(repass)){
            Toast.makeText(this,"Favor digitar senhas iguais",Toast.LENGTH_LONG).show();
            return;
        }
        if(pass.length() <= 3){
            Toast.makeText(this,"A senha deve ter no mínimo quatro dígitos!",Toast.LENGTH_LONG).show();
            return;
        }
        Boolean checkuser = DBUsers.checkusername(user);
        if(checkuser){
            Toast.makeText(this,"nome de usuário ja existe!",Toast.LENGTH_LONG).show();
            return;
        }
        Boolean insert = DBUsers.insertData(user,pass);
        if(insert==false){
            Toast.makeText(this,"falha no cadastro!",Toast.LENGTH_LONG).show();
            return;
        }

        Login usuario = new Login();
        usuario.setLogin(user);
        usuario.setSenha(pass);

        listaLogin.add(usuario);
        Toast.makeText(this,"sucesso no cadastro do usuario",Toast.LENGTH_LONG).show();
        username.setText("");
        password.setText("");
        repassword.setText("");

        for(Login us : listaLogin) {
            Log.d("USER", us.getLogin());
            Log.d("USER", us.getSenha() + "");
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