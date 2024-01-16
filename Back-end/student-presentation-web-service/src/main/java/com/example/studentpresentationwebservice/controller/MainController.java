package com.example.studentpresentationwebservice.controller;

import com.example.studentpresentationwebservice.entity.PresentationEntity;
import com.example.studentpresentationwebservice.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/test")
public class MainController {
    @Autowired
    private PresentationService presentationService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewPresentation (@RequestParam Integer presId, @RequestParam Integer studentId, @RequestParam String mode, @RequestParam Integer teacherId, @RequestParam Integer tutorId){
        PresentationEntity p = new PresentationEntity();
        p.setPresId(presId);
        p.setStudentId(studentId);
        p.setMode(mode);
        p.setTeacherId(teacherId);
        p.setTutorId(tutorId);
        System.out.println(p);
        presentationService.save(p);
//        presentationRepository.save(p);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<PresentationEntity> getAllUsers() {
        return presentationService.getAll();
//        return presentationRepository.findAll();
    }
}
