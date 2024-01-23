package com.example.studentpresentationwebservice.controller;

import com.example.studentpresentationwebservice.domain.Vote;
import com.example.studentpresentationwebservice.domain.VoteBody;
import com.example.studentpresentationwebservice.entity.PresentationDatesEntity;
import com.example.studentpresentationwebservice.entity.PresentationEntity;
import com.example.studentpresentationwebservice.repository.PresentationDatesRepository;
import com.example.studentpresentationwebservice.service.PresentationDateService;
import com.example.studentpresentationwebservice.service.PresentationService;
import jakarta.transaction.Transactional;
import org.hibernate.engine.jdbc.mutation.internal.PreparedStatementGroupNone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/presentation")
public class PresentationController {

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private PresentationDateService presentationDateService;

    @Autowired
    private PresentationDatesRepository presentationDatesRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String init_presentation(@RequestParam Integer studentId, @RequestParam String mode, @RequestParam Integer teacherId, @RequestParam Integer tutorId, @RequestParam String date1, @RequestParam String date2, @RequestParam String date3) {
        try {
            PresentationEntity pE = presentationService.createPresentation(studentId, mode, teacherId, tutorId);
            Integer presId = pE.getPresId();

            presentationDateService.save(presId, date1, 0, 0);
            presentationDateService.save(presId, date2, 0, 0);
            presentationDateService.save(presId, date3, 0, 0);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Presentation initialized";
    }

    @GetMapping("/{userId}")
    public @ResponseBody List<PresentationEntity> getPresByUserId(@PathVariable("userId") Integer userId){
        List<PresentationEntity> filteredPres = presentationService.getById(userId);


        return filteredPres;
    }

    @GetMapping("/vote/{presId}")
    public @ResponseBody List<PresentationDatesEntity> getPresVote(@PathVariable("presId") Integer presId){
        return presentationDateService.getById(presId);
    }

    @GetMapping("/vote/{presId}/{date}")
    public @ResponseBody Optional<PresentationDatesEntity> getPresVote2(@PathVariable("presId") Integer presId, @PathVariable("date") String date){
        return presentationDateService.getByIdDates(presId, date);
    }

    @Transactional
    @PostMapping("/vote/{presId}")
    public @ResponseBody String vote(@RequestBody VoteBody body, @PathVariable Integer presId){

        for (int i = 0; i < body.getVotes().size(); i++) {
            try {
                Vote vote = body.getVotes().get(i);
                PresentationDatesEntity presentationDatesEntity = presentationDateService.getByIdDates(presId, vote.getDate()).orElse(null);
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


        return "ok";
    }


}
