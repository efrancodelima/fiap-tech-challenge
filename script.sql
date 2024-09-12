CREATE DATABASE IF NOT EXISTS lanchonete CHARACTER SET latin1 COLLATE latin1_swedish_ci;

USE lanchonete;

CREATE TABLE cliente (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    cpf BIGINT NOT NULL UNIQUE,
    email VARCHAR(40),
    nome VARCHAR(50),
    PRIMARY KEY (codigo)
);

CREATE TABLE produto (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL,
    descricao VARCHAR(150),
    categoria ENUM('ACOMPANHAMENTO', 'BEBIDA', 'LANCHE', 'SOBREMESA') NOT NULL,
    preco DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (codigo)
);


CREATE TABLE pedido (
    numero BIGINT NOT NULL AUTO_INCREMENT,
    codigo_cliente BIGINT,
    timestamp_checkout DATETIME(6),
    codigo_pagamento BIGINT NOT NULL,
    status_pagamento ENUM('AGUARDANDO_PAGAMENTO', 'APROVADO', 'REPROVADO') NOT NULL,
    timestamp_status_pagamento DATETIME(6) NOT NULL,
    status_pedido ENUM('AGUARDANDO_CHECKOUT', 'EM_PREPARACAO', 'FINALIZADO', 'PRONTO', 'RECEBIDO') NOT NULL,
    timestamp_status_pedido DATETIME(6) NOT NULL,
    PRIMARY KEY (numero),
    FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
);

CREATE TABLE itens_pedido (
    numero_pedido BIGINT NOT NULL,
    codigo_produto BIGINT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (numero_pedido) REFERENCES pedido(numero),
    FOREIGN KEY (codigo_produto) REFERENCES produto(codigo)
);

-- Clientes
INSERT INTO cliente (cpf, email, nome) VALUES (12345678909, 'joao_silva@gmail.com', 'João Pedro da Silva');
INSERT INTO cliente (cpf, email, nome) VALUES (23456789092, 'maria_oliveira@gmail.com', 'Maria Clara de Oliveira');
INSERT INTO cliente (cpf, email, nome) VALUES (34567890175, 'carlos_souza@gmail.com', 'Carlos Eduardo de Souza');
INSERT INTO cliente (cpf, email, nome) VALUES (45678901249, 'ana_pereira@gmail.com', 'Ana Paula Pereira');
INSERT INTO cliente (cpf, email, nome) VALUES (56789012303, 'lucas_fernandes@gmail.com', 'Lucas Henrique Fernandes');

-- Acompanhamentos
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Batata Frita', 'Batata frita crocante', 'ACOMPANHAMENTO', 5.50);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Anéis de Cebola', 'Anéis de cebola empanados', 'ACOMPANHAMENTO', 6.00);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Nuggets de Frango', 'Porção de nuggets de frango', 'ACOMPANHAMENTO', 7.00);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Mandioca Frita', 'Porção de mandioca frita', 'ACOMPANHAMENTO', 5.00);

-- Bebidas
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Refrigerante Cola', 'Refrigerante de cola 350ml', 'BEBIDA', 3.50);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Suco de Laranja', 'Suco de laranja natural 300ml', 'BEBIDA', 4.00);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Água Mineral', 'Água mineral sem gás 500ml', 'BEBIDA', 2.50);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Chá Gelado', 'Chá gelado de limão 300ml', 'BEBIDA', 3.00);

-- Lanches
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Cheeseburger Bacon', 'Hambúrguer com queijo e bacon', 'LANCHE', 12.00);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Sanduíche de Frango', 'Sanduíche de frango grelhado', 'LANCHE', 10.00);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Wrap Vegetariano', 'Wrap vegetariano com legumes', 'LANCHE', 9.00);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Hot Dog', 'Hot dog com molho especial', 'LANCHE', 8.00);

-- Sobremesas
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Sorvete de Chocolate', 'Sorvete de chocolate 2 bolas', 'SOBREMESA', 6.50);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Torta de Maçã', 'Torta de maçã com canela', 'SOBREMESA', 5.50);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Brownie', 'Brownie de chocolate com nozes', 'SOBREMESA', 7.00);
INSERT INTO produto (nome, descricao, categoria, preco) VALUES ('Pudim', 'Pudim de leite condensado', 'SOBREMESA', 4.50);

-- Pedido 1
INSERT INTO pedido (codigo_cliente, timestamp_checkout, codigo_pagamento, timestamp_status_pagamento, status_pagamento, timestamp_status_pedido, status_pedido) 
VALUES (1, '2024-09-11 15:00:00', 10, NOW(), 'APROVADO', NOW(), 'EM_PREPARACAO');

INSERT INTO itens_pedido (numero_pedido, codigo_produto, quantidade) 
VALUES (1, 1, 2), (1, 5, 1);

-- Pedido 2
INSERT INTO pedido (codigo_cliente, timestamp_checkout, codigo_pagamento, timestamp_status_pagamento, status_pagamento, timestamp_status_pedido, status_pedido) 
VALUES (2, '2024-09-11 15:05:00', 20, NOW(), 'APROVADO', NOW(), 'EM_PREPARACAO');

INSERT INTO itens_pedido (numero_pedido, codigo_produto, quantidade) 
VALUES (2, 2, 1), (2, 6, 1), (2, 10, 1);

-- Pedido 3
INSERT INTO pedido (codigo_cliente, timestamp_checkout, codigo_pagamento, timestamp_status_pagamento, status_pagamento, timestamp_status_pedido, status_pedido) 
VALUES (3, '2024-09-11 15:10:00', 30, NOW(), 'APROVADO', NOW(), 'EM_PREPARACAO');

INSERT INTO itens_pedido (numero_pedido, codigo_produto, quantidade) 
VALUES (3, 3, 1), (3, 7, 2);

-- Pedido 4
INSERT INTO pedido (codigo_cliente, timestamp_checkout, codigo_pagamento, timestamp_status_pagamento, status_pagamento, timestamp_status_pedido, status_pedido) 
VALUES (4, '2024-09-11 15:15:00', 40, NOW(), 'APROVADO', NOW(), 'EM_PREPARACAO');

INSERT INTO itens_pedido (numero_pedido, codigo_produto, quantidade) 
VALUES (4, 4, 1), (4, 8, 1), (4, 12, 1);

-- Pedido 5
INSERT INTO pedido (codigo_cliente, timestamp_checkout, codigo_pagamento, timestamp_status_pagamento, status_pagamento, timestamp_status_pedido, status_pedido) 
VALUES (5, '2024-09-11 15:20:00', 50, NOW(), 'APROVADO', NOW(), 'EM_PREPARACAO');

INSERT INTO itens_pedido (numero_pedido, codigo_produto, quantidade) 
VALUES (5, 9, 1), (5, 13, 1);

-- Pedido 6
INSERT INTO pedido (timestamp_checkout, codigo_pagamento, timestamp_status_pagamento, status_pagamento, timestamp_status_pedido, status_pedido) 
VALUES ('2024-09-11 15:25:00', 60, NOW(), 'APROVADO', NOW(), 'EM_PREPARACAO');

INSERT INTO itens_pedido (numero_pedido, codigo_produto, quantidade) 
VALUES (6, 10, 1), (6, 14, 1), (6, 15, 1);

-- Pedido 7
INSERT INTO pedido (timestamp_checkout, codigo_pagamento, timestamp_status_pagamento, status_pagamento, timestamp_status_pedido, status_pedido) 
VALUES ('2024-09-11 15:30:00', 70, NOW(), 'APROVADO', NOW(), 'EM_PREPARACAO');

INSERT INTO itens_pedido (numero_pedido, codigo_produto, quantidade) 
VALUES (7, 11, 1), (7, 16, 1);

-- Pedido 8
INSERT INTO pedido (timestamp_checkout, codigo_pagamento, timestamp_status_pagamento, status_pagamento, timestamp_status_pedido, status_pedido) 
VALUES ('2024-09-11 15:35:00', 80, NOW(), 'APROVADO', NOW(), 'EM_PREPARACAO');

INSERT INTO itens_pedido (numero_pedido, codigo_produto, quantidade) 
VALUES (8, 12, 1), (8, 1, 1), (8, 5, 1), (8, 9, 1);
