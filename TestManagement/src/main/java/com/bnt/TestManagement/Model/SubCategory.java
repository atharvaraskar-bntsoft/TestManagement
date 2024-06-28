package com.bnt.TestManagement.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subcategoryId;
    
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;   
    private String subcategoryName;
    private String subcategoryDescription;

}
