package com.festival.app.controller;

import com.festival.app.model.ApplicationUser;
import com.festival.app.repository.ApplicationUserRepository;
import com.festival.app.service.FestivalService;
import com.festival.app.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private FestivalService festivalService;


    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserDetailsServiceImpl userDetailsService
                          ) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody ApplicationUser user) {
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            applicationUserRepository.save(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<ApplicationUser> getCurrentUser()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ApplicationUser user = userDetailsService.findByUserName(username);
        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/test", produces = {"application/hal+json"})
    public ResponseEntity<ApplicationUser> testMethod() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ApplicationUser user = userDetailsService.findByUserName(username);
        long id = user.getId();
        Link selfLink = linkTo(UserController.class).slash(id).withSelfRel();
        user.add(selfLink);

        if(festivalService.findByUserId(id).size() > 0) {
            Link eventsLink = linkTo(methodOn(FestivalController.class)
            .getAllByUserId(id)).withRel("allEvents");
            user.add(eventsLink);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
