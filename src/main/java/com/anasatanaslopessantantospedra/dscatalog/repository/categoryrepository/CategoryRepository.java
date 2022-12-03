package com.anasatanaslopessantantospedra.dscatalog.repository.categoryrepository;

import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
