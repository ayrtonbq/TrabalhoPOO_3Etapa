package model;

import java.util.Date;

public class RegistroServico {
    private int id;
    private Date data;
    private Servico servico;
    private Animal animal;
    private double valor;
    private String observacoes;

    public RegistroServico(int id, Date data, Servico servico, Animal animal, double valor, String observacoes) {
        this.id = id;
        this.data = data;
        this.servico = servico;
        this.animal = animal;
        this.valor = valor;
        this.observacoes = observacoes;
    }

    public RegistroServico() {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public Servico getServico() {
        return servico;
    }
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    
}
