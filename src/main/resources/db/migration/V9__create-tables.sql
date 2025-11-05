CREATE TABLE farmacia (
                          cod_farmacia SERIAL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          cnpj VARCHAR(18) UNIQUE NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,
                          telefone VARCHAR(50) NOT NULL,
                          cod_usuario INT NOT NULL,

                          CONSTRAINT fk_usuario_farmacia FOREIGN KEY (cod_usuario)
                              REFERENCES usuario(cod_usuario)
);


CREATE TABLE recolhimento (
                              cod_recolhimento SERIAL PRIMARY KEY,
                              cod_medicamento INT NOT NULL,
                              cod_farmacia INT NOT NULL,
                              status VARCHAR(20) NOT NULL CHECK (status IN ('PENDENTE', 'CONCLUIDO', 'CANCELADO')),
                              email_cliente VARCHAR(100) NOT NULL,
                              CONSTRAINT fk_recolhimento_medicamento FOREIGN KEY (cod_medicamento)
                                  REFERENCES medicamento(cod_medicamento),
                              CONSTRAINT fk_recolhimento_farmacia FOREIGN KEY (cod_farmacia)
                                  REFERENCES farmacia(cod_farmacia)
);
