package br.csi.sistema_saude.model.DTO;

import br.csi.sistema_saude.model.Dados;

public record DadosDTO(
        int codDado,
        double peso,
        int glicose,
        int colesterolHDL,
        int colesterolVLDL,
        int creatina,
        int trigliceridio
) {

    public DadosDTO(Dados dados) {
        this(
                dados.getCodDado(),
                dados.getPeso(),
                dados.getGlicose(),
                dados.getColesterolHDL(),
                dados.getColesterolVLDL(),
                dados.getCreatina(),
                dados.getTrigliceridio()
        );
    }

}
