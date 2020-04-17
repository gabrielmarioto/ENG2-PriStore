
CREATE TABLE Marca (
                cod INT NOT NULL serial,
                nome VARCHAR(40) NOT NULL,
                CONSTRAINT pk_Marca PRIMARY KEY (cod)
);

CREATE TABLE Colecao (
               cod INT NOT NULL serial,
                nome VARCHAR(40) NOT NULL,
                dataInicio DATE NOT NULL,
                CONSTRAINT pk_Colecao PRIMARY KEY (cod)
);

CREATE TABLE Categoria (
               cod INT NOT NULL serial,
                nome VARCHAR(40) NOT NULL,
                CONSTRAINT pk_Categoria PRIMARY KEY (cod)
);

CREATE TABLE Produto (
               cod INT NOT NULL serial,
                codCategoria INT NOT NULL,
                nome VARCHAR(40) NOT NULL,
                tamanho CHAR(2) NOT NULL,
                preco DECIMAL(6,2) NOT NULL,
                descricao VARCHAR(40) NOT NULL,
                codMarca INT NOT NULL,
                codColecao INT NOT NULL,
                CONSTRAINT pk_Produto PRIMARY KEY (cod),
                CONSTRAINT Marca_Produto_fk FOREIGN KEY (codMarca) REFERENCES Marca (cod),
                CONSTRAINT Colecao_Produto_fk FOREIGN KEY (codColecao) REFERENCES Colecao (cod),
                CONSTRAINT Categoria_Produto_fk FOREIGN KEY (codCategoria) REFERENCES Categoria (cod)
);
CREATE TABLE Fornecedor (
               cod INT NOT NULL serial,
                nome VARCHAR(40) NOT NULL,
                cnpj VARCHAR(14) NOT NULL,
                inscricaoEstadual VARCHAR(9) NOT NULL,
                endereco VARCHAR(40) NOT NULL,
                email VARCHAR(40) NOT NULL,
                tel VARCHAR(11) NOT NULL,
                rua VARCHAR(50) NOT NULL,
                cidade VARCHAR(50) NOT NULL,
                numRua INT NOT NULL,
                bairro VARCHAR(50) NOT NULL,
                CEP INT NOT NULL,
                CONSTRAINT pk_Fornecedor PRIMARY KEY (cod)
);
