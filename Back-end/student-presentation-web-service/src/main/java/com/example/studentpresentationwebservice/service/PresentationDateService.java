package com.example.studentpresentationwebservice.service;

import com.example.studentpresentationwebservice.entity.PresentationDatesEntity;
import com.example.studentpresentationwebservice.entity.PresentationEntity;
import com.example.studentpresentationwebservice.repository.PresentationDatesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PresentationDateService {

    @Autowired
    private PresentationDatesRepository presentationDatesRepository;

    @Transactional
    public @ResponseBody PresentationDatesEntity save(Integer presId, String date, Integer teacherVote, Integer tutorVote){
        PresentationDatesEntity pde = new PresentationDatesEntity();
        pde.setPresId(presId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        try {
            pde.setDate(formatter.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        pde.setTeacherVote(teacherVote);
        pde.setTutorVote(tutorVote);
        return presentationDatesRepository.save(pde);
    }

    @Transactional
    public @ResponseBody List<PresentationDatesEntity> getById(Integer presId){
        List<PresentationDatesEntity> dates = presentationDatesRepository.findByPresId(presId);
        return dates;
    }



}
