-- cria um usuário <RECOMENDÁVEL MUDAR O NOME>
CREATE USER 'template'@'localhost' IDENTIFIED BY 'template';
GRANT ALL PRIVILEGES ON * . * TO 'template'@'localhost';
FLUSH PRIVILEGES;

-- cria o database <MUDAR O NOME>
CREATE DATABASE TEMPLATE;
USE TEMPLATE;

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
id_grade SMALLINT AUTO_INCREMENT,
id_user SMALLINT UNSIGNED NOT NULL,
grade NUMERIC(4, 2) NOT NULL,
date DATE NOT NULL,
time TIME NOT NULL,
PRIMARY KEY (id_grade)
) ENGINE = InnoDB;
ALTER TABLE DADOS ADD FOREIGN KEY (id_user)
REFERENCES LOGIN_SENHA (id)
ON DELETE RESTRICT
ON UPDATE RESTRICT;


INSERT INTO DADOS VALUES
(0, 0, 6.5, '2023-06-16', '12:00:00'),
(0, 1, 2.0, '2023-06-16', '12:00:00'),
(0, 2, 6.0, '2010-06-16', '12:00:00');
SELECT * FROM DADOS;




/*
TIPOS DE DADOS:

- VARCHAR(60) NOT NULL
- SMALLINT UNSIGNED NOT NULL (para ID)
- DATE NOT NULL (para datas)
- NUMERIC (algarismos_totais, num_decimais)
- TIME NOT NULL (para horários)
- BOOLEAN NOT NULL


=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

FAZER REFERÊNCIA ENTRE TABELAS:

ALTER TABLE <tabela-que-refernciará-outra> ADD FOREIGN KEY (<nome-da-variavel-que-referenciara-a-outra-tabela>)
REFERENCES <tabela-referenciada> (<variavel-referenciada-da-outra-tabela>)
ON DELETE RESTRICT
ON UPDATE RESTRICT;
*/
