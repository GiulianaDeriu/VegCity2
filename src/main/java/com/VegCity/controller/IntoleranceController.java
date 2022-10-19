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
import com.VegCity.services.IntoleranceService;
import com.VegCity.utility.Utility;

@RestController
@RequestMapping("/intolleranza")
public class IntoleranceController {

    @Autowired
    IntoleranceService intoleranceService;
    @Autowired
    Utility utility;

    @PostMapping("/getAllLactoseIntolerance")
    public ModelAndView getAllLactoseIntolerance(HttpServletRequest request, HttpSession session) {
        var model = new ModelAndView("community");
        var user = (User) session.getAttribute("user");
        model.addObject("ruolo", user.getRuolo());
        List<Recipe> listLactose = intoleranceService.getAllLactoseIntolerance();
        model.addObject("tutte", listLactose);
        return model;
    }

    @PostMapping("/getAllNichelIntolerance")
    public ModelAndView getAllNichelIntolerance(HttpServletRequest request, HttpSession session) {
        var model = new ModelAndView("community");
        var user = (User) session.getAttribute("user");
        model.addObject("ruolo", user.getRuolo());
        List<Recipe> listNichel = intoleranceService.getAllNichelIntolerance();
        model.addObject("tutte", listNichel);
        return model;
    }

    @PostMapping("/getAllGlutenIntolerance")
    public ModelAndView getAllGlutenIntolerance(HttpServletRequest request, HttpSession session) {
        var model = new ModelAndView("community");
        var user = (User) session.getAttribute("user");
        model.addObject("ruolo", user.getRuolo());
        List<Recipe> listGluten = intoleranceService.getAllGlutenIntolerance();
        model.addObject("tutte", listGluten);
        return model;
    }
    
}
