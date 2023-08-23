package com.itb.mif3an.academicologin.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "roles")
public class Role {
	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long Id;
private String Name;


public Long getId() {
	return Id;
}
public void setId(Long id) {
	Id = id;
}
public String getNome() {
	return Name;
}
public void setNome(String nome) {
	Name = nome;
}

public Role() {
	
}
public Role(String nome) {
	Name = nome;
}
public Role(Long id, String nome) {
	Id = id;
	Name = nome;
}
}