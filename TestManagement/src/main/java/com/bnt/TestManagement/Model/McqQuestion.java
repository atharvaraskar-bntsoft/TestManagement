package com.bnt.TestManagement.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private int  question_id ;
    @ManyToOne
    @JoinColumn(name = "subcategoryId")
    private SubCategory subCategory;
	private String question;
	private String option_one;
	private String option_two;
    private String option_three;
	private String option_four ;
	private String correct_option;
	private int positive_mark;
	private int negative_mark;

}
