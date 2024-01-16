package com.example.studentpresentationwebservice.repository;

import com.example.studentpresentationwebservice.entity.PresentationEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PresentationRepository extends JpaRepository<PresentationEntity, Integer> {

}

