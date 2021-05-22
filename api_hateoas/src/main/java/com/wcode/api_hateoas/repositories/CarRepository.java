package com.wcode.api_hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcode.api_hateoas.models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
