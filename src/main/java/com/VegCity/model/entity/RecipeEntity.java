package com.VegCity.model.entity;

import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity(name = "recipe")
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ricetta")
    private Long id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "ingredienti")
    private String ingredienti;

    @Column(name = "preparazione")
    private String preparazione;

    @Column(name = "cottura")
    private String cottura;

    @Column(name = "img")
    private Blob img;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    
    @OneToMany(cascade = CascadeType.ALL)
    @ElementCollection(targetClass = CategoryEntity.class)
    private List<CategoryEntity> categoria;
    @OneToMany(cascade = CascadeType.ALL)
    @ElementCollection(targetClass = IntoleranceEntity.class)
    private List<IntoleranceEntity> intolleranza;

}
