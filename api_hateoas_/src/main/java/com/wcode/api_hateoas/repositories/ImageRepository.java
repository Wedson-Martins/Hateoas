package com.wcode.api_hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcode.api_hateoas.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
