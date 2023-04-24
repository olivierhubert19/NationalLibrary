package com.example.nationallibrary.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mybooks")
public class MyBook {
    @Id
    private int id;
    private String name;
    private String author;
    private double price;
}
