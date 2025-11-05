ALTER TABLE recolhimento
    ADD COLUMN cod_farmacia_aceitou INT;

ALTER TABLE recolhimento
    ADD CONSTRAINT fk_recolhimento_farmacia_aceitou
        FOREIGN KEY (cod_farmacia_aceitou) REFERENCES farmacia(cod_farmacia);