package br.csi.sistema_saude.controller;


import br.csi.sistema_saude.model.DTO.DadoFarmaciaDTO;
import br.csi.sistema_saude.model.DTO.DadoUsuarioDTO;
import br.csi.sistema_saude.model.Farmacia;
import br.csi.sistema_saude.model.Sexo;
import br.csi.sistema_saude.model.Usuario;
import br.csi.sistema_saude.model.UsuarioPerfil;
import br.csi.sistema_saude.service.FarmaciaService;
import br.csi.sistema_saude.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import br.csi.sistema_saude.service.FarmaciaService;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/farmacia")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Farmácia", description = "Path relacionado as farmácias")
public class FarmaciaController {

    private final FarmaciaService farmaciaService;
    private final UsuarioService usuarioService;

    public FarmaciaController(FarmaciaService farmaciaService, UsuarioService usuarioService) {
        this.farmaciaService = farmaciaService;
        this.usuarioService = usuarioService;
    }


    //URL púlbica
    @PostMapping("/salvar")
    @Transactional
    @Operation(summary = "Criar novo usuário (farmárica)", description = "Cadastra um novo usuário (farmácia) ao banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Farmácia criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Farmacia.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao criar usuário", content = @Content)
    })
    public ResponseEntity<Farmacia> salvarFarmacia(@RequestBody @Valid Farmacia farmacia, UriComponentsBuilder uriBuilder) {

        //crio o usuario
        Usuario usuario = farmacia.getUsuario();

        if (usuario.getPerfil() == null) {
            usuario.setPerfil(new UsuarioPerfil());
            usuario.getPerfil().setSexo(Sexo.I);
            usuario.getPerfil().setAltura(0.0);
            usuario.getPerfil().setNome(farmacia.getUsuario().getPerfil().getNome());


        }


        //salvo esse usuario
        this.usuarioService.salvarUsuario(usuario);

        //passo esse usuario para farmacia pq farmacia é um usuario
        farmacia.setUsuario(usuario);
        Farmacia farmaciaSalva = farmaciaService.salvarFarmacia(farmacia);

        URI uri = uriBuilder.path("/farmacia/{codFarmacia}").buildAndExpand(farmaciaSalva.getCodFarmacia()).toUri();
        return ResponseEntity.created(uri).body(farmaciaSalva);
    }

    @GetMapping("/listar-farmacias")
    @Operation(summary = "Listar todas as farmácias", description = "Retorna uma lista com todas as farmácias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Farmacia.class))),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar dados", content = @Content),
    })
    public ResponseEntity<List<DadoFarmaciaDTO>> listarFarmacias() {
        List<DadoFarmaciaDTO> usuarios = this.farmaciaService.listarFarmacias();

        if (usuarios.isEmpty()) {
            throw new NoSuchElementException("Nenhuma farmácia encontrada"); // chama o método do Tratador de Error
        }

        return ResponseEntity.ok(usuarios); // 200
    }



    @PutMapping("/atualizar-farmacia")
    @Transactional
    @Operation(summary = "Atualizar uma farmácia (usuário)", description = "Recebe uma Farmacia e atualiza seus dados no banco de dados. Já associado ao Usuário autenticado da sessão.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Farmacia.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar usuário", content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar dados", content = @Content),
    })
    public ResponseEntity<DadoFarmaciaDTO> atualizarUsuarioFarmacia(@RequestBody @Valid Farmacia farmacia) {

        // Retorna o usuário logado pelo email
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario logado = usuarioService.buscarPorEmail(email);

        if (logado.getPerfil() == null) {
            logado.setPerfil(new UsuarioPerfil());
        }

        logado.getPerfil().setSexo(Sexo.I);
        logado.getPerfil().setAltura(0.0);
        logado.getPerfil().setNome(farmacia.getUsuario().getPerfil().getNome());


        farmacia.setUsuario(logado);

        Farmacia atualizada = farmaciaService.atualizarFarmacia(farmacia);


        return ResponseEntity.ok(new DadoFarmaciaDTO(atualizada));
    }

    @GetMapping("/perfil")
    @Operation(summary = "Busca a farmacia logada", description = "Retorna uma farmácia logado, buscando pelo email no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Farmácia encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Farmacia.class))),
            @ApiResponse(responseCode = "404", description = "Erro ao encontrar farmácia", content = @Content),
    })
    public ResponseEntity<DadoFarmaciaDTO> carregarFarmaciaLogada() {


            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            Usuario logado = usuarioService.buscarPorEmail(email);


            Farmacia farmacia = farmaciaService.buscarPorUsuario(logado.getCodUsuario());

            if (farmacia == null) {
                throw new NoSuchElementException("Usuário não encontrado");
            }

            DadoFarmaciaDTO dto = new DadoFarmaciaDTO(farmacia);
            return ResponseEntity.ok(dto);

    }

}



