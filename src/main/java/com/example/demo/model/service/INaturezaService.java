package com.example.demo.model.service;

import org.springframework.data.domain.Page;

import com.example.demo.model.ParamsRequestModel;
import com.example.demo.model.entity.Natureza;

public interface INaturezaService {

	public Page<Natureza> findAll(ParamsRequestModel prm);

	public Page<Natureza> findByNomeContaining(String nome, ParamsRequestModel prm);

	public Natureza FindById(Long id);

	public Natureza save(Natureza natureza);

	public void deleteById(Long id);

}
