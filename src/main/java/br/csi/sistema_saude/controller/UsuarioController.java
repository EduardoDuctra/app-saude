package br.csi.sistema_saude.controller;

import br.csi.sistema_saude.model.Dados;
import br.csi.sistema_saude.model.Relatorio;
import br.csi.sistema_saude.model.Usuario;
import br.csi.sistema_saude.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Path relacionado aos usuários")
public class UsuarioController {

    private UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    @GetMapping("/listar-usuarios")
    @Operation(summary = "Listar todos os usuário", description = "Lista todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuários listados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Usuários invalidos", content = @Content)
    })
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = this.usuarioService.listarUsuarios();
        if (usuarios.isEmpty()) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error

        }
        return ResponseEntity.ok(usuarios); // 200
    }


    @GetMapping("/{codUsuario}")
    @Operation(summary = "Listar usuário pelo código dele", description = "Lista usuário pelo código dele")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Código invalido", content = @Content)
    })
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Integer codUsuario) {
        Usuario usuario = this.usuarioService.buscarUsuario(codUsuario);
        if (usuario == null) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error

        }
        return ResponseEntity.ok(usuario); // 200
    }

    @PostMapping("/salvar")
    @Transactional
    @Operation(summary = "Criar novo usuário", description = "Cria novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao criar usuário", content = @Content)
    })
    public ResponseEntity salvarUsuario(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {
        this.usuarioService.salvarUsuario(usuario);
        URI uri = uriBuilder.path("/usuario/{codUsuario}").buildAndExpand(usuario.getCodUsuario()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/atualizar")
    @Transactional
    @Operation(summary = "Atualizar um usuário", description = "Atualiza um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar usuário", content = @Content)
    })
    public ResponseEntity atualizarUsuario(@RequestBody @Valid Usuario usuario) {
        this.usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/deletar/{codUsuario}")
    @Operation(summary = "Deletar um usuário", description = "Deleta um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário deletado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar usuário", content = @Content)
    })
    public ResponseEntity deleteUsuario(@PathVariable Integer codUsuario) {
        this.usuarioService.excluirUsuario(codUsuario);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    @Operation(summary = "Validar do login", description = "Validação com o banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login efetuado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao loggar", content = @Content)
    })
    public ResponseEntity<?> login(@RequestParam String email,
                                   @RequestParam String senha,
                                   HttpSession session) {

        Usuario usuario = usuarioService.validarUsuario(email, senha);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário ou senha inválidos");
        }

        session.setAttribute("usuarioLogado", usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{codUsuario}/imc")
    @Operation(summary = "Calcular o IMC de um usuário", description = "Calcula o IMC de um usuário a partir do código do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cálculo efetuado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao calcular IMC", content = @Content)
    })
    public ResponseEntity<?> calcularIMC(@PathVariable Integer codUsuario) {
        Usuario usuario = usuarioService.buscarUsuario(codUsuario);
        if (usuario == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }

        // Busca os relatórios do usuário
        List<Relatorio> relatoriosDoUsuario = usuarioService.buscarRelatoriosPorUsuario(usuario);

        try {
            double imc = usuarioService.calcularIMC(usuario, relatoriosDoUsuario);
            return ResponseEntity.ok("IMC do usuário " + usuario.getPerfil().getNome() + ": " + imc);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //validar se está buscando o email certo.
    @GetMapping("/buscar-email")
    @Operation(summary = "Buscar um usuário a partir do seu email", description = "Busca um usuário no banco de dados a partir do seu email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (usuario == null) {

            throw new NoSuchElementException("Usuário não encontrado com o email: " + email);
        }


        return ResponseEntity.ok(usuario);
    }



}
