package br.csi.sistema_saude.controller;

import br.csi.sistema_saude.model.Dados;
import br.csi.sistema_saude.model.recordsSecurity.DadosAutenticacao;
import br.csi.sistema_saude.model.recordsSecurity.DadosTokenJWT;
import br.csi.sistema_saude.security.TokenServiceJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Segurança", description = "Path relacionado ao login e sergurança/validação")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenServiceJWT tokenServiceJWT;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenServiceJWT tokenServiceJWT) {
        this.authenticationManager = authenticationManager;
        this.tokenServiceJWT = tokenServiceJWT;
    }


    @PostMapping
    @Operation(summary = "Logar", description = "Faz a validação do login (usuário e senha)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosTokenJWT.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao validar", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Dados não encontrados", content = @Content)
    })
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try {

            System.out.println("Tentativa de login com email: " + dados.email());

            // Cria um token de autenticação do Spring Security com email e senha fornecidos
            // Autentica o usuário usando o AuthenticationManager
            Authentication autenticado = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            Authentication at = authenticationManager.authenticate(autenticado);

            // Obtém o usuário autenticado (objeto do Spring Security)
            User user = (User) at.getPrincipal();

            // Gera o token JWT para esse usuário
            String token = this.tokenServiceJWT.gerarToken(user); //200

            return ResponseEntity.ok().body(new DadosTokenJWT(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Usuário ou senha incorretos"); //401 não autorizado
        }
    }

}
