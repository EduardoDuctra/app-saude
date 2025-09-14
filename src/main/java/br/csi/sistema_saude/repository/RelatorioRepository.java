package br.csi.sistema_saude.repository;

import br.csi.sistema_saude.model.Relatorio;
import br.csi.sistema_saude.model.RelatorioId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelatorioRepository extends JpaRepository<Relatorio, RelatorioId> {

    List<Relatorio> findByUsuario_CodUsuario(int codUsuario);
}
