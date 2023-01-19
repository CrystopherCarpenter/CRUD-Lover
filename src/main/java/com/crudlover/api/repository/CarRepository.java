package com.crudlover.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudlover.api.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}