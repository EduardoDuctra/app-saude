package br.csi.sistema_saude.repository;

import br.csi.sistema_saude.model.RecolhimentoFarmacia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecolhimentoFarmaciaRepository extends JpaRepository<RecolhimentoFarmacia, Integer> {

    Optional<RecolhimentoFarmacia> findByRecolhimento_CodRecolhimentoAndFarmacia_CodFarmacia(int codRecolhimento, int codFarmacia);
}