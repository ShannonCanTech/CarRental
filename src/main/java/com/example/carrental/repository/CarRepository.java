package com.example.carrental.repository;

import com.example.carrental.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

//    Iterable<Car> findAllByModelContainingIgnoreCase(String partial);
}
