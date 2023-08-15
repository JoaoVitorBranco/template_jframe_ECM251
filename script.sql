-- cria um usuário <RECOMENDÁVEL MUDAR O NOME>
CREATE USER 'aula_14'@'localhost' IDENTIFIED BY 'aula_14';
GRANT ALL PRIVILEGES ON * . * TO 'aula_14'@'localhost';
FLUSH PRIVILEGES;

-- cria o database <MUDAR O NOME>
CREATE DATABASE AULA_14;
USE AULA_14;

-- cria a tabela <RECOMENDÁVEL MUDAR O NOME>
CREATE TABLE LOGIN_SENHA
(
id SMALLINT UNSIGNED NOT NULL,
username VARCHAR(60) NOT NULL,
senha VARCHAR(60) NOT NULL,
PRIMARY KEY (id) -- indica qual será a chave primária
) ENGINE = InnoDB;

INSERT INTO LOGIN_SENHA VALUES
(0, 'Joao', 'Joao123'),
(1, 'Pedro', 'Pedro123'),
(2, 'Marcos', 'Marcos123'); 
SELECT * FROM LOGIN_SENHA;

CREATE TABLE DADOS
(
id SMALLINT UNSIGNED NOT NULL,
final_grade VARCHAR(60) NOT NULL,
miss VARCHAR(60) NOT NULL,
date DATE NOT NULL,
time TIME NOT NULL,
PRIMARY KEY (id)
) ENGINE = InnoDB;
ALTER TABLE DADOS ADD FOREIGN KEY (id)
REFERENCES LOGIN_SENHA (id)
ON DELETE RESTRICT
ON UPDATE RESTRICT;

INSERT INTO DADOS VALUES
(0, '9.9', '0', '2023-06-16', '12:00:00'),
(1, '2.0', '20', '2023-06-16', '12:00:00'),
(2, '6.0', '2', '2010-06-16', '12:00:00');
SELECT * FROM DADOS;





/*
TIPOS DE DADOS:

- VARCHAR(60) NOT NULL
- SMALLINT UNSIGNED NOT NULL (para ID)
- DATE NOT NULL (para datas)
- NUMERIC (algarismos_inteiros, num_decimais)
- TIME NOT NULL (para horários)
- BOOLEAN NOT NULL


=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

FAZER REFERÊNCIA ENTRE TABELAS:

ALTER TABLE <tabela-que-refernciará-outra> ADD FOREIGN KEY (<nome-da-variavel-que-referenciara-a-outra-tabela>)
REFERENCES <tabela-referenciada> (<variavel-referenciada-da-outra-tabela>)
ON DELETE RESTRICT
ON UPDATE RESTRICT;
*/
