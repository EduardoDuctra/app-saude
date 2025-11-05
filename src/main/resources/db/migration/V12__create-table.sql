CREATE TABLE IF NOT EXISTS lista_de_farmacias (
                                                  cod_recolhimento INT NOT NULL,
                                                  cod_farmacia INT NOT NULL,
                                                  PRIMARY KEY (cod_recolhimento, cod_farmacia),
    FOREIGN KEY (cod_recolhimento) REFERENCES recolhimento(cod_recolhimento),
    FOREIGN KEY (cod_farmacia) REFERENCES farmacia(cod_farmacia)
    );