package com.crudlover.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudlover.api.dto.CarsDTO;
import com.crudlover.api.model.Car;
import com.crudlover.api.repository.CarRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private CarRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid CarsDTO req) {
        repository.save(new Car(req));
    }

    @GetMapping
    public List<Car> listAll() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody @Valid CarsDTO req) {
        repository.findById(id).map(car -> {
            car.setModelo(req.modelo());
            car.setFabricante(req.fabricante());
            car.setDataFabricacao(req.dataFabricacao());
            car.setValor(req.valor());
            car.setAnoModelo(req.anoModelo());

            return repository.save(car);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }

}
