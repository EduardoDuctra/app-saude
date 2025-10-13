package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Usuario;

public record DadoUsuario(Integer id, String email) {
    public DadoUsuario(Usuario usuario) {
        this(usuario.getCodUsuario(),
                usuario.getConta().getEmail());
    }
}
