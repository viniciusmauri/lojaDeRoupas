package com.example.lojamarcao.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, String> telefone;
	public static volatile SingularAttribute<Pessoa, Boolean> ativo;
	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	public static volatile SingularAttribute<Pessoa, Long> cod;
	public static volatile SingularAttribute<Pessoa, String> nomePessoa;

	public static final String TELEFONE = "telefone";
	public static final String ATIVO = "ativo";
	public static final String ENDERECO = "endereco";
	public static final String COD = "cod";
	public static final String NOME_PESSOA = "nomePessoa";

}

