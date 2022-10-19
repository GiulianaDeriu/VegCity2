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
public class CategoryService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private Utility utility;

    public List<Recipe> getAllVegan() {
        List<Recipe> listRecVegan = new ArrayList<Recipe>();

        Optional<List<RecipeEntity>> recVeg = recipeRepository.findAllVegan();

        if (recVeg.isPresent()) {
            listRecVegan = utility.getAllRecipeOfUser(recVeg.get());
        }

        return listRecVegan;
    }

    public List<Recipe> getAllVegetarian() {
        List<Recipe> listRecVegetarian = new ArrayList<Recipe>();

        Optional<List<RecipeEntity>> recVeget = recipeRepository.findAllVegetarian();

        if (recVeget.isPresent()) {
            listRecVegetarian = utility.getAllRecipeOfUser(recVeget.get());
        }

        return listRecVegetarian;
    }

    public List<Recipe> getAllError() {
        List<Recipe> listRecError = new ArrayList<Recipe>();

        Optional<List<RecipeEntity>> recError = recipeRepository.findAllError();

        if (recError.isPresent()) {
            listRecError = utility.getAllRecipeOfUser(recError.get());
        }

        return listRecError;
    }

}
