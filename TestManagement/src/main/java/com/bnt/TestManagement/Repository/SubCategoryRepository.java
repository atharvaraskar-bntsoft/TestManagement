package com.bnt.TestManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.bnt.TestManagement.Model.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer>{
     Optional<SubCategory> findBySubcategoryName(String subcategoryName);

}
