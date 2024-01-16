package com.example.studentpresentationwebservice.repository;

import com.example.studentpresentationwebservice.entity.PresentationDatesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationDatesRepository extends CrudRepository<PresentationDatesEntity, Integer> {

}
