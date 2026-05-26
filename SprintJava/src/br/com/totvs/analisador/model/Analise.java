package br.com.totvs.analisador.model;

public class Analise {

    private String sentimento;
    private String risco;
    private int chanceRenovacao;
    private String resumo;
    private String recomendacao;

    public Analise() {
    }

    public String getSentimento() {
        return sentimento;
    }

    public void setSentimento(String sentimento) {
        this.sentimento = sentimento;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    public int getChanceRenovacao() {
        return chanceRenovacao;
    }

    public void setChanceRenovacao(int chanceRenovacao) {
        this.chanceRenovacao = chanceRenovacao;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }
}