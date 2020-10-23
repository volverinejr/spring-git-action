package com.example.demo.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({ "id", "nome" })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Relation(collectionRelation = "naturezas")
public class NaturezaDTO extends RepresentationModel<NaturezaDTO> implements Serializable {

	@Mapping("id")
	@JsonProperty("id")
	private Long key;

	@NotBlank
	@Size(min = 4, max = 100)
	private String nome;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NaturezaDTO other = (NaturezaDTO) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;
}
