package com.senai.GiovannaRochaLivros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.GiovannaRochaLivros.Entities.GerLivros;

public interface LivrosRepository extends JpaRepository<GerLivros, Long> {
	
}
