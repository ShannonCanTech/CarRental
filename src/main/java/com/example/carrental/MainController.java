package com.example.carrental;

import com.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable; //added
import org.springframework.web.bind.annotation.RequestMapping; //added

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    CarRepository carRepo;

    @RequestMapping("/")
    public String showIndex(Model model)
    {
        model.addAttribute("cars",carRepo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addCar(Model model)
    {
        model.addAttribute("aCar", new Car());
        return "addcar";
    }

    @RequestMapping("/cars")
    public String showCars(Model model)
    {
        model.addAttribute("cars",carRepo.findAll());
        return "carlist";
    }

    @PostMapping("/savecar")
    public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult result)
    {

        if(result.hasErrors())
        {
            return "addcar";
        }
        carRepo.save(car);
        return "redirect:cars";
    }

    @RequestMapping("/changestatus/{id}")
    public String availability(@PathVariable("id") long id)
    {
        Car thisCar = carRepo.findById(id).get();

        thisCar.setAvailable(!thisCar.isAvailable());
        carRepo.save(thisCar);
        return "redirect:cars";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("aCar",carRepo.findById(id).get());
        return "addcar";
    }
}
