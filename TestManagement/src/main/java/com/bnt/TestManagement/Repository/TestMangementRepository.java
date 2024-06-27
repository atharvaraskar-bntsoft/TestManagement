package com.bnt.TestManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.TestManagement.Model.McqQuestion;

@Repository
public interface TestMangementRepository extends JpaRepository<McqQuestion,Integer>{

}
