package com.VegCity.utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Component;

import com.VegCity.model.Category;
import com.VegCity.model.Intolerance;
import com.VegCity.model.Recipe;
import com.VegCity.model.User;
import com.VegCity.model.entity.CategoryEntity;
import com.VegCity.model.entity.IntoleranceEntity;
import com.VegCity.model.entity.RecipeEntity;
import com.VegCity.model.entity.UserEntity;

@Component
public class Utility {

    public UserEntity convertUser(User user) throws Exception {
        UserEntity userEntityCast = new UserEntity();

        if (user != null && user.getMail() != null) {
            userEntityCast.setId(user.getId());
            userEntityCast.setEmail(user.getMail());
            userEntityCast.setUsername(user.getName());
            userEntityCast.setPassword(user.getPassword());
            userEntityCast.setRuolo(user.getRuolo());
        } else {
            throw new Exception("error mapping userEntity");
        }

        return userEntityCast;
    }

    public User toUser(UserEntity userEntity) {
        User userCast = new User();

        if (userEntity != null) {
            userCast.setMail(userEntity.getEmail());
            userCast.setName(userEntity.getUsername());
            userCast.setPassword(userEntity.getPassword());
            userCast.setRuolo(userEntity.getRuolo());
            userCast.setRecipe(getAllRecipeOfUser(userEntity.getRecipe()));
        }
        return userCast;
    }

    // metodo che mi mostra le ricette dell'utente nel suo profilo
    public List<Recipe> getAllRecipeOfUser(List<RecipeEntity> recipe) {
        List<Recipe> listRecipe = new ArrayList<Recipe>();

        for (RecipeEntity recipeToConvert : recipe) {

            Recipe r = new Recipe();
            r.setId(recipeToConvert.getId().toString());
            r.setTitolo(recipeToConvert.getTitolo());
            r.setIngredienti(recipeToConvert.getIngredienti());
            r.setPreparazione(recipeToConvert.getPreparazione());
            r.setCottura(recipeToConvert.getCottura());
//			r.setUser(recipeToConvert.getUser()); 

            listRecipe.add(r);
        }

        return listRecipe;
    }

    public RecipeEntity convertRecipe(Recipe recipe) throws Exception {
        RecipeEntity recipeEntityCast = new RecipeEntity();

        if (recipeEntityCast != null) {
            if (recipe.getId() != null && !recipe.getId().isEmpty()) {
                recipeEntityCast.setId(Long.parseLong(recipe.getId()));
            }
            recipeEntityCast.setIngredienti(recipe.getIngredienti());
            recipeEntityCast.setPreparazione(recipe.getPreparazione());
            recipeEntityCast.setTitolo(recipe.getTitolo());
            recipeEntityCast.setCottura(recipe.getCottura());
            recipeEntityCast.setCategoria(convertCategory(recipe.getCategoria()));
            recipeEntityCast.setIntolleranza(convertIntolerance(recipe.getIntolleranza()));
            recipeEntityCast.setUser(convertUser(recipe.getUser()));

//		} else {
//			throw new Exception("error mapping recipeEntity");
        }
        return recipeEntityCast;
    }

    public List<CategoryEntity> convertCategory(List<Category> categoriaList) throws Exception {

        List<CategoryEntity> categoryEntityCastList = new ArrayList<>();
 
        if (categoriaList != null) {
            for (Category categoria : categoriaList) {

                CategoryEntity categoryEntityCast = new CategoryEntity();

                categoryEntityCast.setId(categoria.getId());
                categoryEntityCast.setTipologia(categoria.getTipologia());
              
                categoryEntityCastList.add(categoryEntityCast);
            }
        }

        return categoryEntityCastList;
    }

    public List<IntoleranceEntity> convertIntolerance(List<Intolerance> intolleranzaList) {
        List<IntoleranceEntity> intoleranceEntityCastList = new ArrayList<>();

        if (intolleranzaList != null) {
            for (Intolerance intolleranza : intolleranzaList) {
            
                IntoleranceEntity intoleranceEntityCast = new IntoleranceEntity();

                intoleranceEntityCast.setId(intoleranceEntityCast.getId());
                intoleranceEntityCast.setTipologia(intolleranza.getTipologia());

                intoleranceEntityCastList.add(intoleranceEntityCast);
            }
        }

        return intoleranceEntityCastList;
    }

    public Recipe convertRecipeEntity(RecipeEntity recipeEntity) throws Exception {
        Recipe recipeCast = new Recipe();

        if (recipeEntity != null) {
            recipeCast.setId(recipeEntity.getId().toString());
            recipeCast.setIngredienti(recipeEntity.getIngredienti());
            recipeCast.setPreparazione(recipeEntity.getPreparazione());
            recipeCast.setTitolo(recipeEntity.getTitolo());
            recipeCast.setCottura(recipeEntity.getCottura());

//	      } else {
//	          throw new Exception("error mapping recipeEntity");
        }
        return recipeCast;
    }

    public Recipe populateRecipe(HttpServletRequest request) throws SerialException, SQLException {
        Recipe recipe = new Recipe();
        recipe.setCottura(request.getParameter("cottura"));
        recipe.setId(request.getParameter("id"));        
        recipe.setIngredienti(request.getParameter("ingredienti"));
        recipe.setPreparazione(request.getParameter("preparazione"));
        recipe.setTitolo(request.getParameter("titolo"));
//        
//        byte[] buff = request.getParameter("id").getBytes(); 
//        Blob blobimg = new SerialBlob(buff);
//        recipe.setImg(blobimg);

        return recipe;
    }

}
