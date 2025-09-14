package br.csi.sistema_saude.controller;


import br.csi.sistema_saude.model.Dados;
import br.csi.sistema_saude.model.Medicamento;
import br.csi.sistema_saude.model.Usuario;
import br.csi.sistema_saude.service.MedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private MedicamentoService medicamentoService;
    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @GetMapping("/listar-medicamentos")
    public ResponseEntity<List<Medicamento>> listarTodosMedicamentod() {
        List <Medicamento> medicamentos = medicamentoService.buscarMedicamentos();
        if (medicamentos.isEmpty()) {
            throw new NoSuchElementException();
        }
        return ResponseEntity.ok(medicamentos); //200 OK
    }

    @GetMapping("/{codMedicamento}")
    public ResponseEntity <Medicamento> buscarMedicamento(@PathVariable Integer codMedicamento) {

        Medicamento medicamento = medicamentoService.buscarMedicamento(codMedicamento);
        if (medicamento == null) {
            throw new NoSuchElementException(); //chama o metodo NoSuchElementException da classe Tratador de Error
        }
        return ResponseEntity.ok(medicamento); // 200

    }

    @GetMapping("/buscar-por-usuario/{codUsuario}")
    public ResponseEntity<List<Medicamento>> buscarMedicamentosUsuario(@PathVariable Integer codUsuario) {
        List <Medicamento> medicamentos = this.medicamentoService.buscarMedicamentoUsuario(codUsuario);
        return ResponseEntity.ok(medicamentos);
    }

    @PostMapping("/salvar")
    public ResponseEntity salvarMedicamento(@RequestBody Medicamento medicamento, UriComponentsBuilder uriBuilder) {
        this.medicamentoService.salvarMedicamento(medicamento);
        URI uri = uriBuilder.path("/medicamentos/{id}").buildAndExpand(medicamento.getCodMedicamento()).toUri();
        return ResponseEntity.created(uri).body(medicamento);
    }

    @PutMapping("/atualizar")
    public ResponseEntity atualizarMedicamento(@RequestBody Medicamento medicamento) {
        this.medicamentoService.atualizarMedicamento(medicamento);
        return ResponseEntity.ok(medicamento);
    }

    @DeleteMapping("/deletar/{codMedicamento}")
    public ResponseEntity excluirMedicamento(@PathVariable Integer codMedicamento) {
        this.medicamentoService.excluirMedicamento(codMedicamento);
        return ResponseEntity.noContent().build();
    }


}
