package com.verteiltesys.wgplanspringbootmysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class Controller {


    @Autowired
    private ServiceClass service;


    @GetMapping("/aufgabe/0") //GetAll
    public List<Aufgabe> list() {
        System.out.println("#################### GET ALL REQEST #######################");
       return service.listAll();
    }


    @GetMapping("/aufgabe/{id}") //GetByID
    public ResponseEntity<Aufgabe> get(@PathVariable Integer id){
        try {
            Aufgabe aufgabe = service.get(id);
            System.out.println("#################### GET BY ID REQEST #####################");
            return new ResponseEntity<Aufgabe>(aufgabe, HttpStatus.OK); //Return Element when found
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Aufgabe>(HttpStatus.NOT_FOUND); //404 When element not found
        }
    }


    @PostMapping("/aufgabe") //Post
    public void add(@RequestBody Aufgabe aufgabe){
        System.out.println("################### POST #######################");
        service.save(aufgabe);
    }


    @PutMapping("/aufgabe/{id}") //Put
    public ResponseEntity<?> update(@RequestBody Aufgabe aufgabe, @PathVariable Integer id) {
        try {
            Aufgabe existAufgabe = service.get(id);
            service.save(aufgabe);
            System.out.println("#################### PUT BY ID REQEST #####################");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            System.out.println("################### 404 NOT FOUND #######################");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/aufgabe/{id}") //Delete
    public void delete(@PathVariable Integer id) {
        System.out.println("################### DELETE BY ID REQEST #######################");
        service.delete(id);
    }


}
