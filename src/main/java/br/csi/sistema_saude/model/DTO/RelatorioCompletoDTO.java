package br.csi.sistema_saude.model.DTO;
import br.csi.sistema_saude.model.Relatorio;

import java.time.LocalDate;

public record RelatorioCompletoDTO(
        LocalDate data,
        Double peso,
        Integer glicose,
        Integer colesterolHDL,
        Integer colesterolVLDL,
        Integer creatina,
        Integer trigliceridio
) {
    public RelatorioCompletoDTO(Relatorio relatorio) {
        this(
                relatorio.getId().getData(),
                relatorio.getDados().getPeso(),
                relatorio.getDados().getGlicose(),
                relatorio.getDados().getColesterolHDL(),
                relatorio.getDados().getColesterolVLDL(),
                relatorio.getDados().getCreatina(),
                relatorio.getDados().getTrigliceridio()
        );
    }
}