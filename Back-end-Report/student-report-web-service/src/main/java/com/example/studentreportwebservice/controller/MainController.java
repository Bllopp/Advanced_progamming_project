package com.example.studentreportwebservice.controller;

import com.example.studentreportwebservice.domain.SubmitBody;
import com.example.studentreportwebservice.entity.ReportEntity;
import com.example.studentreportwebservice.service.ReportService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.jsonwebtoken.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path = "/reports")
public class MainController {

    @Autowired
    private ReportService reportService;

    private Jws<Claims> checkToken(String token) throws Exception {
        Jws<Claims> jws;

        byte[] secret = "randomKeyForHS512Algorithm123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890".getBytes();

        try{
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            jws = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
        }catch (ExpiredJwtException e){
            throw new Exception(e);
        }catch (UnsupportedJwtException | MalformedJwtException e) {
            // The JWT was not correctly constructed and should be rejected
            throw new Exception(e);
        } catch (JwtException e) {
            // Any other JWT related exception
            throw new Exception(e);
        } catch (IllegalArgumentException e) {
            // The JWT token is null or empty and should be rejected
            throw new Exception(e);
        }
        return jws;
    }

    @Operation(summary = "Submit a report", description = "Submit a report with the provided information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReportEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PostMapping(path="/submit")
    public @ResponseBody String submitReport (@RequestHeader("Authorization") String token, @ModelAttribute SubmitBody body) throws Exception{
        Jws<Claims> jws = checkToken(token);

       return reportService.submit(body);

    }

    @Operation(summary = "Download a report by student ID", description = "Download a report by providing the student ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReportService.class), mediaType = "application/pdf") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/download/{studentId}")
    public ResponseEntity<byte[]> downloadReport(@RequestHeader("Authorization") String token, @PathVariable Integer studentId) throws Exception {
        Jws<Claims> jws = checkToken(token);

        return reportService.download(studentId);
    }

    @Operation(summary = "Validate a report by student ID", description = "Validate a report by providing the student ID")
    @PostMapping(path = "/validate/studentId")
    public @ResponseBody String validateReport(
            @RequestHeader String token,
            @PathVariable Integer studentId
    ) throws Exception {
        Jws<Claims> jws = checkToken(token);
        return reportService.validate(studentId);


    }

    @Operation(summary = "Submit a teacher vote", description = "Submit a teacher vote for a student report")
    @PostMapping("/vote/{role}/{studentId}")
    public @ResponseBody String submitTeacherVote(@RequestHeader("Authorization") String token, @PathVariable("studentId") Integer studentId,@PathVariable("role") String role, @RequestBody Integer vote) throws Exception{
        Jws<Claims> jws = checkToken(token);

        return reportService.submitVote(studentId, role, vote);
    }

//    @PostMapping("/vote/tutor/{studentId}")
//    public @ResponseBody String submitTutorVote(@PathVariable Integer studentId, @RequestParam Integer tutorVote) {
//        try{
//            Optional<ReportEntity> optionalReport = reportService.getReportById(studentId);
//
//            if (optionalReport.isPresent()) {
//                ReportEntity report = optionalReport.get();
//                report.setTutorVote(tutorVote);
//                reportService.save(report);
//
//                return "Tutor vote successfully submitted!";
//            } else {
//                return "Report not found for studentId: " + studentId;
//            }
//        } catch (Exception e) {
//            return "Error submitting tutor vote: " + e.getMessage();
//        }
//    }
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

    @Operation(summary = "Get all reports", description = "Get a list of all reports")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/all")
    public @ResponseBody Iterable<ReportEntity> getAllReports(@RequestHeader("Authorization") String token) throws Exception {
        Jws<Claims> jws = checkToken(token);
        return reportService.getAll();
//        return reportRepository.findAll();
    }
}
