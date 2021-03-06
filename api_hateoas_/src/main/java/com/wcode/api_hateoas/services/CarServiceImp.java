package com.wcode.api_hateoas.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Car> getAllCarsPagination(Pageable pageable) {
		Page<Car> page = this.carRepository.findAll(pageable);
		return page;
	}

	@Override
	public Page<Car> getAllCarsPaginationByModel(String model, Pageable pageable) {
		System.out.println("Model: " + model);
		Page<Car> page = this.carRepository.findCarPaginationByModel(model, pageable);
		return page;
		// System.out.println("Tamanho: " + page.getSize());
		// if (page.getSize() > 0) {
		// System.out.println("TEM");
		//
		// for(int i = 0; i < page.getSize(); i++){
		// System.out.println("Model: " + page.);
		// }
		//
		// return page;
		// } else {
		// System.out.println("NÃO TEM");
		// return null;
		// }

	}

	@Override
	public Optional<Car> getOneCar(Long id) {
		Optional<Car> car = this.carRepository.findById(id);
		return car;
	}

	@Override
	public Car updateCar(Long idCar, Car car) {
		Car carExist = this.getOneCar(idCar).get();
		if (carExist == null) {
			return null;
		} else {
			carExist.setIdCar(idCar);
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
