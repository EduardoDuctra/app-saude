package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Usuario;



public record DadoUsuarioDTO(Conta conta, Perfil perfil) {
    public record Conta(String email, String senha, String permissao) {}
    public record Perfil(String nome, String sexo, Double altura) {}

    public DadoUsuarioDTO(Usuario usuario) {
        this(
                new Conta(usuario.getConta().getEmail(), "", usuario.getConta().getPermissao()),
                new Perfil(
                        usuario.getPerfil().getNome(),
                        usuario.getPerfil().getSexo().name(),
                        usuario.getPerfil().getAltura()
                )
        );
    }
}