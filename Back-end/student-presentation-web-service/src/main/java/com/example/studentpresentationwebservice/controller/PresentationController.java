package com.example.studentpresentationwebservice.controller;

import com.example.studentpresentationwebservice.entity.PresentationDatesEntity;
import com.example.studentpresentationwebservice.entity.PresentationEntity;
import com.example.studentpresentationwebservice.repository.PresentationDatesRepository;
import com.example.studentpresentationwebservice.service.PresentationDateService;
import com.example.studentpresentationwebservice.service.PresentationService;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/presentation")
public class PresentationController {

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private PresentationDateService presentationDateService;

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

}
