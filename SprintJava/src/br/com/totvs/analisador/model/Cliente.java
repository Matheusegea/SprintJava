package br.com.totvs.analisador.model;

public class Cliente extends Usuario {

    private String empresa;
    private String plano;

    public Cliente() {
    }

    public Cliente(int id, String nome, String email,
                   String empresa, String plano) {

        super(id, nome, email);

        this.empresa = empresa;
        this.plano = plano;
    }

    @Override
    public String exibirDados() {

        return "Nome: " + getNome() + " | Empresa: " + empresa + " | Plano: " + plano;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }
}