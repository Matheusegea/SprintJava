package br.com.totvs.analisador.model;

public class Reuniao {

    private int id;
    private String data;
    private String transcricao;
    private Cliente cliente;

    public Reuniao() {
    }

    public Reuniao(int id, String data,String transcricao, Cliente cliente) {

        this.id = id;
        this.data = data;
        this.transcricao = transcricao;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTranscricao() {
        return transcricao;
    }

    public void setTranscricao(String transcricao) {
        this.transcricao = transcricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}