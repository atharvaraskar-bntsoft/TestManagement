package com.bnt.TestManagement.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "mcq_question")
public class McqQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
    int  question_id ;
	String category;
	String question;
	String option_one;
	String option_two;
    String option_three;
	String option_four ;
	String correct_option;
	int positive_mark;
	int negative_mark;

}
