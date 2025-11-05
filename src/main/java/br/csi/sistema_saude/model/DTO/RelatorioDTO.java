package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Relatorio;

import java.time.LocalDate;

public record RelatorioDTO (Double valor, LocalDate data){

    public RelatorioDTO(Relatorio relatorio, Double valor) {
        this(valor, relatorio.getId().getData());
    }

}
