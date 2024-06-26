package model;

public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private char sexo;
    private double peso;
    private byte[] foto;
    private Proprietario proprietario;
  
    public Animal(int id, String nome, String especie, String raca, int idade, char sexo, double peso, byte[] foto,
            Proprietario proprietario) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
        this.foto = foto;
        this.proprietario = proprietario;
    }
    public Animal() {
        //TODO Auto-generated constructor stub
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getRaca() {
        return raca;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public Proprietario getProprietario() {
        return proprietario;
    }
    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

}
