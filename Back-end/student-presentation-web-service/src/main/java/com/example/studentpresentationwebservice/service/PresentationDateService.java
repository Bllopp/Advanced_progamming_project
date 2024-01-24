package com.example.studentpresentationwebservice.service;

import com.example.studentpresentationwebservice.domain.Vote;
import com.example.studentpresentationwebservice.domain.VoteBody;
import com.example.studentpresentationwebservice.entity.PresentationDatesEntity;
import com.example.studentpresentationwebservice.entity.PresentationDatesId;
import com.example.studentpresentationwebservice.repository.PresentationDatesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    @Transactional
    public @ResponseBody String create3Vote(VoteBody body, Integer presId){
        for (int i = 0; i < body.getVotes().size(); i++) {
            save(presId, body.getVotes().get(i).getDate(), 0, 0);
        }

        return "3 new dates have been proposed";
    }

    @Transactional
    public @ResponseBody String update3Votes(VoteBody body, Integer presId){
        for (int i = 0; i < body.getVotes().size(); i++) {
            try {
                Vote vote = body.getVotes().get(i);
                PresentationDatesEntity presentationDatesEntity = getByIdDates(presId, vote.getDate()).orElse(null);
                if(presentationDatesEntity != null){
                    if(body.getRole().equals("teacher")) {
                        presentationDatesEntity.setTeacherVote(vote.getVote());

                    }
                    else{
                        presentationDatesEntity.setTutorVote(vote.getVote());

                    }
                    presentationDatesRepository.save(presentationDatesEntity);

                }
            }
            catch(Exception e){
                e.printStackTrace();
            }


//            presentationDatesRepository.saveAll(datesArray);




        }
        return "the 3 vote have been saved";
    }




}
