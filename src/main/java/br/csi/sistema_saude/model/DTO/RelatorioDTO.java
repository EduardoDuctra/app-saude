package br.csi.sistema_saude.model.DTO;

import java.time.LocalDate;

public class RelatorioDTO {

    private Double valor;
    private LocalDate data;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
