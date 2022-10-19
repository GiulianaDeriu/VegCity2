package com.VegCity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.VegCity.model.Recipe;
import com.VegCity.model.User;
import com.VegCity.services.CategoryService;
import com.VegCity.utility.Utility;

@RestController
@RequestMapping("/categoria")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    Utility utility;

    @PostMapping("/getAllVegan")
    public ModelAndView getAllVegan(HttpServletRequest request, HttpSession session) {
        var model = new ModelAndView("community");
        var user = (User) session.getAttribute("user");
        model.addObject("ruolo", user.getRuolo());
        List<Recipe> listVegan = categoryService.getAllVegan();
        model.addObject("tutte", listVegan);
        return model;
    }

    @PostMapping("/getAllVegetarian")
    public ModelAndView getAllVegetarian(HttpServletRequest request, HttpSession session) {
        var model = new ModelAndView("community");
        var user = (User) session.getAttribute("user");
        model.addObject("ruolo", user.getRuolo());
        List<Recipe> listVegetarian = categoryService.getAllVegetarian();
        model.addObject("tutte", listVegetarian);
        return model;
    }

    @PostMapping("/getAllError")
    public ModelAndView getAllError(HttpServletRequest request, HttpSession session) {
        var model = new ModelAndView("community");
        var user = (User) session.getAttribute("user");
        model.addObject("ruolo", user.getRuolo());
        List<Recipe> listError = categoryService.getAllError();
        model.addObject("tutte", listError);
        return model;

    }
}
