package com.example.petpdm;

import java.io.Serializable;

public class Login implements Serializable {

    private String login;
    private String senha;

    public Login() {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
