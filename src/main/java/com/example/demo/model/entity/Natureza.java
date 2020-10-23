package com.example.demo.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "naturezas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Natureza implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	@NotBlank
	private String nome;

	public void Atualizar(String nome) {
		this.nome = nome;
	}

	private static final long serialVersionUID = 1L;
}
