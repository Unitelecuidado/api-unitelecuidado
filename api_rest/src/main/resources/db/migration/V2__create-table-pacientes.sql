CREATE TABLE pacientes (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(100) unique,
    telefone varchar(100) not null,
    desfecho varchar(100),
    observacoes text,
    detalhes text,
    ativo tinyint,
    primary key (id)
);