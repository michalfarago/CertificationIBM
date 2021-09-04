package ibm.java.academy.cerfiticationsapp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;
// import ibm.java.academy.cerfiticationsapp.service.UserService;

@Controller
public class RestController {

    @Autowired 
    UserJpaRepository userJpaRepository;
    
    // @Autowired 
    // UserService service;
    
    @GetMapping("/all-users")
    @ResponseBody
    public List<User> users() {
        return userJpaRepository.findAll();
    }

    @PostMapping(path = "/add-user", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userJpaRepository.save(user);
    }

    @DeleteMapping("/delete-user")
    @ResponseBody 
    public void deleteUser(@RequestParam("id") Long id) {
        userJpaRepository.deleteAllById(Arrays.asList(id));
    }

    

    
}
