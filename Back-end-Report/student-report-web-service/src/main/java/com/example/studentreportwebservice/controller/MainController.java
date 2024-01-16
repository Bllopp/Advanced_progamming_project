package com.example.studentreportwebservice.controller;

import com.example.studentreportwebservice.entity.ReportEntity;
import com.example.studentreportwebservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Controller
//@RequestMapping(path = "/test")
@RequestMapping(path = "/reports")
public class MainController {
    @Autowired
    private ReportService reportService;

    @PostMapping(path="/send")
    public @ResponseBody String addNewReport (@RequestParam Integer studentId, @RequestParam MultipartFile file, @RequestParam Integer teacherId, @RequestParam Integer tutorId){

        try {
            if (file.isEmpty()) {
                return "The file you submitted is empty. Please submit a valid file.";
            }

        ReportEntity report = new ReportEntity();
        report.setStudentId(studentId);
        report.setFile(file.getBytes());
        report.setTeacherId(teacherId);
        report.setTutorId(tutorId);
        report.setUploadDate(new Date());
        System.out.println(report);
        reportService.save(report);
//        reportRepository.save(p);
        return "Report sent!";
    } catch (IOException e) {
            return "Error while processing the file: " + e.getMessage();
        }
    }

    @GetMapping("/get/{studentId}")
    public ResponseEntity<byte[]> getReport(@PathVariable Integer studentId) {
        Optional<ReportEntity> optionalReport = reportService.getReportById(studentId);

        if (optionalReport.isPresent()) {
            ReportEntity report = optionalReport.get();
            org.springframework.http.HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");
            return new ResponseEntity<>(report.getFile(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
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
    }*/

    @GetMapping("/all")
    public @ResponseBody Iterable<ReportEntity> getAllUsers() {
        return reportService.getAll();
//        return reportRepository.findAll();
    }
}
