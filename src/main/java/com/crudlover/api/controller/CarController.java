package com.crudlover.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crudlover.api.dto.CarDTO;
import com.crudlover.api.model.Car;
import com.crudlover.api.service.CarService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody @Valid CarDTO req) {
        service.save(new Car(req));
    }

    @GetMapping
    public List<Car> listAll(@PageableDefault(page = 0) Pageable page) {
        return service.findAll(page.getPageNumber());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable long id) {
        var car = service.findById(id);

        return (car != null) ? ResponseEntity.ok().body(car)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable long id, @RequestBody @Valid CarDTO req) {
        var car = service.update(id, req);

        return (car != null) ? ResponseEntity.ok().body(car)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }

}
