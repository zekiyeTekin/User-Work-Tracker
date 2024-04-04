package com.miniProject.miniProject.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniProject.miniProject.dto.AssignmentDto;
import com.miniProject.miniProject.dto.AssignmentForFuncDto;
import com.miniProject.miniProject.dto.TimeRecordDto;
import com.miniProject.miniProject.entity.TimeRecord;
import com.miniProject.miniProject.filter.TimeRecordFilter;
import com.miniProject.miniProject.implementation.TimeRecordService;
import com.miniProject.miniProject.mapper.TimeRecordMapper;
import com.miniProject.miniProject.mapper.UserMapper;
import com.miniProject.miniProject.repository.TimeRecordRepository;
import com.miniProject.miniProject.specification.ProjectSpecifications;
import com.miniProject.miniProject.specification.TimeRecordSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    @Autowired
    TimeRecordRepository timeRecordRepository;

    @Autowired
    TimeRecordMapper timeRecordMapper;

    public ResponseEntity<List<TimeRecordDto>> getAllRecord() {
        try {
            List<TimeRecord> timeRecordList = timeRecordRepository.findAll();
            if (timeRecordList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordList), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Page<TimeRecordDto>> getAllRecordWithPagination(Pageable pageable) {

        Page<TimeRecord> timeRecordList = timeRecordRepository.findAll(pageable);
        if (timeRecordList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(timeRecordMapper.mapPage(timeRecordList), HttpStatus.OK);
    }


    public ResponseEntity<List<TimeRecordDto>> getRecordByDate(LocalDate date) {
        try {
            List<TimeRecord> timeRecordList = timeRecordRepository.findByDate(date);
            if (timeRecordList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordList), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<TimeRecordDto>> recordByDateWithFilter(TimeRecordFilter timeRecordFilter) {
           /*
            List<TimeRecord> timeRecordList =timeRecordRepository.findAll(TimeRecordSpecification.searchRecordByDate(timeRecordFilter));
            if(timeRecordList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordList),HttpStatus.OK);
            */
        return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordRepository.findAll(TimeRecordSpecification.searchRecordByDate(timeRecordFilter))), HttpStatus.OK);
    }

    public ResponseEntity<Page<TimeRecordDto>> getRecordByAssignment(TimeRecordFilter timeRecordFilter, Pageable pageable) {
        return new ResponseEntity<>(timeRecordMapper.mapPage(timeRecordRepository.findAll(TimeRecordSpecification.searchRecordByAssignment(timeRecordFilter), pageable)), HttpStatus.OK);

    }

    public ResponseEntity<List<TimeRecordDto>> getRecordByTwoDate(LocalDate startDate, LocalDate endDate) {
        try {
            List<TimeRecord> timeRecordList = timeRecordRepository.findByDateBetween(startDate, endDate);
            if (timeRecordList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordList), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    //kullanıcıya göre bir projede tüm günler dahil toplam kaç saat çalışmış
    public ResponseEntity<List<AssignmentForFuncDto>> getRecordInfoByUser(Integer id) {

        List<TimeRecord> timeRecordFind = timeRecordRepository.findByAssignment_User_Id(id);
        Integer totalHours = 0;

        List<AssignmentForFuncDto> assignmentForFuncDto = new ArrayList<>();

        for (TimeRecord timeRecord : timeRecordFind) {
            String userName = timeRecord.getAssignment().getUser().getName();
            String projectName = timeRecord.getAssignment().getProject().getName();
            LocalDate date = timeRecord.getDate();
            Integer time = timeRecord.getTime();

            totalHours += time;

            AssignmentForFuncDto info = new AssignmentForFuncDto();
            info.setUserName(userName);
            info.setProjectName(projectName);
            info.setDate(date);
            info.setTime(time);
            assignmentForFuncDto.add(info);
        }

        if (!assignmentForFuncDto.isEmpty()) {
            AssignmentForFuncDto totalInfo = new AssignmentForFuncDto();
            totalInfo.setTime(totalHours);
            assignmentForFuncDto.add(totalInfo);
            return ResponseEntity.ok(assignmentForFuncDto);
        } else {

            return ResponseEntity.notFound().build();
        }

    }


    public ResponseEntity<Page<TimeRecordDto>> getRecordWithAll(TimeRecordFilter timeRecordFilter, Pageable pageable) {


        return new ResponseEntity<>(timeRecordMapper.mapPage(timeRecordRepository.findAll(TimeRecordSpecification.searchRecordWithAll(timeRecordFilter), pageable)), HttpStatus.OK);


    }


}
