package com.example.demo.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Natureza;

public interface NaturezaRepository extends JpaRepository<Natureza, Long>{

	Page<Natureza> findByNomeContaining(String nome, Pageable pageable);
	
}
