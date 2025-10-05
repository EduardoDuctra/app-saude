package br.csi.sistema_saude.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um objeto do Tipo UsuarioConta (email e senha)")

public class UsuarioConta {

    @Column(name = "email")
    @NotBlank
    @Email (message = "Email inválido")
    @Schema(description = "Email do usuário. Exemplo: usuario@email.com. Não pode ser nulo")
    private String email;

    @Column(name = "senha")
    @NotBlank
    @Schema(description = "Senha do usuário. Não pode ser nulo")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
