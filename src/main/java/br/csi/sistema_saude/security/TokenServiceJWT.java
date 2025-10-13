package br.csi.sistema_saude.security;

import br.csi.sistema_saude.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceJWT {

    public String gerarToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256("poow2");
        return JWT.create()
                .withIssuer("Api de saude")
                .withSubject(user.getUsername())
                .withClaim("ROLE", user.getAuthorities().stream().toList().get(0).toString())
                .withExpiresAt(dataExpiracao())
                .sign(algorithm);
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}