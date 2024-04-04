package com.miniProject.miniProject.implementation;

import com.miniProject.miniProject.dto.AssignmentForFuncDto;
import com.miniProject.miniProject.dto.TimeRecordDto;
import com.miniProject.miniProject.entity.Assignment;
import com.miniProject.miniProject.entity.TimeRecord;
import com.miniProject.miniProject.filter.TimeRecordFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TimeRecordService {

    ResponseEntity<List<TimeRecordDto>> getAllRecord();
    ResponseEntity<Page<TimeRecordDto>> getAllRecordWithPagination(Pageable pageable);

    ResponseEntity<List<TimeRecordDto>> getRecordByDate(LocalDate date);

    ResponseEntity<List<TimeRecordDto>> recordByDateWithFilter(TimeRecordFilter timeRecordFilter);

    ResponseEntity<Page<TimeRecordDto>> getRecordByAssignment(TimeRecordFilter timeRecordFilter, Pageable pageable);

    ResponseEntity<List<TimeRecordDto>> getRecordByTwoDate(LocalDate startDate, LocalDate endDate);

    ResponseEntity<List<AssignmentForFuncDto>> getRecordInfoByUser(Integer id);

    ResponseEntity<Page<TimeRecordDto>> getRecordWithAll(TimeRecordFilter timeRecordFilter,Pageable pageable);



}
