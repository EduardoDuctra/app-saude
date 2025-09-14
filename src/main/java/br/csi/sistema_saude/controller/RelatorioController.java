package br.csi.sistema_saude.controller;

import br.csi.sistema_saude.model.Relatorio;
import br.csi.sistema_saude.model.RelatorioId;
import br.csi.sistema_saude.model.Usuario;
import br.csi.sistema_saude.service.RelatorioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    // Salvar um relatório
    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity salvarRelatorio(@RequestBody @Valid Relatorio relatorio, UriComponentsBuilder uriBuilder) {
        relatorioService.salvarRelatorio(relatorio);
        URI uri = uriBuilder
                .path("/relatorios/{codUsuario}/{codDado}/{data}")
                .buildAndExpand(relatorio.getId().getCodUsuario(),
                        relatorio.getId().getCodDado(),
                        relatorio.getId().getData())
                .toUri();
        return ResponseEntity.created(uri).body(relatorio); //201 criado
    }

    // Listar todos os relatórios
    @GetMapping("listar-relatorios")
    public ResponseEntity<List<Relatorio>> listarRelatorios() {
        List<Relatorio> relatorios = relatorioService.listarRelatorios();
        if (relatorios.isEmpty()) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error
        }
        return ResponseEntity.ok(relatorios); //200
    }

    // Buscar um relatório pelo ID composto
    @GetMapping("/{codUsuario}/{codDado}/{data}")
    public ResponseEntity<Relatorio> buscarRelatorio(
            @PathVariable int codUsuario,
            @PathVariable int codDado,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ) {
        RelatorioId id = new RelatorioId(codUsuario, codDado, data);
        Relatorio relatorio = relatorioService.buscarRelatorio(id);

        if (relatorio == null) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error
        }

        return ResponseEntity.ok(relatorio); //  200
    }

    // Excluir um relatório pelo ID composto
    @DeleteMapping("/{codUsuario}/{codDado}/{data}")
    public ResponseEntity excluirRelatorio(
            @PathVariable int codUsuario,
            @PathVariable int codDado,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ) {
        RelatorioId id = new RelatorioId(codUsuario, codDado, data);
        relatorioService.excluirRelatorio(id);
        return ResponseEntity.noContent().build(); //204 sem conteudo
    }

    // Listar relatorio de um tipo de dado especifico
    //http://localhost:8080/sistema-saude/relatorios/listar-por-tipo?codUsuario=2&tipoDado=glicose
    @GetMapping("/listar-por-tipo")
    public ResponseEntity<List<Double>> listarTipoDado(
            @RequestParam int codUsuario,
            @RequestParam String tipoDado) {

        List<Double> valores = relatorioService.listarValoresPorUsuarioETipo(codUsuario, tipoDado);

        if (valores.isEmpty()) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error
        }
        return ResponseEntity.ok(valores); //200
    }
}
