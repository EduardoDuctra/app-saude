package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Usuario;



public record DadoUsuarioDTO(Integer id, String nome, String email, String permissao, Double altura) {
    public DadoUsuarioDTO(Usuario usuario) {
        this(usuario.getCodUsuario(),
                usuario.getPerfil().getNome(),
                usuario.getConta().getEmail(),
                usuario.getConta().getPermissao(),
                usuario.getPerfil().getAltura());
    }
}
