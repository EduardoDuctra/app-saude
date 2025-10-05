package br.csi.sistema_saude.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table (name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um Usuário")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_usuario")
    @Schema(description = "ID do usuário")
    private int codUsuario;

    @UuidGenerator
    @Schema(description = "UUID do usuário")
    private UUID uuidUsuario;

    @Embedded
    @Schema(description = "Objeto que representa a conta do usuário. Recebe UsuarioConta")
    private UsuarioConta conta;

    @Embedded
    @Schema(description = "Objeto que representa a perfil do usuário. Recebe UsuarioPerfil")
    private UsuarioPerfil perfil;

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public UUID getUuidUsuario() {
        return uuidUsuario;
    }

    public void setUuidUsuario(UUID uuidUsuario) {
        this.uuidUsuario = uuidUsuario;
    }

    public UsuarioConta getConta() {
        return conta;
    }

    public void setConta(UsuarioConta conta) {
        this.conta = conta;
    }

    public UsuarioPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(UsuarioPerfil perfil) {
        this.perfil = perfil;
    }
}
