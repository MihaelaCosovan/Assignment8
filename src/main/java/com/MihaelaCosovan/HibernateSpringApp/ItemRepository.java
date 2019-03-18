package com.MihaelaCosovan.HibernateSpringApp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	Page<Item> findByCategorieId(Long Categorie_id, Pageable pageable);
}