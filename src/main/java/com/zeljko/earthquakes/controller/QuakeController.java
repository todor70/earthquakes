package com.zeljko.earthquakes.controller;

import com.zeljko.earthquakes.entity.Quake;
import com.zeljko.earthquakes.service.QuakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class QuakeController {

    private QuakeService quakeService;

    @Autowired
    public QuakeController(QuakeService quakeService) {
        this.quakeService = quakeService;
    }

    @RequestMapping("/quakes")
    public String list(Model model) {
        List<Quake> quakes = quakeService.getAllQuakes();
        model.addAttribute("quakes", quakes);
        return "quake_list";
    }

    @RequestMapping("/quakes4")
    public String quakes4(Model model) {
        List<Quake> quakes = quakeService.getAllByMagBetween(5.0, 5.49);
        model.addAttribute("quakes", quakes);
        return "quake_list";
    }

    @RequestMapping("/quakes5")
    public String quakes5(Model model) {
        List<Quake> quakes = quakeService.getAllByMagBetween(5.5, 6.0);
        model.addAttribute("quakes", quakes);
        return "quake_list";
    }

    @RequestMapping("/quakes6")
    public String quakes6(Model model) {
        List<Quake> quakes = quakeService.getAllByMagGreaterThan(6.0);
        model.addAttribute("quakes", quakes);
        return "quake_list";
    }

}
