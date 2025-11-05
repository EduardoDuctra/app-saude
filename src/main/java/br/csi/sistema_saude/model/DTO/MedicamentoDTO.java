package br.csi.sistema_saude.model.DTO;

public class MedicamentoDTO {
    private int codMedicamento;
    private String nomeMedicamento;
    private int doseDiaria;
    private String dataInicio;
    private int duracaoTratamento;

    public int getCodMedicamento() {
        return codMedicamento;
    }

    public void setCodMedicamento(int codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public int getDoseDiaria() {
        return doseDiaria;
    }

    public void setDoseDiaria(int doseDiaria) {
        this.doseDiaria = doseDiaria;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getDuracaoTratamento() {
        return duracaoTratamento;
    }

    public void setDuracaoTratamento(int duracaoTratamento) {
        this.duracaoTratamento = duracaoTratamento;
    }
}
