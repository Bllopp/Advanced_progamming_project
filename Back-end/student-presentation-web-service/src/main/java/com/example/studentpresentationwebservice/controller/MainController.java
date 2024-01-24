package com.example.studentpresentationwebservice.controller;

import com.example.studentpresentationwebservice.entity.PresentationDatesEntity;
import com.example.studentpresentationwebservice.entity.PresentationEntity;
import com.example.studentpresentationwebservice.repository.PresentationDatesRepository;
import com.example.studentpresentationwebservice.repository.PresentationRepository;
import com.example.studentpresentationwebservice.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping(path = "/test")
public class MainController {
//    @Autowired
//    private PresentationRepository presentationRepository;
//
//    @Autowired
//    private PresentationDatesRepository presentationDatesRepository;

//    @PostMapping("/addDates")
//    public @ResponseBody String addNewPresentationDate (@RequestParam Integer presId, @RequestParam String date, @RequestParam Integer teacherVote, @RequestParam Integer tutorVote) throws ParseException {
//        PresentationDatesEntity pde = new PresentationDatesEntity();
//        pde.setPresId(presId);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
//        try {
//            pde.setDate(formatter.parse(date));
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        pde.setTeacherVote(teacherVote);
//        pde.setTutorVote(tutorVote);
//
//        return "Saved";
//    }

//    @PostMapping(path="/add")
//    public @ResponseBody String addNewPresentation (@RequestParam Integer presId, @RequestParam Integer studentId, @RequestParam String mode, @RequestParam Integer teacherId, @RequestParam Integer tutorId){
//        PresentationEntity p = new PresentationEntity();
//        p.setPresId(presId);
//        p.setStudentId(studentId);
//        p.setMode(mode);
//        p.setTeacherId(teacherId);
//        p.setTutorId(tutorId);
//        System.out.println(p);
//
//       presentationRepository.save(p);
//        return "Saved";
//    }

//    @GetMapping("/all")
//    public @ResponseBody Iterable<PresentationEntity> getAllUsers() {
//
//        return presentationRepository.findAll();
//    }
}
