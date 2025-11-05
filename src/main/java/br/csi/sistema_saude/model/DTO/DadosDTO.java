package br.csi.sistema_saude.model.DTO;

public class DadosDTO {

    private int codDado;
    private double peso;
    private int glicose;
    private int colesterolHDL;
    private int colesterolVLDL;
    private int creatina;
    private int trigliceridio;


    public int getCodDado() {
        return codDado;
    }

    public void setCodDado(int codDado) {
        this.codDado = codDado;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getGlicose() {
        return glicose;
    }

    public void setGlicose(int glicose) {
        this.glicose = glicose;
    }

    public int getColesterolHDL() {
        return colesterolHDL;
    }

    public void setColesterolHDL(int colesterolHDL) {
        this.colesterolHDL = colesterolHDL;
    }

    public int getColesterolVLDL() {
        return colesterolVLDL;
    }

    public void setColesterolVLDL(int colesterolVLDL) {
        this.colesterolVLDL = colesterolVLDL;
    }

    public int getCreatina() {
        return creatina;
    }

    public void setCreatina(int creatina) {
        this.creatina = creatina;
    }

    public int getTrigliceridio() {
        return trigliceridio;
    }

    public void setTrigliceridio(int trigliceridio) {
        this.trigliceridio = trigliceridio;
    }
}
