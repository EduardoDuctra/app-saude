package br.csi.sistema_saude.service;


import br.csi.sistema_saude.model.DTO.RelatorioCompletoDTO;
import br.csi.sistema_saude.model.DTO.RelatorioDTO;
import br.csi.sistema_saude.model.Dados;
import br.csi.sistema_saude.model.Relatorio;
import br.csi.sistema_saude.model.RelatorioId;
import br.csi.sistema_saude.model.Usuario;
import br.csi.sistema_saude.repository.DadosRepository;
import br.csi.sistema_saude.repository.RelatorioRepository;
import br.csi.sistema_saude.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final UsuarioRepository usuarioRepository;
    private final DadosRepository dadosRepository;

    public RelatorioService(RelatorioRepository relatorioRepository, UsuarioRepository usuarioRepository, DadosRepository dadosRepository) {
        this.relatorioRepository = relatorioRepository;
        this.usuarioRepository = usuarioRepository;
        this.dadosRepository = dadosRepository;
    }

    public void salvarRelatorio(Relatorio relatorio) {

        Usuario usuario = usuarioRepository.findById(relatorio.getUsuario().getCodUsuario())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        Dados dado = dadosRepository.findById(relatorio.getDados().getCodDado())
                .orElseThrow(() -> new NoSuchElementException("Dado não encontrado"));

        relatorio.setUsuario(usuario);
        relatorio.setDados(dado);

        relatorioRepository.save(relatorio);
    }


    public Relatorio buscarRelatorio(RelatorioId id) {
        return relatorioRepository.findById(id).orElse(null);
    }

    public void excluirRelatorio(RelatorioId id) {
        relatorioRepository.deleteById(id);
    }

    public List<RelatorioCompletoDTO> listarTodosRelatoriosPorUsuario(int codUsuario) {
        List<Relatorio> relatorios = relatorioRepository.findByUsuario_CodUsuario(codUsuario);
        List<RelatorioCompletoDTO> dtoList = new ArrayList<>();

        for (Relatorio rel : relatorios) {
            if (rel.getDados() != null) {
                RelatorioCompletoDTO dto = new RelatorioCompletoDTO(
                        rel.getId().getData(),
                        rel.getDados().getPeso(),
                        rel.getDados().getGlicose(),
                        rel.getDados().getColesterolHDL(),
                        rel.getDados().getColesterolVLDL(),
                        rel.getDados().getCreatina(),
                        rel.getDados().getTrigliceridio()
                );

                dtoList.add(dto);
            }
        }

        return dtoList;
    }


    public List<RelatorioDTO> listarRelatoriosPorUsuarioETipo(int codUsuario, String tipoDado) {

        return relatorioRepository.findByUsuario_CodUsuario(codUsuario)
                .stream()
                .map(rel -> {
                    Double valor;

                    // pega o valor correto de acordo com o tipo
                    switch (tipoDado.toLowerCase()) {
                        case "glicose":
                            valor = rel.getDados().getGlicose().doubleValue();
                            break;
                        case "colesterolhdl":
                            valor = rel.getDados().getColesterolHDL().doubleValue();
                            break;
                        case "colesterolvldl":
                            valor = rel.getDados().getColesterolVLDL().doubleValue();
                            break;
                        case "peso":
                            valor = rel.getDados().getPeso();
                            break;
                        case "creatina":
                            valor = rel.getDados().getCreatina().doubleValue();
                            break;
                        case "trigliceridio":
                            valor = rel.getDados().getTrigliceridio().doubleValue();
                            break;
                        default:
                            throw new NoSuchElementException("Tipo de dado inválido: " + tipoDado);
                    }

                    // passa a data do relatório e o valor no construtor do record
                    return new RelatorioDTO(rel, valor);
                })
                .toList();
    }

}
