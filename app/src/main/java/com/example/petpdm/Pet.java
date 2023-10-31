package com.example.petpdm;

import java.io.Serializable;

public class Pet implements Serializable {

    private int id;
    private String nome;
    private Double peso;
    private String raca;

    public Pet(int id, String nome, Double peso, String raca) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.raca = raca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Pet() {
        this.nome = nome;
        this.peso = peso;
        this.raca = raca;
    }

    public Pet(String nome, Double peso, String raca) {
        this.nome = nome;
        this.peso = peso;
        this.raca = raca;
    }

    public Pet(String raca) {
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}
