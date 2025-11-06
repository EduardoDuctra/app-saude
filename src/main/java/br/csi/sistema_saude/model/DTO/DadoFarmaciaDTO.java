package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Farmacia;

public record DadoFarmaciaDTO(
        Integer id,
        DadoUsuarioDTO usuario,
        String cnpj,
        String telefone
) {
    public DadoFarmaciaDTO(Farmacia farmacia) {
        this(
                farmacia.getCodFarmacia(),
                new DadoUsuarioDTO(farmacia.getUsuario()),
                farmacia.getCnpj(),
                farmacia.getTelefone()
        );
    }
}