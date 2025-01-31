package com.example.carrentalsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cars") 
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String availabilityStatus = "Available";;
    private String imagePath;
    private int year;        
    private double pricePerDay; 
    
	
}
