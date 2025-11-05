package br.csi.sistema_saude.repository;

import br.csi.sistema_saude.model.Recolhimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecolhimentoRepository extends JpaRepository<Recolhimento, Integer> {

    @Query("SELECT rf.recolhimento FROM RecolhimentoFarmacia rf WHERE rf.farmacia.codFarmacia = :codFarmacia AND rf.status = 'PENDENTE'")
    List<Recolhimento> findPendentesByFarmacia(@Param("codFarmacia") int codFarmacia);




}
