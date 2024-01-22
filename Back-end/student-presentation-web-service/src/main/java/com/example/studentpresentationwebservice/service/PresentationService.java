package com.example.studentpresentationwebservice.service;

import com.example.studentpresentationwebservice.entity.PresentationEntity;
import com.example.studentpresentationwebservice.repository.PresentationRepository;
import jakarta.persistence.Temporal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public  class PresentationService {
    @Autowired
    PresentationRepository presentationRepository;

    @Transactional
    public Iterable<PresentationEntity> getAll(){
        return presentationRepository.findAll();
    }

    @Transactional
    public List<PresentationEntity> getById(Integer userId) {
        List<PresentationEntity> matchedPres = presentationRepository.findByStudentIdOrTeacherIdOrTutorId(userId,userId,userId);
        List<PresentationEntity> filteredPres = matchedPres.stream().filter(presentationEntity -> presentationEntity.getFinalDate() == null ).toList();
        return filteredPres;
    }


    @Transactional
    public @ResponseBody PresentationEntity createPresentation( Integer studentId,
                                                     String mode,
                                                     Integer teacherId,
                                                     Integer tutorId)
            throws Exception
    {
        PresentationEntity p = new PresentationEntity();
        p.setStudentId(studentId);
        p.setMode(mode);
        p.setTeacherId(teacherId);
        p.setTutorId(tutorId);
        return presentationRepository.save(p);
    }
}
