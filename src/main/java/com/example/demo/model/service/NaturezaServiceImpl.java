package com.example.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.ParamsRequestModel;
import com.example.demo.model.entity.Natureza;
import com.example.demo.model.repository.NaturezaRepository;


@Service
public class NaturezaServiceImpl implements INaturezaService {
	
	@Autowired
	private NaturezaRepository _naturezaRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<Natureza> findAll(ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		return _naturezaRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Natureza> findByNomeContaining(String nome, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		return _naturezaRepository.findByNomeContaining(nome, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Natureza FindById(Long id) {
		return _naturezaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Registro não encontrado para id %d", id)));
	}

	@Override
	@Transactional
	public Natureza save(Natureza natureza) {
		return _naturezaRepository.save(natureza);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		_naturezaRepository.deleteById(id);
	}

}
