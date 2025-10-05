package br.csi.sistema_saude.controller;


import br.csi.sistema_saude.model.Dados;
import br.csi.sistema_saude.model.Medicamento;
import br.csi.sistema_saude.model.Usuario;
import br.csi.sistema_saude.service.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/medicamentos")
@Tag(name = "Mediacamentos", description = "Path relacionado aos medicamentos")
public class MedicamentoController {

    private MedicamentoService medicamentoService;
    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @Operation(summary = "Listar todo os medicamentos", description = "Lista todo os medicamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medicamentos listados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao listar medicamentos", content = @Content)
    })
    @GetMapping("/listar-medicamentos")
    public ResponseEntity<List<Medicamento>> listarTodosMedicamentod() {
        List <Medicamento> medicamentos = medicamentoService.buscarMedicamentos();
        if (medicamentos.isEmpty()) {
            throw new NoSuchElementException();
        }
        return ResponseEntity.ok(medicamentos); //200 OK
    }

    @GetMapping("/{codMedicamento}")
    @Operation(summary = "Listar um medicamentos a partir do seu código", description = "Lista um medicamentos a partir do seu código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medicamento listado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao listar medicamento", content = @Content)
    })
    public ResponseEntity <Medicamento> buscarMedicamento(@PathVariable Integer codMedicamento) {

        Medicamento medicamento = medicamentoService.buscarMedicamento(codMedicamento);
        if (medicamento == null) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error
        }
        return ResponseEntity.ok(medicamento); // 200

    }

    @GetMapping("/buscar-por-usuario/{codUsuario}")
    @Operation(summary = "Listar os medicamentos a partir do código do usuário", description = "Lista os medicamentos a partir do código do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medicamentos listados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao listar medicamentos do usuário", content = @Content)
    })
    public ResponseEntity<List<Medicamento>> buscarMedicamentosUsuario(@PathVariable Integer codUsuario) {
        List <Medicamento> medicamentos = this.medicamentoService.buscarMedicamentoUsuario(codUsuario);
        return ResponseEntity.ok(medicamentos);
    }

    @PostMapping("/salvar")
    @Operation(summary = "Criar um novo medicamento", description = "Cria um novo medicamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medicamento salvo com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao salvar medicamento", content = @Content)
    })
    public ResponseEntity salvarMedicamento(@RequestBody Medicamento medicamento, UriComponentsBuilder uriBuilder) {
        this.medicamentoService.salvarMedicamento(medicamento);
        URI uri = uriBuilder.path("/medicamentos/{id}").buildAndExpand(medicamento.getCodMedicamento()).toUri();
        return ResponseEntity.created(uri).body(medicamento);
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar um  medicamento", description = "Atualiza um medicamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medicamento atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar medicamento", content = @Content)
    })
    public ResponseEntity atualizarMedicamento(@RequestBody Medicamento medicamento) {
        this.medicamentoService.atualizarMedicamento(medicamento);
        return ResponseEntity.ok(medicamento);
    }

    @DeleteMapping("/deletar/{codMedicamento}")
    @Operation(summary = "Deletar um medicamento", description = "Deleta um medicamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medicamento deletado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dados.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar medicamento", content = @Content)
    })
    public ResponseEntity excluirMedicamento(@PathVariable Integer codMedicamento) {
        this.medicamentoService.excluirMedicamento(codMedicamento);
        return ResponseEntity.noContent().build();
    }


}
