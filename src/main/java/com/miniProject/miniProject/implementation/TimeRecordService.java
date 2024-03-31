package com.miniProject.miniProject.implementation;

import com.miniProject.miniProject.dto.TimeRecordDto;
import com.miniProject.miniProject.entity.Assignment;
import com.miniProject.miniProject.entity.TimeRecord;
import com.miniProject.miniProject.filter.TimeRecordFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface TimeRecordService {

    ResponseEntity<List<TimeRecordDto>> getAllRecord();
    ResponseEntity<Page<TimeRecordDto>> getAllRecordWithPagination(Pageable pageable);

    ResponseEntity<List<TimeRecordDto>> getRecordByDate(Date date);

    ResponseEntity<List<TimeRecordDto>> getRecordByDateWithFilter(TimeRecordFilter timeRecordFilter);

    ResponseEntity<List<TimeRecordDto>> getRecordByAssignment(TimeRecordFilter timeRecordFilter);

    ResponseEntity<List<TimeRecordDto>> getRecordByTwoDate(Date startDate, Date endDate);



}
