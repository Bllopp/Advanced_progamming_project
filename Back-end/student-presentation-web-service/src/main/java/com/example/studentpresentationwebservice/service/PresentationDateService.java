package com.example.studentpresentationwebservice.service;

import com.example.studentpresentationwebservice.entity.PresentationDatesEntity;
import com.example.studentpresentationwebservice.entity.PresentationDatesId;
import com.example.studentpresentationwebservice.entity.PresentationEntity;
import com.example.studentpresentationwebservice.repository.PresentationDatesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        try {
            PresentationDatesId pdeId = new PresentationDatesId(presId,formatter.parse(date));
            pde.setDateId(pdeId);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        pde.setTeacherVote(teacherVote);
        pde.setTutorVote(tutorVote);
        return presentationDatesRepository.save(pde);
    }

    @Transactional
    public @ResponseBody List<PresentationDatesEntity> getById(Integer presId){
        List<PresentationDatesEntity> dates = presentationDatesRepository.findByDateIdPresId(presId);
        return dates;
    }

    @Transactional
    public @ResponseBody Optional<PresentationDatesEntity> getByIdDates(Integer presId, String date){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        try {
            Date formattedDate =  formatter.parse(date);
            PresentationDatesId dateId = new PresentationDatesId(presId,formattedDate);
            Optional<PresentationDatesEntity> dateEntity = presentationDatesRepository.findByDateId(dateId);
            return dateEntity;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }




}
