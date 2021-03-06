package com.wcode.api_hateoas.controllers;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wcode.api_hateoas.models.Car;
import com.wcode.api_hateoas.services.CarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CarEndPoint")
@RestController
@RequestMapping
public class CarController {

	@Autowired
	CarService carService;

	@ApiOperation("Add Car")
	@PostMapping(produces = { "application/json", "application/xml" }, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> addCar(@RequestBody Car car) {

		Car newCar = this.carService.addCar(car);
		if (newCar == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Car>(newCar, HttpStatus.OK);
		}
	}

	@ApiOperation("get all cars")
	@GetMapping(value = "/cars", produces = { "application/json;charset=utf-8", "application/xml;charset=utf-8" })
	public ResponseEntity<?> getAllCars() {
		List<Car> cars = this.carService.getAllCars();
		if (cars.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {

			for (Car car : cars) {
				Long idCar = car.getIdCar();
				car.add(linkTo(methodOn(CarController.class).getOneCars(idCar)).withSelfRel());
			}

			return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
		}
	}

	@ApiOperation("Get all Cars pagination")
	@GetMapping(value = "/cars_pagination", produces = { "application/json;charset=utf-8",
			"application/xml;charset=utf-8" })
	public ResponseEntity<PagedResources<Car>> getAllCarsPagination(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {

		Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "modelo"));

		Page<Car> cars = this.carService.getAllCarsPagination(pageable);
		if (cars.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {

			for (Car car : cars) {
				Long idCar = car.getIdCar();
				car.add(linkTo(methodOn(CarController.class).getOneCars(idCar)).withSelfRel());
			}

			return new ResponseEntity<>(assembler.toResource(cars), HttpStatus.OK);
		}
	}

	@ApiOperation("Get all Cars pagination by Model")
	@GetMapping(value = "/cars_pagination_by_model/{model}", produces = { "application/json;charset=utf-8",
			"application/xml;charset=utf-8" })
	public ResponseEntity<PagedResources<Car>> getAllCarsPaginationByModel(@PathVariable(value = "model") String model,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {

		Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "modelo"));

		Page<Car> cars = this.carService.getAllCarsPaginationByModel(model, pageable);
		if (cars.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {

			for (Car car : cars) {
				Long idCar = car.getIdCar();
				car.add(linkTo(methodOn(CarController.class).getOneCars(idCar)).withSelfRel());
			}
			return new ResponseEntity<>(assembler.toResource(cars), HttpStatus.OK);
		}
	}

	@ApiOperation("Get Car by ID")
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

	@ApiOperation("Update Car by ID")
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

	@ApiOperation("Delete Car by ID")
	@DeleteMapping("/cars/{id}")
	public void removeCar(@PathVariable(value = "id") Long id) {
		this.carService.removeCar(id);
	}

}
