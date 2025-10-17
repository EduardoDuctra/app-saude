package br.csi.sistema_saude.service;

import br.csi.sistema_saude.model.Usuario;
import br.csi.sistema_saude.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByContaEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário ou senha incorretos");
        }

        // Define a autoridade padrão de todos os usuários. Todos meus usuários tem a mesma autoridade no programa
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        // Cria o User do Spring Security com email, senha e autoridade
        //cria objeto User do spring security
        //passa o nome de usuario
        //passa a senha
        //passa o role/autoridade
        //User implementa UserDetails, que é usado pelo Spring Security para autenticação e autorização
        return User.builder()
                .username(usuario.getConta().getEmail())
                .password(usuario.getConta().getSenha())
                .authorities(Collections.singletonList(authority))
                .build();
    }
}
