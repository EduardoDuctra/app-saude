package br.csi.sistema_saude.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recolhimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa o recolhimento de medicamentos por uma farmácia")
public class Recolhimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_recolhimento")
    @Schema(description = "ID do recolhimento")
    private int codRecolhimento;

    @ManyToOne
    @JoinColumn(name = "cod_medicamento", nullable = false)
    @Schema(description = "Medicamento associado ao recolhimento")
    private Medicamento medicamento;


    @Column(name = "email_cliente", nullable = false)
    @Schema(description = "E-mail do usuário associado ao medicamento")
    private String emailCliente;

    @OneToMany(mappedBy = "recolhimento", cascade = CascadeType.ALL)
    private List<RecolhimentoFarmacia> farmaciasAssociadas = new ArrayList<>();

    public List<RecolhimentoFarmacia> getFarmaciasAssociadas() {
        return farmaciasAssociadas;
    }

    public void setFarmaciasAssociadas(List<RecolhimentoFarmacia> farmaciasAssociadas) {
        this.farmaciasAssociadas = farmaciasAssociadas;
    }

    public int getCodRecolhimento() {
        return codRecolhimento;
    }

    public void setCodRecolhimento(int codRecolhimento) {
        this.codRecolhimento = codRecolhimento;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
}
