package com.MihaelaCosovan.HibernateSpringApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository <Categorie, Long> {
	//Page<Parere> findByCategorieId(Long categorieId, Pageable pageable);
	//Optional<Parere> findByIdAndCategorieId(Long id, Long categorieId);
}
