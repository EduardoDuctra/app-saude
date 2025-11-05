package br.csi.sistema_saude.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "farmacia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Farmacia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_farmacia")
    @Schema(description = "ID da farmácia")
    private int codFarmacia;


    @Column(name = "cnpj", unique = true, length = 18, nullable = false)
    @NotBlank
    @Schema(description = "CNPJ da farmácia")
    private String cnpj;

    @Column(name = "telefone", nullable = false)
    @NotBlank
    @Schema(description = "Telefone de contato da farmácia")
    private String telefone;

    @OneToOne
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "farmacia", cascade = CascadeType.ALL)
    private List<RecolhimentoFarmacia> recolhimentosAssociados = new ArrayList<>();


    public List<RecolhimentoFarmacia> getRecolhimentosAssociados() {
        return recolhimentosAssociados;
    }

    public void setRecolhimentosAssociados(List<RecolhimentoFarmacia> recolhimentosAssociados) {
        this.recolhimentosAssociados = recolhimentosAssociados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCodFarmacia() {
        return codFarmacia;
    }

    public void setCodFarmacia(int codFarmacia) {
        this.codFarmacia = codFarmacia;
    }


    public @NotBlank String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@NotBlank String cnpj) {
        this.cnpj = cnpj;
    }


    public @NotBlank String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank String telefone) {
        this.telefone = telefone;
    }


}

