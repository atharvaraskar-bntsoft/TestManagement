package com.bnt.TestManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.bnt.TestManagement.Model.McqQuestion;

@Repository
public interface TestMangementRepository extends JpaRepository<McqQuestion,Integer>{
    Optional<McqQuestion> findByQuestion(String question);

}
