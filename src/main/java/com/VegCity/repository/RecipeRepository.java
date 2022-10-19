package com.VegCity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.VegCity.model.entity.RecipeEntity;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    
    @Query(value="select r.* "
            + "from RECIPE r "
            + "INNER join CATEGORY c "
            + "on r.id_ricetta LIKE c.recipe_id_ricetta "
            + "WHERE c.tipologia = 'vegana'", nativeQuery=true)
    Optional<List<RecipeEntity>> findAllVegan();
    @Query(value="select r.* "
            + "from RECIPE r "
            + "INNER join CATEGORY c "
            + "on r.id_ricetta LIKE c.recipe_id_ricetta "
            + "WHERE c.tipologia = 'vegetariana'", nativeQuery=true)
    Optional<List<RecipeEntity>> findAllVegetarian();
    @Query(value="select r.* "
            + "from RECIPE r "
            + "INNER join CATEGORY c "
            + "on r.id_ricetta=c.recipe_id_ricetta "
            + "WHERE c.tipologia = 'errore'", nativeQuery=true)
    Optional<List<RecipeEntity>> findAllError();
    
    @Query(value="select r.* "
            + "from RECIPE r "
            + "INNER join INTOLERANCE i "
            + "on r.id_ricetta=i.recipe_id_ricetta "
            + "WHERE i.tipologia = 'lattosio'", nativeQuery=true)
    Optional<List<RecipeEntity>> findAllLactose();

    @Query(value="select r.* "
            + "from RECIPE r "
            + "INNER join INTOLERANCE i "
            + "on r.id_ricetta=i.recipe_id_ricetta "
            + "WHERE i.tipologia = 'nichel'", nativeQuery=true)
    Optional<List<RecipeEntity>> findAllNichel();

    @Query(value="select r.* "
            + "from RECIPE r "
            + "INNER join INTOLERANCE i "
            + "on r.id_ricetta=i.recipe_id_ricetta "
            + "WHERE i.tipologia = 'glutine'", nativeQuery=true)
    Optional<List<RecipeEntity>> findAllGluten();


    @Query(value = "select * From recipe", nativeQuery = true)
    List<RecipeEntity> findAllRecipe();

    @Query(value = "select * From recipe R WHERE R.ingredienti LIKE %?1% OR R.titolo LIKE %?1%", nativeQuery = true)
    public Optional<List<RecipeEntity>> search(@Param("keyword") String keyword);

}
