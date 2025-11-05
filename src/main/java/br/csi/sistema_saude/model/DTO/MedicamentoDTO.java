package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Medicamento;

import java.time.LocalDate;

public record MedicamentoDTO(
        int codMedicamento,
        String nomeMedicamento,
        int doseDiaria,
        LocalDate dataInicio,
        int duracaoTratamento
) {
    public MedicamentoDTO(Medicamento medicamento) {
        this(
                medicamento.getCodMedicamento(),
                medicamento.getBancoMedicamentos().getNome(),
                medicamento.getDoseDiaria(),
                medicamento.getDataInicio(),
                medicamento.getDuracaoTratamento()
        );
    }
}