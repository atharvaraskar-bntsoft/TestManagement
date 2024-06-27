package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import com.bnt.TestManagement.Model.McqQuestion;

public interface TestManageMentService {
    public McqQuestion saveMcqQuestion( McqQuestion mcqQuestion);

    public Optional<McqQuestion> getMcqQuestionById(int id);

    public List<McqQuestion> getAllEMcqQuestions();

    public McqQuestion updateMcqQuestion(McqQuestion mcqQuestion);

    public void deleteMcqQuestion(int id);

}
