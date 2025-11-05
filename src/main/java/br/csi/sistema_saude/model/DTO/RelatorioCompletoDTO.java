package br.csi.sistema_saude.model.DTO;
import java.time.LocalDate;

public class RelatorioCompletoDTO {

    private LocalDate data;
    private Double peso;
    private Integer glicose;
    private Integer colesterolHDL;
    private Integer colesterolVLDL;
    private Integer creatina;
    private Integer trigliceridio;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getGlicose() {
        return glicose;
    }

    public void setGlicose(Integer glicose) {
        this.glicose = glicose;
    }

    public Integer getColesterolHDL() {
        return colesterolHDL;
    }

    public void setColesterolHDL(Integer colesterolHDL) {
        this.colesterolHDL = colesterolHDL;
    }

    public Integer getColesterolVLDL() {
        return colesterolVLDL;
    }

    public void setColesterolVLDL(Integer colesterolVLDL) {
        this.colesterolVLDL = colesterolVLDL;
    }

    public Integer getCreatina() {
        return creatina;
    }

    public void setCreatina(Integer creatina) {
        this.creatina = creatina;
    }

    public Integer getTrigliceridio() {
        return trigliceridio;
    }

    public void setTrigliceridio(Integer trigliceridio) {
        this.trigliceridio = trigliceridio;
    }
}
