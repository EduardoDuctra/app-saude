package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Usuario;

public record UsuarioPerfilDTO(Double altura,
                               String sexo) {
    public UsuarioPerfilDTO(Usuario usuario) {
        this(
                usuario.getPerfil().getAltura(),
                usuario.getPerfil().getSexo().name()
        );
    }
}
