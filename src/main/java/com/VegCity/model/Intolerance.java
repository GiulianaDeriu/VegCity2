package com.VegCity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Intolerance {

    private Long id;
    private String tipologia;

    private Recipe recipe;
    
    
    
}
