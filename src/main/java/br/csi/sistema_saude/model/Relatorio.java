package br.csi.sistema_saude.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "relatorio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um relatório")

public class Relatorio {

    @EmbeddedId
    @Schema(description = "Chave primária. Recebe um objeto do tipo RelatorioId")
    private RelatorioId id;

    @ManyToOne
    @MapsId("codUsuario")
    @JoinColumn(name = "cod_usuario")
    @Schema(description = "Objeto Usuário")
    private Usuario usuario;

    @ManyToOne
    @MapsId("codDado")
    @JoinColumn(name = "cod_dado")
    @Schema(description = "Objeto Dados")
    private Dados dados;

    public RelatorioId getId() {
        return id;
    }

    public void setId(RelatorioId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Dados getDados() {
        return dados;
    }

    public void setDados(Dados dados) {
        this.dados = dados;
    }
}

