create table ocorrencia(
	id bigint primary key not null auto_increment,
    entrega_id bigint not null,
    descricao text not null,
    data_registro datetime not null,
    
    constraint fk_ocorrencia_entrega foreign key (entrega_id) references entrega (id)
);