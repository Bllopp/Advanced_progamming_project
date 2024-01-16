package com.example.studentpresentationwebservice.service;

import com.example.studentpresentationwebservice.entity.PresentationEntity;
import com.example.studentpresentationwebservice.repository.PresentationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class PresentationService {
    @Autowired
    PresentationRepository presentationRepository;

    @Transactional
    public Iterable<PresentationEntity> getAll(){
        return presentationRepository.findAll();
    }

    @Transactional
    public void save(PresentationEntity p){
        presentationRepository.save(p);
    }


}
