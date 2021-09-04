package ibm.java.academy.cerfiticationsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;

@Controller
@RequestMapping("view")
public class ViewController {

    @Autowired
    UserJpaRepository userJpaRepository;

    @GetMapping("/all")
    public String listAll(Model model){
        model.addAttribute("users", userJpaRepository.findAll());
        return "listAll";
    }
    
}
