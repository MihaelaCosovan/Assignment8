package com.MihaelaCosovan.HibernateSpringApp;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
	
	@Autowired
	private ItemRepository itemR;
	
	@Autowired 
	private CategorieRepository catR;
	
/*	@GetMapping("/")
	public void DisplayText() {
		System.out.println("This is Iroot");
	} */
	
	@GetMapping("/items")
	public Page<Item> getAllItems(Pageable pageable) {
		return itemR.findAll(pageable);
	}
	
//new ->ok	
	@GetMapping("/categorie/{Categorie_id}/items")
	public Page<Item> getItemsByCategoryId(@PathVariable(value="Categorie_id") Long Categorie_id, Pageable pageable){
		return itemR.findByCategorieId(Categorie_id, pageable);
	} 
	
	@PostMapping("/categorie/{Cat_id}/items")
	public Item createItem(@PathVariable(value="Categorie_id") Long Categorie_id, @Valid @RequestBody Item item) {
		return catR.findById(Categorie_id).map(categorie -> {
			item.setCategorie(categorie);
			return itemR.save(item);
		}).orElseThrow(() -> new ExceptieLipsaObiect("Item" + Categorie_id + "not found"));
	} 
	
	@PutMapping("/items/{id}")
	public Item updateItem(@PathVariable Long id, @Valid @RequestBody Item itemReq) {
		return itemR.findById(id).map(item -> {
					item.setTitle(itemReq.getTitle());
					item.setDescription(itemReq.getDescription());
					item.setContent(itemReq.getContent());
					return itemR.save(item);
											  }).orElseThrow( () -> new ExceptieLipsaObiect("Item: "+id+" not found!"));
	}
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable Long itemId) {
		return itemR.findById(itemId).map(item -> {
					itemR.delete(item);
					return ResponseEntity.ok().build();
												  }).orElseThrow( () -> new ExceptieLipsaObiect("Item: "+itemId+" not found!"));		
	}

}
