package com.MihaelaCosovan.HibernateSpringApp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController; 

@RestController
public class ParereController {
	@Autowired
	private ParereRepository pr;
	
	@Autowired 
	private ItemRepository ir;
	
	@GetMapping("/item/{id}/pareri")
	public Page<Parere> getPareriByItemId(@PathVariable(value="id") Long id, Pageable pageable){
		return pr.findByItemId(id, pageable);
	}
	
	@PostMapping("item/{id}/pareri")
	public Parere createParere(@PathVariable(value="id") Long id, @Valid @RequestBody Parere parere) {
		//Item item = ir.findById(id).get();
		return ir.findById(id).map(item -> {
			parere.setItem(item);
			return pr.save(parere);
		}).orElseThrow(() -> new ExceptieLipsaObiect("Item" + id + "not found"));
	}
	
	@PutMapping("/item/{iid}/pareri/{pid}")
	public Parere updateParere(@PathVariable(value="iid") Long itemId, @PathVariable(value="pid") Long parereId, @Valid @RequestBody Parere parereReq) {
		if(!ir.existsById(parereId)) {
			throw new ExceptieLipsaObiect("Item" + itemId + "not found");
		}
		
		return pr.findById(parereId).map(parere -> {
			parere.setText(parereReq.getText());
			return pr.save(parere);
		}).orElseThrow(() -> new ExceptieLipsaObiect("Parere" + parereId + "not found"));
	} 
	
	
} 