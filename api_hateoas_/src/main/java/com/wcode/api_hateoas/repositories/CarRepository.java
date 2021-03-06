package com.wcode.api_hateoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wcode.api_hateoas.models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

//	@Query("SELECT c FROM Car c WHERE c.modelo LIKE LOWER(CONCAT ('%', :model ,'%'))")
	@Query("SELECT p FROM Car p WHERE p.modelo LIKE LOWER(CONCAT ('%', :model, '%'))")
	Page<Car> findCarPaginationByModel(@Param("model") String model, Pageable pageable);

}
