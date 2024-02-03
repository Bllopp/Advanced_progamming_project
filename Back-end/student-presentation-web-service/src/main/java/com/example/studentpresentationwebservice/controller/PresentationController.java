package com.example.studentpresentationwebservice.controller;

import com.example.studentpresentationwebservice.domain.PresentationBody;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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

    @Operation(summary = "Initialize presentation", description = "Initialize a new presentation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Presentation initialized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(path = "/add")
    public @ResponseBody String init_presentation(@RequestBody PresentationBody presBody) {
        try {


            PresentationEntity pE = presentationService.createPresentation(presBody);
            Integer presId = pE.getPresId();

            presentationDateService.save(presId, presBody.getDate1(), 0, 0);
            presentationDateService.save(presId, presBody.getDate2(), 0, 0);
            presentationDateService.save(presId, presBody.getDate3(), 0, 0);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Presentation initialized";
    }

    @Operation(summary = "Get presentations by user ID", description = "Get presentations by user ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of presentations"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{userId}")
    public @ResponseBody List<PresentationEntity> getPresByUserId(@PathVariable("userId") Integer userId){
        List<PresentationEntity> filteredPres = presentationService.getById(userId);


        return filteredPres;
    }

    @Operation(summary = "Get presentation vote by presentation ID", description = "Get presentation vote by presentation ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Presentation vote"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/vote/{presId}")
    public @ResponseBody List<PresentationDatesEntity> getPresVote(@PathVariable("presId") Integer presId){
        return presentationDateService.getById(presId);
    }

    @Operation(summary = "Get presentation vote by presentation ID and date", description = "Get presentation vote by presentation ID and date")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Presentation vote"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/vote/{presId}/{date}")
    public @ResponseBody Optional<PresentationDatesEntity> getPresVote2(@PathVariable("presId") Integer presId, @PathVariable("date") String date){
        return presentationDateService.getByIdDates(presId, date);
    }


    @Operation(summary = "Add new vote for presentation", description = "Add a new vote for a presentation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vote added successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Transactional
    @PostMapping("/vote/{presId}/add")
    public @ResponseBody String newVote(@RequestBody VoteBody body, @PathVariable Integer presId){
       return presentationDateService.create3Vote(body,presId);
    }

    @Operation(summary = "Modify vote for presentation", description = "Modify vote for a presentation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vote modified successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Transactional
    @PostMapping("/vote/{presId}")
    public @ResponseBody String modifyVote(@RequestBody VoteBody body, @PathVariable Integer presId){
        return presentationDateService.update3Votes(body,presId);
    }


}
