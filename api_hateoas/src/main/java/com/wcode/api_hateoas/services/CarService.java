package com.wcode.api_hateoas.services;

import java.util.List;
import java.util.Optional;

import com.wcode.api_hateoas.models.Car;

public interface CarService {

	public List<Car> getAllCars();

	public Optional<Car> getOneCar(Long id);

	public Car updateCar(Long id, Car car);

	public void removeCar(Long id);

	public Car addCar(Car car);

}
