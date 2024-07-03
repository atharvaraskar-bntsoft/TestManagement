package com.bnt.TestManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.bnt.TestManagement.Model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,Integer>{
    Optional<Category> findByCategoryName(String categoryName);
}
