package com.VegCity.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.VegCity.model.Category;
import com.VegCity.model.Intolerance;
import com.VegCity.model.Recipe;
import com.VegCity.model.entity.CategoryEntity;
import com.VegCity.model.entity.IntoleranceEntity;
import com.VegCity.model.entity.RecipeEntity;
import com.VegCity.model.entity.UserEntity;
import com.VegCity.repository.CategoryRepository;
import com.VegCity.repository.IntoleranceRepository;
import com.VegCity.repository.RecipeRepository;
import com.VegCity.repository.UserRepository;
import com.VegCity.utility.Utility;

@Service
public class RecipeService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IntoleranceRepository intoleranceRepository;
    @Autowired
    private Utility utility;
    // quando ho una variabile sull'app.properties e per passarla alla classe uso
    // questa ann. se è solo una stringa
    // @Value("${streetNumber}") private String streetNumber;
    // quando ho una variabile sull'app.properties e per passarla alla classe uso
    // questa ann. se è una lista
    @Value("#{'${vegan}'.split(',')}")
    private List<String> veganWords;
    @Value("#{'${vegetarian}'.split(',')}")
    private List<String> vegetarianWords;
    @Value("#{'${noLactose}'.split(',')}")
    private List<String> noLactoseWords;
    @Value("#{'${noNichel}'.split(',')}")
    private List<String> noNichelWords;
    @Value("#{'${noGluten}'.split(',')}")
    private List<String> noGlutenWords;
    @Value("#{'${error}'.split(',')}")
    private List<String> errorWords;

    public Object createRecipe(Recipe recipe) throws Exception {

        Optional<UserEntity> userEntity = userRepository.findByUsername(recipe.getUser().getName());

        ingredientControl(recipe);

        RecipeEntity recipeEntity = utility.convertRecipe(recipe);
        if (recipeEntity != null) {

            if (recipeEntity.getId() != null) {
                Optional<RecipeEntity> recipeFromDb = recipeRepository.findById(recipeEntity.getId());
                if (recipeFromDb.isPresent()) {
                    update(recipe, recipeFromDb);
                } else {
                    System.out.println("ricetta salvata");
                    
                    
                    recipeEntity.setUser(userEntity.get());
                    recipeRepository.save(recipeEntity);
                    
                    for(CategoryEntity categoryEntity : recipeEntity.getCategoria()) {
                        categoryEntity.setRecipe(recipeEntity);
                    }
                    for(IntoleranceEntity intoleranceEntity : recipeEntity.getIntolleranza()) {
                        intoleranceEntity.setRecipe(recipeEntity);
                    }
                    
                    categoryRepository.saveAllAndFlush(recipeEntity.getCategoria());
                    intoleranceRepository.saveAllAndFlush(recipeEntity.getIntolleranza());
                    
                    userEntity.get().getRecipe().add(recipeEntity);
                    userRepository.save(userEntity.get());
                }
            } else {
                
                System.out.println("ricetta salvata");
                
                recipeEntity.setUser(userEntity.get());
                recipeRepository.save(recipeEntity);
                
                for(CategoryEntity categoryEntity : recipeEntity.getCategoria()) {
                    categoryEntity.setRecipe(recipeEntity);
                }
                for(IntoleranceEntity intoleranceEntity : recipeEntity.getIntolleranza()) {
                    intoleranceEntity.setRecipe(recipeEntity);
                }
                
                categoryRepository.saveAllAndFlush(recipeEntity.getCategoria());
                intoleranceRepository.saveAllAndFlush(recipeEntity.getIntolleranza());
                
                userEntity.get().getRecipe().add(recipeEntity);
                userRepository.save(userEntity.get());

            }

        }
        return recipe;
    }

    private void ingredientControl(Recipe recipe) {
        
        List<Category> categories = new ArrayList<Category>();
        List<Intolerance> intolerances = new ArrayList<>();

        categories = errorControl(recipe);
        if(categories.isEmpty()) {
            Category vegan = veganControl(recipe);
            if(vegan != null) {
                categories.add(vegan);
            }
            Category vegetarian = vegetariaControl(recipe);
            if(vegetarian != null) {
                categories.add(vegetarian);
            }
        }

        if(categories != null && !categories.isEmpty()) {
           recipe.setCategoria(categories);
       }
        intolerances = intoleranceControl(recipe);
       
        if(intolerances != null && !intolerances.isEmpty()) {
            recipe.setIntolleranza(intolerances);
        }

    }

    private List<Category> errorControl(Recipe recipe) {
        
        List<Category> categories = new ArrayList<Category>();
        boolean error = false;
        
        List<String> ingredienti = Arrays.asList(recipe.getIngredienti().replace(" ", "").split(","));
        for (String ingrediente : ingredienti) {
           
            if (errorWords.contains(ingrediente)) {
                error = true;
            }
        }
        if(error) {
            Category category = new Category();
            category.setTipologia("errore");
            category.setRecipe(recipe);
            categories.add(category);
        }
        return categories;
    }

    private Category vegetariaControl(Recipe recipe) {
        
        Category category = null;
        boolean veg = false;
        
        List<String> ingredienti = Arrays.asList(recipe.getIngredienti().replace(" ", "").split(","));
        for (String ingrediente : ingredienti) {
            
               if (vegetarianWords.contains(ingrediente)) {
                   veg = true;
                }
        }
        
        if (veg) {
            category = new Category();
            category.setTipologia("vegetariana");
            category.setRecipe(recipe);
        }
        
        return category;
    }

    private Category veganControl(Recipe recipe) {
        
        Category category = null;
        boolean veg = false;
        
        List<String> ingredienti = Arrays.asList(recipe.getIngredienti().replace(" ", "").split(","));
        for (String ingrediente : ingredienti) {
            if (veganWords.contains(ingrediente)) {
                veg = true;
            }
        }
        
        if (veg) {
            category = new Category();
            category.setTipologia("vegana");
            category.setRecipe(recipe);
        }
        
        
        return category;
    }

    private List<Intolerance> intoleranceControl(Recipe recipe) {
        
        List<Intolerance> intolerances = new ArrayList<>();

        Intolerance nichel = nichelControl(recipe);
        if(nichel != null) {
            intolerances.add(nichel);
        }
        Intolerance lactose =lactoseControl(recipe);
        if(lactose != null) {
            intolerances.add(lactose);
        }
        Intolerance gluten =glutenControl(recipe);
        if(gluten != null) {
            intolerances.add(gluten);
        }

        return intolerances;
    }

    private Intolerance glutenControl(Recipe recipe) {

        Intolerance intolerance = null;
        boolean gluten = false;

        List<String> ingredienti = Arrays.asList(recipe.getIngredienti().replace(" ", "").split(","));
        for (String ingrediente : ingredienti) {
            if (noGlutenWords.contains(ingrediente) || noGlutenWords.contains(recipe.getCottura())) {
                gluten = true;
                
            }
        }
        
        if (gluten) {
            intolerance = new Intolerance();
            intolerance.setTipologia("glutine");
        }
        return intolerance;
    }

    private Intolerance lactoseControl(Recipe recipe) {

        Intolerance intolerance = null;
        boolean lactose = false;
        
        List<String> ingredienti = Arrays.asList(recipe.getIngredienti().replace(" ", "").split(","));
        for (String ingrediente : ingredienti) {
            if (noLactoseWords.contains(ingrediente)) {
                lactose = true;
                
            }
        }
        
        if (lactose) {
            intolerance = new Intolerance();
            intolerance.setTipologia("lattosio");
        }
        return intolerance;
    }

    private Intolerance nichelControl(Recipe recipe) {

        Intolerance intolerance = null;
        boolean nichel = false;
        List<String> ingredienti = Arrays.asList(recipe.getIngredienti().replace(" ", "").split(","));
        for (String ingrediente : ingredienti) {
            if (noNichelWords.contains(ingrediente)) {
                nichel = true;

            }
        }
        if (nichel) {
            intolerance = new Intolerance();
            intolerance.setTipologia("nichel");
        }
        return intolerance;
    }

    public RecipeEntity update(Recipe recipe, Optional<RecipeEntity> recipeFromDb) throws Exception {

        recipeFromDb.get().setIngredienti(recipe.getIngredienti());
        recipeFromDb.get().setCottura(recipe.getCottura());
        recipeFromDb.get().setTitolo(recipe.getTitolo());
        recipeFromDb.get().setCategoria(utility.convertCategory(recipe.getCategoria()));
        recipeFromDb.get().setIntolleranza(utility.convertIntolerance(recipe.getIntolleranza()));
        
        recipeRepository.save(recipeFromDb.get());

        return recipeFromDb.get();
    }

    public void delete(String id) {

        if (id != null) {
            Optional<RecipeEntity> deleteRecipe = recipeRepository.findById(Long.parseLong((id)));
            UserEntity userEntity = deleteRecipe.get().getUser();
        
            List<CategoryEntity> categoryEntities = deleteRecipe.get().getCategoria();
            List<IntoleranceEntity> intoleranceEntities = deleteRecipe.get().getIntolleranza();

            intoleranceRepository.deleteAll(intoleranceEntities);
            categoryRepository.deleteAll(categoryEntities);
            
            recipeRepository.delete(deleteRecipe.get());
            userEntity.getRecipe().remove(deleteRecipe.get());
            userRepository.save(userEntity);
        }
    }

    public List<Recipe> getAll() {
        List<Recipe> responseList = new ArrayList<Recipe>();

        List<RecipeEntity> requestList = recipeRepository.findAllRecipe();

        if (requestList != null && !requestList.isEmpty()) {
            responseList = utility.getAllRecipeOfUser(requestList);
        }

        return responseList;
    }

    public List<Recipe> getAllByUsername(String username) {

        List<Recipe> listaRicetteUtente = new ArrayList<Recipe>();

        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        if (userEntity.isPresent()) {
            listaRicetteUtente = utility.getAllRecipeOfUser(userEntity.get().getRecipe());
        }

        return listaRicetteUtente;
    }

    public List<Recipe> search(String keyword) {
        
        List<Recipe> recipes = new ArrayList<>();
        Optional<List<RecipeEntity>> recipesFromDB = recipeRepository.search(keyword);
        
        if(recipesFromDB.isPresent()) {
            recipes.addAll(utility.getAllRecipeOfUser(recipesFromDB.get()));
        }
        
        return recipes;
    }

    public Recipe findById(Long id) throws Exception {

        Optional<RecipeEntity> recipEntity = recipeRepository.findById(id);
        Recipe recipe = new Recipe();

        if (recipEntity.isPresent()) {
            recipe = utility.convertRecipeEntity(recipEntity.get());
        }

        return recipe;
    }
}
