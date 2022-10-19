package com.VegCity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VegCity.model.entity.IntoleranceEntity;

@Repository
public interface IntoleranceRepository extends JpaRepository<IntoleranceEntity, Long> {

}
