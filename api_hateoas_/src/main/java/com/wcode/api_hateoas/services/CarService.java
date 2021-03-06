package com.wcode.api_hateoas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wcode.api_hateoas.models.Car;

public interface CarService {

	public List<Car> getAllCars();

	public Page<Car> getAllCarsPagination(Pageable pageable);

	public Page<Car> getAllCarsPaginationByModel(String model, Pageable pageable);

	public Optional<Car> getOneCar(Long id);

	public Car updateCar(Long id, Car car);

	public void removeCar(Long id);

	public Car addCar(Car car);

}
