package com.senai.GiovannaRochaLivros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.GiovannaRochaLivros.Entities.GerLivros;
import com.senai.GiovannaRochaLivros.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosController {
	
	@GetMapping("/home")
	public String paginaInicial() {
		return "index";
	}

	private final LivrosService livrosService;
	
	@Autowired
	public LivrosController(LivrosService livrosService) {
		this.livrosService = livrosService;
	}
	
	@PostMapping
	public GerLivros createLivros(@RequestBody GerLivros gerLivros) {
		return livrosService.saveLivros(gerLivros);
	}
	
	//Utilizando o ResponseEntity e RequestEntity
	@GetMapping
	public ResponseEntity<List<GerLivros>> getAllLivros(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<GerLivros> gerLivros = livrosService.getAllLivros();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(gerLivros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GerLivros> getLivros(@PathVariable Long id){
		GerLivros gerLivros = livrosService.getLivrosById(id);
		if(gerLivros != null) {
			return ResponseEntity.ok(gerLivros);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public GerLivros updateLivros(@PathVariable Long id, @RequestBody GerLivros gerLivros) {
		return livrosService.updateLivros(id, gerLivros);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLivros(@PathVariable Long id) {
		livrosService.deleteLivros(id);
	}

}
