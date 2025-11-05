package br.csi.sistema_saude.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recolhimento_farmacia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa o recolhimento aceito de medicamentos por uma farmácia x")
public class RecolhimentoFarmacia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da associação Recolhimento-Farmácia")
    private int id;

    @ManyToOne
    @JoinColumn(name = "cod_recolhimento", nullable = false)
    @Schema(description = "Recolhimento associado à farmácia")
    private Recolhimento recolhimento;

    @ManyToOne
    @JoinColumn(name = "cod_farmacia", nullable = false)
    @Schema(description = "Farmácia associada ao recolhimento")
    private Farmacia farmacia;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Schema(description = "Status da farmácia em relação ao recolhimento (PENDENTE, CANCELADO, CONCLUIDO.)")
    private StatusRecolhimento status = StatusRecolhimento.PENDENTE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recolhimento getRecolhimento() {
        return recolhimento;
    }

    public void setRecolhimento(Recolhimento recolhimento) {
        this.recolhimento = recolhimento;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public StatusRecolhimento getStatus() {
        return status;
    }

    public void setStatus(StatusRecolhimento status) {
        this.status = status;
    }
}
