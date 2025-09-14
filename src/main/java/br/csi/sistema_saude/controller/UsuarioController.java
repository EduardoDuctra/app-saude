package br.csi.sistema_saude.controller;

import br.csi.sistema_saude.model.Usuario;
import br.csi.sistema_saude.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = this.usuarioService.listarUsuarios();
        if (usuarios.isEmpty()) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error

        }
        return ResponseEntity.ok(usuarios); // 200
    }


    @GetMapping("/{codUsuario}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Integer codUsuario) {
        Usuario usuario = this.usuarioService.buscarUsuario(codUsuario);
        if (usuario == null) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error

        }
        return ResponseEntity.ok(usuario); // 200
    }

    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity salvarUsuario(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {
        this.usuarioService.salvarUsuario(usuario);
        URI uri = uriBuilder.path("/usuario/{codUsuario}").buildAndExpand(usuario.getCodUsuario()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarUsuario(@RequestBody @Valid Usuario usuario) {
        this.usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/deletar/{codUsuario}")
    public ResponseEntity deleteUsuario(@PathVariable Integer codUsuario) {
        this.usuarioService.excluirUsuario(codUsuario);
        return ResponseEntity.noContent().build();
    }
}
