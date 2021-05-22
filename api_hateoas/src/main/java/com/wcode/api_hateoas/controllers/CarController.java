package com.wcode.api_hateoas.controllers;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcode.api_hateoas.models.Car;
import com.wcode.api_hateoas.services.CarService;

@RestController
@RequestMapping("/")
public class CarController {

	@Autowired
	CarService carService;

	@PostMapping(produces = { "application/json", "application/xml" }, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> addCar(@RequestBody Car car) {
		Car newCar = this.carService.addCar(car);
		if (newCar == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Car>(newCar, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/test", produces = { "application/json;charset=utf-8", "application/xml;charset=utf-8" })
	public List<Car> teste() {
		List<Car> cars = this.carService.getAllCars();
		return cars;
	}

	@GetMapping(value = "/cars",produces = { "application/json;charset=utf-8", "application/xml;charset=utf-8" })
	public ResponseEntity<?> getAllCars() {
		List<Car> cars = this.carService.getAllCars();
		if (cars.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {

			for (Car car : cars) {
				Long id = car.getId();
				car.add(linkTo(methodOn(CarController.class).getOneCars(id)).withSelfRel());
			}

			return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/cars/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> getOneCars(@PathVariable(value = "id") Long id) {
		Optional<Car> car = this.carService.getOneCar(id);
		if (!car.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			car.get().add(linkTo(methodOn(CarController.class).getAllCars()).withRel("Car list"));
			return new ResponseEntity<Car>(car.get(), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/cars/{id}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<?> updateCar(@PathVariable(value = "id") Long id, @RequestBody Car car) {
		Car carExist = this.carService.updateCar(id, car);
		if (carExist == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Car>(carExist, HttpStatus.OK);
		}
	}

	@DeleteMapping("/cars/{id}")
	public void removeCar(@PathVariable(value = "id") Long id) {
		this.carService.removeCar(id);
	}

}