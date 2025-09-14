package br.csi.sistema_saude.service;


import br.csi.sistema_saude.model.Relatorio;
import br.csi.sistema_saude.model.RelatorioId;
import br.csi.sistema_saude.repository.RelatorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;

    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    public void salvarRelatorio(Relatorio relatorio) {
        relatorioRepository.save(relatorio);
    }

    public List<Relatorio> listarRelatorios() {
        return relatorioRepository.findAll();
    }


    public Relatorio buscarRelatorio(RelatorioId id) {
        return relatorioRepository.findById(id).orElse(null);
    }

    public void excluirRelatorio(RelatorioId id) {
        relatorioRepository.deleteById(id);
    }

    public List<Double> listarValoresPorUsuarioETipo(int codUsuario, String tipoDado) {
        return relatorioRepository.findByUsuario_CodUsuario(codUsuario)
                .stream()
                .map(rel -> {
                    if (rel.getDados() == null) {
                        return null;
                    }
                    switch (tipoDado.toLowerCase()) {
                        case "glicose":
                            return (double) rel.getDados().getGlicose();
                        case "colesterolhdl":
                            return (double) rel.getDados().getColesterolHDL();
                        case "colesterolvldl":
                            return (double) rel.getDados().getColesterolVLDL();
                        case "peso":
                            return rel.getDados().getPeso();
                        case "creatina":
                            return (double) rel.getDados().getCreatina();
                        case "trigliceridio":
                            return (double) rel.getDados().getTrigliceridio();
                        default:
                            throw new IllegalArgumentException("Tipo de dado inválido: " + tipoDado);
                    }
                })
                .filter(value -> value != null)
                .toList();
    }

}
