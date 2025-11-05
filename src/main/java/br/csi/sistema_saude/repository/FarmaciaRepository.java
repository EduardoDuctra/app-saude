package br.csi.sistema_saude.repository;

import br.csi.sistema_saude.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmaciaRepository extends JpaRepository <Farmacia, Integer>{

    Optional<Farmacia> findById(Integer codFarmacia);

    Optional<Farmacia> findByUsuario_CodUsuario(int codUsuario);

}
