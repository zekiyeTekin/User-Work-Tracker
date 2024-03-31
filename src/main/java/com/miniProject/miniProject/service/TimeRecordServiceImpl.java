package com.miniProject.miniProject.service;

import com.miniProject.miniProject.dto.TimeRecordDto;
import com.miniProject.miniProject.entity.TimeRecord;
import com.miniProject.miniProject.filter.TimeRecordFilter;
import com.miniProject.miniProject.implementation.TimeRecordService;
import com.miniProject.miniProject.mapper.TimeRecordMapper;
import com.miniProject.miniProject.repository.TimeRecordRepository;
import com.miniProject.miniProject.specification.ProjectSpecifications;
import com.miniProject.miniProject.specification.TimeRecordSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    @Autowired
    TimeRecordRepository timeRecordRepository;

    @Autowired
    TimeRecordMapper timeRecordMapper;

    public ResponseEntity<List<TimeRecordDto>> getAllRecord(){
        try{
            List<TimeRecord> timeRecordList = timeRecordRepository.findAll();
            if(timeRecordList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordList), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Page<TimeRecordDto>> getAllRecordWithPagination(Pageable pageable){

        Page<TimeRecord> timeRecordList = timeRecordRepository.findAll(pageable);
        if(timeRecordList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(timeRecordMapper.mapPage(timeRecordList), HttpStatus.OK);
    }


    public ResponseEntity<List<TimeRecordDto>> getRecordByDate(Date date){
        try{
            List<TimeRecord> timeRecordList = timeRecordRepository.findByDate(date);
            if(timeRecordList.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordList),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<TimeRecordDto>> getRecordByDateWithFilter(TimeRecordFilter timeRecordFilter){
           /*
            List<TimeRecord> timeRecordList =timeRecordRepository.findAll(TimeRecordSpecification.searchRecordByDate(timeRecordFilter));
            if(timeRecordList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordList),HttpStatus.OK);
            */
        return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordRepository.findAll(TimeRecordSpecification.searchRecordByDate(timeRecordFilter))),HttpStatus.OK);
    }

    public ResponseEntity<List<TimeRecordDto>> getRecordByAssignment(TimeRecordFilter timeRecordFilter){
        return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordRepository.findAll(TimeRecordSpecification.searchRecordByAssignment(timeRecordFilter))),HttpStatus.OK);

    }


    public ResponseEntity<List<TimeRecordDto>> getRecordByTwoDate(Date startDate, Date endDate){
        try{
            List<TimeRecord> timeRecordList = timeRecordRepository.findByDateBetween(startDate,endDate);
            if(timeRecordList.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(timeRecordMapper.convertList(timeRecordList),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }



}
