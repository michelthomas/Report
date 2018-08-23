package br.com.cpsoftware;

public class Tipo {

    private String nome;
    private Long id;

    public Tipo(String nome, Long id) {
        this.nome = nome;
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}