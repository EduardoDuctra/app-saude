package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Farmacia;
import br.csi.sistema_saude.model.Recolhimento;
import br.csi.sistema_saude.model.StatusRecolhimento;
import br.csi.sistema_saude.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public record DadoFarmaciaDTO(Integer id,
                              String nome,
                              String cnpj,
                              String email,
                              String telefone,
                              String permissao
) {
    public DadoFarmaciaDTO(Farmacia farmacia) {
        this(
                farmacia.getCodFarmacia(),
                farmacia.getUsuario().getPerfil().getNome(),
                farmacia.getCnpj(),
                farmacia.getUsuario().getConta().getEmail(),
                farmacia.getTelefone(),
                farmacia.getUsuario().getConta().getPermissao()
        );
    }



}