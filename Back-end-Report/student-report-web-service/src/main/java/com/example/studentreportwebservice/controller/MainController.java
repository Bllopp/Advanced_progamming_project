package com.example.studentreportwebservice.controller;

import com.example.studentreportwebservice.entity.ReportEntity;
import com.example.studentreportwebservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/test")
public class MainController {
    @Autowired
    private ReportService reportService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewReport (@RequestParam Integer studentId, @RequestParam byte[] file, @RequestParam Integer teacherId, @RequestParam Integer teacherVote, @RequestParam Integer tutorId, @RequestParam Integer tutorVote){
        ReportEntity p = new ReportEntity();
        p.setStudentId(studentId);
        p.setFile(file);
        p.setTeacherId(teacherId);
        p.setTeacherVote(teacherVote);
        p.setTutorId(tutorId);
        p.setTutorVote(tutorVote);
        System.out.println(p);
        reportService.save(p);
//        reportRepository.save(p);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<ReportEntity> getAllUsers() {
        return reportService.getAll();
//        return reportRepository.findAll();
    }
}
