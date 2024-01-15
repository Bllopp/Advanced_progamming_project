package com.example.studentpresentationwebservice.service;

import com.example.studentpresentationwebservice.entity.PresentationEntity;
import com.example.studentpresentationwebservice.repository.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class PresentationService {
    @Autowired
    PresentationRepository presentationRepository;

    public Iterable<PresentationEntity> getAll(){
        return presentationRepository.findAll();
    }

    public void save(PresentationEntity p){
        presentationRepository.save(p);
    }


}
