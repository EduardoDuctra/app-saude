package br.csi.sistema_saude.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um medicamento")

public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_medicamento")
    @Schema(description = "ID do medicamento")
    private int codMedicamento;

    @UuidGenerator
    @Schema(description = "UUID do medicamento")
    private UUID uuidMedicamento;

    @Column(name = "nome_medicamento")
    @NotBlank
    @Schema(description = "Nome do medicamento. Exemplo: Dipirona")
    private String nomeMedicamento;

    @Column(name = "duracao_tratamento")
    @Schema(description = "Duração do tratamento em dias. Exemplo: 4")
    private int duracaoTratamento;

    @Column(name = "data_inicio")
    @Schema(description = "Data inicial do tratamento. Exemplo: 05/10/2025")
    private LocalDate dataInicio;

    @Column(name = "dose_diaria")
    @Schema(description = "Quantidade de vezes ao dia. Exemplo: 2")
    private int doseDiaria;

    @ManyToOne
    @JoinColumn(name = "cod_usuario")
    @Schema(description = "Objeto Usuário")
    private Usuario usuario;

    public int getCodMedicamento() {
        return codMedicamento;
    }

    public void setCodMedicamento(int codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public UUID getUuidMedicamento() {
        return uuidMedicamento;
    }

    public void setUuidMedicamento(UUID uuidMedicamento) {
        this.uuidMedicamento = uuidMedicamento;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public int getDuracaoTratamento() {
        return duracaoTratamento;
    }

    public void setDuracaoTratamento(int duracaoTratamento) {
        this.duracaoTratamento = duracaoTratamento;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getDoseDiaria() {
        return doseDiaria;
    }

    public void setDoseDiaria(int doseDiaria) {
        this.doseDiaria = doseDiaria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}


