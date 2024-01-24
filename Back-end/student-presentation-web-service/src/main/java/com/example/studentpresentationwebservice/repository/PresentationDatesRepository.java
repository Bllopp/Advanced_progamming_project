package com.example.studentpresentationwebservice.repository;

import com.example.studentpresentationwebservice.entity.PresentationDatesEntity;
import com.example.studentpresentationwebservice.entity.PresentationDatesId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresentationDatesRepository extends CrudRepository<PresentationDatesEntity, PresentationDatesId> {

    List<PresentationDatesEntity> findByDateIdPresId (Integer presId);

    List<PresentationDatesEntity> findByDateIdDate (Date date);
    Optional<PresentationDatesEntity> findByDateId(PresentationDatesId datesId);


    void flush();
}
