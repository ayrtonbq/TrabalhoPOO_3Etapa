CREATE DATABASE PetShop;

USE PetShop;

CREATE TABLE Proprietario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE Animal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(50),
    raca VARCHAR(50),
    idade INT,
    sexo CHAR(1),
    peso DECIMAL(5,2),
    foto BLOB,
    proprietario_id INT,
    FOREIGN KEY (proprietario_id) REFERENCES Proprietario(id)
);

CREATE TABLE Servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2)
);

CREATE TABLE RegistroServico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE,
    servico_id INT,
    animal_id INT,
    valor DECIMAL(10,2),
    observacoes TEXT,
    FOREIGN KEY (servico_id) REFERENCES Servico(id),
    FOREIGN KEY (animal_id) REFERENCES Animal(id)
);
