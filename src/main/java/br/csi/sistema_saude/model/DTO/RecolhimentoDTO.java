package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Farmacia;
import br.csi.sistema_saude.model.Recolhimento;
import br.csi.sistema_saude.model.RecolhimentoFarmacia;
import br.csi.sistema_saude.model.StatusRecolhimento;

import java.util.ArrayList;
import java.util.List;

public record RecolhimentoDTO(
        int codRecolhimento,
        String emailCliente,
        String nomeMedicamento
) {

    public RecolhimentoDTO(Recolhimento recolhimento) {
        this(
                recolhimento.getCodRecolhimento(),
                recolhimento.getEmailCliente(),
                recolhimento.getMedicamento().getBancoMedicamentos().getNome()
        );
    }
}