package com.example.studentreportwebservice.service;

import com.example.studentreportwebservice.entity.ReportEntity;
import com.example.studentreportwebservice.repository.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Transactional
    public Iterable<ReportEntity> getAll(){
        return reportRepository.findAll();
    }

    @Transactional
    public void save(ReportEntity p){
        reportRepository.save(p);
    }


}
