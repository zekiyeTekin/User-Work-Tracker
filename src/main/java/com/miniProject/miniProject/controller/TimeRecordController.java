package com.miniProject.miniProject.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniProject.miniProject.dto.AssignmentForFuncDto;
import com.miniProject.miniProject.dto.TimeRecordDto;
import com.miniProject.miniProject.entity.TimeRecord;
import com.miniProject.miniProject.filter.TimeRecordFilter;
import com.miniProject.miniProject.implementation.TimeRecordService;
import com.miniProject.miniProject.repository.TimeRecordRepository;
import com.miniProject.miniProject.specification.TimeRecordSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/record")
public class TimeRecordController {

    @Autowired
    TimeRecordService timeRecordService;

    //http://localhost:8081/record/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<TimeRecordDto>> getAllRecord() {
        return timeRecordService.getAllRecord();
    }

    //http://localhost:8081/record/getRecords?page=0&size=3
    @GetMapping("/getRecords")
    public ResponseEntity<Page<TimeRecordDto>> getAllRecordWithPagination(Pageable pageable) {
        return timeRecordService.getAllRecordWithPagination(pageable);
    }

    //http://localhost:8081/record/getByDate?date=28-03-2024
    @GetMapping("/getByDate")
    public ResponseEntity<List<TimeRecordDto>> getRecordByDate(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return timeRecordService.getRecordByDate(date);
    }


    //http://localhost:8081/record/filter/date
    @PostMapping("/filter/date")
    public ResponseEntity<List<TimeRecordDto>> recordByDateWithFilter(@RequestBody TimeRecordFilter timeRecordFilter) {
        return timeRecordService.recordByDateWithFilter(timeRecordFilter);
    }

    //http://localhost:8081/record/getBetweenDate?startDate=10-03-2024&endDate=20-03-2024
    @GetMapping("/getBetweenDate")
    public ResponseEntity<List<TimeRecordDto>> getRecordByTwoDate(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
        return timeRecordService.getRecordByTwoDate(startDate, endDate);
    }

    //http://http://localhost:8081/record/get/assignment?page=0&size=2
    @PostMapping("/get/assignment")
    public ResponseEntity<Page<TimeRecordDto>> getRecordByAssignment(@RequestBody TimeRecordFilter timeRecordFilter, Pageable pageable) {
        return timeRecordService.getRecordByAssignment(timeRecordFilter, pageable);
    }

    //http://localhost:8081/record/get/recordByUser?id=2
    @GetMapping("/get/recordByUser")
    public ResponseEntity<List<AssignmentForFuncDto>> getRecordInfoByUser(@RequestParam Integer id) {
        return timeRecordService.getRecordInfoByUser(id);
    }

    //http://localhost:8081/record/get/allObject?page=0&size=3
    @PostMapping("/get/allObject")
    public ResponseEntity<Page<TimeRecordDto>> getRecordWithAll(@RequestBody TimeRecordFilter timeRecordFilter, Pageable pageable) {
        return timeRecordService.getRecordWithAll(timeRecordFilter, pageable);
    }

}
