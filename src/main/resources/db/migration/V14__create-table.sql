
DROP TABLE IF EXISTS lista_de_farmacias;

ALTER TABLE recolhimento
DROP COLUMN IF EXISTS cod_farmacia_aceitou;


CREATE TABLE IF NOT EXISTS recolhimento_farmacia (
    id SERIAL PRIMARY KEY,
    cod_recolhimento INT NOT NULL,
    cod_farmacia INT NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDENTE', 'CONCLUIDO', 'CANCELADO')),
    CONSTRAINT fk_recolhimento FOREIGN KEY (cod_recolhimento) REFERENCES recolhimento(cod_recolhimento),
    CONSTRAINT fk_farmacia FOREIGN KEY (cod_farmacia) REFERENCES farmacia(cod_farmacia)
);
