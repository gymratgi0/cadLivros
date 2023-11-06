package com.senai.GiovannaRochaLivros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.GiovannaRochaLivros.Entities.GerLivros;
import com.senai.GiovannaRochaLivros.repositories.LivrosRepository;

@Service
public class LivrosService {
	
	private final LivrosRepository livrosRepository;
	
	@Autowired
	public LivrosService(LivrosRepository livrosRepository) {
		this.livrosRepository = livrosRepository;
		
	}
	
	public GerLivros saveLivros(GerLivros gerLivros) {
		return livrosRepository.save(gerLivros);
	}
	
	public GerLivros getLivrosById(Long id) {
		return livrosRepository.findById(id).orElse(null);
	}

	public List<GerLivros> getAllLivros(){
		return livrosRepository.findAll();
	}
	
	public void deleteLivros(Long id) {
		livrosRepository.deleteById(id);
	}
	
	// fazendo o update do gerenciamento de livros com o optional
		public GerLivros updateLivros(Long id, GerLivros novoGerLivros) {
			Optional<GerLivros> livrosOptional = livrosRepository.findById(id);
		        
			if (livrosOptional.isPresent()) {
		        	GerLivros livrosExistente = livrosOptional.get();
		           	livrosExistente.setDescricao(novoGerLivros.getDescricao());
		        	livrosExistente.setIsbn(novoGerLivros.getIsbn());          
		            return livrosRepository.save(livrosExistente); 
			} else {
				return null; 
		    }
		}
	
}
