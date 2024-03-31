package com.miniProject.miniProject.mapper;

import com.miniProject.miniProject.dto.TimeRecordDto;
import com.miniProject.miniProject.entity.TimeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeRecordMapper {


    @Autowired
    AssignmentMapper assignmentMapper;
    public TimeRecordDto toDto(TimeRecord timeRecord){
        return new TimeRecordDto.Builder()
                .id(timeRecord.getId())
                .date(timeRecord.getDate())
                .time(timeRecord.getTime())
                .assignment(assignmentMapper.toDtoWithoutUser(timeRecord.getAssignment()))
                .build();

    }

    public List<TimeRecordDto> convertList(List<TimeRecord> timeRecord){

        List<TimeRecordDto> timeRecordList = new ArrayList<>();
        for (TimeRecord timeRecord1 : timeRecord){
            timeRecordList.add(toDto(timeRecord1));
        }
        return timeRecordList;
    }

    public Page<TimeRecordDto> mapPage(Page<TimeRecord> timeRecords){
        return new PageImpl<>(convertList(timeRecords.getContent()),timeRecords.getPageable(), timeRecords.getTotalElements());
    }


}
