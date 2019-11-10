package com.example.lojamarcao.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lancamento")
public class Lancamento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod;

    @NotNull
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private  LocalDate dataPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cod_categoria")
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cod_pessoa")
    private Pessoa pessoa;

    @NotNull
    private BigDecimal valor;

    private Boolean status;

    @NotNull
    private String descricao;

    private String observacao;

    public Long getCod() {
	return cod;
    }

    public void setCod(Long cod) {
	this.cod = cod;
    }

    public Boolean getStatus() {
	return status;
    }

    public void setStatus(Boolean status) {
	this.status = status;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
    }

    public LocalDate getDataVencimento() {
	return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
	this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
	return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
	this.dataPagamento = dataPagamento;
    }

    public TipoLancamento getTipo() {
	return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
	this.tipo = tipo;
    }

    public Categoria getCategoria() {
	return categoria;
    }

    public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
    }

    public Pessoa getPessoa() {
	return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
	this.pessoa = pessoa;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public String getObservacao() {
	return observacao;
    }

    public void setObservacao(String observacao) {
	this.observacao = observacao;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cod == null) ? 0 : cod.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Lancamento other = (Lancamento) obj;
	if (cod == null) {
	    if (other.cod != null)
		return false;
	} else if (!cod.equals(other.cod))
	    return false;
	return true;
    }
}