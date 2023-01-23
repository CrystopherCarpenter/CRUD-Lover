package com.crudlover.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.crudlover.api.dto.CarDTO;
import com.crudlover.api.model.Car;
import com.crudlover.api.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public void save(Car req) {
        repository.save(req);
    }

    public List<Car> findAll(int page) {
        PageRequest pagination = PageRequest.of(page, 5);

        return repository.findAll(pagination).getContent();
    }

    public Car findById(long id) {
        return repository.findById(id).map(car -> car).orElse(null);
    }

    public Car update(long id, CarDTO req) {
        return repository.findById(id).map(car -> {
            car.setModelo(req.modelo());
            car.setFabricante(req.fabricante());
            car.setDataFabricacao(req.dataFabricacao());
            car.setValor(req.valor());
            car.setAnoModelo(req.anoModelo());

            return repository.save(car);
        }).orElse(null);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

}
