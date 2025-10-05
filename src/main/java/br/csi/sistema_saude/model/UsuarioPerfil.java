package br.csi.sistema_saude.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um objeto do Tipo UsuarioPerfil (dados pessoais)")

public class UsuarioPerfil {

    @Column(name = "nome")
    @NotBlank
    @Schema(description = "Nome do usuário. Exemplo: Eduardo. Não pode ser nulo")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    @NotBlank
    @Schema(description = "Sexo do usuário. Valores possíveis: M ou F")
    private Sexo sexo;


    @Column(name = "altura")
    @Schema(description = "Altura do usuário. Exemplo: 1.73")
    private double altura;

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank Sexo getSexo() {
        return sexo;
    }

    public void setSexo(@NotBlank Sexo sexo) {
        this.sexo = sexo;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}
