package com.VegCity.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VegCity.model.Recipe;
import com.VegCity.model.entity.RecipeEntity;
import com.VegCity.repository.RecipeRepository;
import com.VegCity.utility.Utility;

@Service
public class IntoleranceService {
    
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private Utility utility;
    
    
    public List<Recipe> getAllLactoseIntolerance() {
        List<Recipe> listLactose = new ArrayList<Recipe>();

        Optional<List<RecipeEntity>> recLact = recipeRepository.findAllLactose();

        if (recLact.isPresent()) {
            listLactose = utility.getAllRecipeOfUser(recLact.get());
        }

        return listLactose;
    }

    public List<Recipe> getAllNichelIntolerance() {
        List<Recipe> listNichel = new ArrayList<Recipe>();

        Optional<List<RecipeEntity>> recNich = recipeRepository.findAllNichel();

        if (recNich.isPresent()) {
            listNichel = utility.getAllRecipeOfUser(recNich.get());
        }

        return listNichel;
    }

    public List<Recipe> getAllGlutenIntolerance() {
        List<Recipe> listGluten = new ArrayList<Recipe>();

        Optional<List<RecipeEntity>> recGlu = recipeRepository.findAllGluten();

        if (recGlu.isPresent()) {
            listGluten = utility.getAllRecipeOfUser(recGlu.get());
        }

        return listGluten;
    }

}
