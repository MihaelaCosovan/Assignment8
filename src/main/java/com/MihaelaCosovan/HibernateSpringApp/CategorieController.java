package com.MihaelaCosovan.HibernateSpringApp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieController {
	
	@Autowired
	private CategorieRepository categorieR;
	
	@GetMapping("/categorie")
	public Page<Categorie> getAllCategories(Pageable pageable) {
		return categorieR.findAll(pageable);
	}
	
	@PostMapping("/categorie")
	public Categorie createCategorie(@Valid @RequestBody Categorie categorie) {
		return categorieR.save(categorie);
	}
	
	@PutMapping("/categorie/{id}")
	public Categorie updateCategorie(@PathVariable Long categorieId, @Valid @RequestBody Categorie categorieReq) {
		return categorieR.findById(categorieId).map(categorie -> {
					categorie.setName(categorieReq.getName());
					categorie.setDescription(categorieReq.getDescription());
					return categorieR.save(categorie);
											  }).orElseThrow( () -> new ExceptieLipsaObiect("Categorie: "+categorieId+" not found!"));
	}
	
	@DeleteMapping("/categorie/{id}")
	public ResponseEntity<?> deleteCategorie(@PathVariable Long categorieId) {
		return categorieR.findById(categorieId).map(categorie -> {
					categorieR.delete(categorie);
					return ResponseEntity.ok().build();
												  }).orElseThrow( () -> new ExceptieLipsaObiect("Categorie: "+categorieId+" not found!"));		
	}

}