package com.zeljko.earthquakes.controller;

import com.zeljko.earthquakes.entity.Quake;
import com.zeljko.earthquakes.service.QuakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuakeController {

    private QuakeService quakeService;

    @Autowired
    public QuakeController(QuakeService quakeService) {
        this.quakeService = quakeService;
    }

    private double mag1;
    private double mag2;

    @GetMapping("/quakes")
    public String list(Model model) {
        List<Quake> quakes = quakeService.getAllByMagBetween(mag1, mag2);
        List<Double> mag = new ArrayList<>();
        mag.add(mag1);
        mag.add(mag2);
        model.addAttribute("quakes", quakes);
        model.addAttribute("mag", mag);
        return "quake_list";
    }

    @GetMapping("/quakesAll")
    public String listAll() {
        mag1 = 5.0;
        mag2 = 9.0;
        return "redirect:/quakes";
    }

    @GetMapping("/quakes5")
    public String quakes4() {
        mag1 = 5.0;
        mag2 = 5.5;
        return "redirect:/quakes";
    }

    @GetMapping("/quakes5.5")
    public String quakes5() {
        mag1 = 5.5;
        mag2 = 6.0;
        return "redirect:/quakes";
    }

    @GetMapping("/quakes6")
    public String quakes6() {
        mag1 = 6.0;
        mag2 = 9.0;
        return "redirect:/quakes";
    }

}