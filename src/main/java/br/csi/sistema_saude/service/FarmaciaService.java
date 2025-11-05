package br.csi.sistema_saude.service;

import br.csi.sistema_saude.model.DTO.DadoFarmaciaDTO;
import br.csi.sistema_saude.model.DTO.RecolhimentoDTO;
import br.csi.sistema_saude.model.Farmacia;
import br.csi.sistema_saude.model.Recolhimento;
import br.csi.sistema_saude.model.StatusRecolhimento;
import br.csi.sistema_saude.repository.FarmaciaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FarmaciaService {

    private final FarmaciaRepository farmaciaRepository;

    public FarmaciaService(FarmaciaRepository farmaciaRepository) {
        this.farmaciaRepository = farmaciaRepository;
    }


    public Farmacia salvarFarmacia(Farmacia farmacia) {
        return farmaciaRepository.save(farmacia);
    }

    public List<DadoFarmaciaDTO> listarFarmacias() {

        List<Farmacia> farmacias = farmaciaRepository.findAll();
        List<DadoFarmaciaDTO> lista = new ArrayList<>();

        for (Farmacia farmacia : farmacias) {
            lista.add(new DadoFarmaciaDTO(farmacia));
        }

        return lista;
    }

    public Farmacia atualizarFarmacia(Farmacia farmacia) {
        Farmacia f = farmaciaRepository.findById(farmacia.getCodFarmacia())
                .orElseThrow(() -> new NoSuchElementException("Farmácia não encontrada"));


        f.getUsuario().getPerfil().setNome(farmacia.getUsuario().getPerfil().getNome());
        f.setTelefone(farmacia.getTelefone());


        if (f.getUsuario() != null && farmacia.getUsuario() != null) {

            if (farmacia.getUsuario().getConta().getSenha() != null &&
                    !farmacia.getUsuario().getConta().getSenha().isEmpty()) {
                f.getUsuario().getConta().setSenha(
                        new BCryptPasswordEncoder().encode(farmacia.getUsuario().getConta().getSenha())
                );
            }
        }


        return farmaciaRepository.save(f);
    }


    public Farmacia buscarPorUsuario(int codUsuario) {
        return farmaciaRepository.findByUsuario_CodUsuario(codUsuario)
                .orElseThrow(() -> new NoSuchElementException("Farmácia não encontrada para este usuário"));
    }


}
