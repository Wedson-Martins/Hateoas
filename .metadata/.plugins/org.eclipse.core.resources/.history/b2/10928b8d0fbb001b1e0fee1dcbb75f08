package com.wcode.api_hateoas.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcode.api_hateoas.models.Car;
import com.wcode.api_hateoas.repositories.CarRepository;

@Service
public class CarServiceImp implements CarService {

	@Autowired
	CarRepository carRepository;

	@Override
	public List<Car> getAllCars() {
		return this.carRepository.findAll();
	}

	@Override
	public Optional<Car> getOneCar(Long id) {
		Optional<Car> car = this.carRepository.findById(id);
		return car;
	}

	@Override
	public Car updateCar(Long id, Car car) {
		Car carExist = this.getOneCar(id).get();
		if (carExist == null) {
			return null;
		} else {
			carExist.setId(id);
			carExist.setFabricante(car.getFabricante());
			carExist.setModelo(car.getModelo());
			carExist.setAno(car.getAno());
			return this.carRepository.save(carExist);
		}
	}

	@Override
	public void removeCar(Long id) {
		this.carRepository.deleteById(id);
	}

	@Override
	public Car addCar(Car car) {
		return this.carRepository.save(car);
	}

}
