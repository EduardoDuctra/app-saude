package br.csi.sistema_saude.controller;

import br.csi.sistema_saude.model.Dados;
import br.csi.sistema_saude.model.Usuario;
import br.csi.sistema_saude.service.DadosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/dados")
public class DadosController {
    private DadosService dadosService;

    public DadosController(DadosService dadosService) {
        this.dadosService = dadosService;
    }

    @GetMapping("/listar-dados")
    public ResponseEntity<List<Dados>> listarTodosDados() {
        List<Dados> dados = dadosService.listarDados();
        if (dados.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(dados); // 200
    }

    @GetMapping("/{codDado}")
    public ResponseEntity<Dados> buscarDado(@PathVariable Integer codDado) {
        Dados dados = this.dadosService.buscarDados(codDado);
        if(dados == null) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error
        }
        return ResponseEntity.ok(dados); //200
    }

    @GetMapping("/buscar-por-usuario/{uuidUsuario}")
    public ResponseEntity<List<Dados>> buscarDadosUsuario(@PathVariable Integer codUsuario) {
        List<Dados> dados = this.dadosService.buscarDadosUsuario(codUsuario);

        if (dados.isEmpty()) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error
        }
        return ResponseEntity.ok(dados); // 200 OK
    }

    @PostMapping("/salvar")
    public ResponseEntity salvarDados(@RequestBody Dados dados, UriComponentsBuilder uriBuilder) {
        this.dadosService.salvarDados(dados);
        URI uri = uriBuilder.path("/dados/{codDado}").buildAndExpand(dados.getCodDado()).toUri();
        return ResponseEntity.created(uri).build(); //200 OK
    }

    @PutMapping("/atualizar")
    public ResponseEntity atualizarDados(@RequestBody Dados dados) {
        this.dadosService.atualizarDados(dados);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/deletar/{codDado}")
    public ResponseEntity excluirDados(@PathVariable Integer codDado) {
        this.dadosService.excluirDados(codDado);
        return ResponseEntity.noContent().build();
    }
}
