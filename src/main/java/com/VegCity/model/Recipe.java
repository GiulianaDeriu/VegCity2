package com.VegCity.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Recipe {

    public Recipe(String id, String titolo, String ingredienti, String preparazione, String cottura) {
        this.id = id;
        this.titolo = titolo;
        this.ingredienti = ingredienti;
        this.preparazione = preparazione;
        this.cottura = cottura;
    }

    private String id;
    private String titolo;
    private String ingredienti;
    private String preparazione;
    private String cottura;
    
    private List<Category> categoria;

    private List<Intolerance> intolleranza;

    private User user;

}
