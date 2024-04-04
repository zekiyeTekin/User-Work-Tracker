package com.miniProject.miniProject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class TimeRecordDto {

    private Integer id;
    private Integer time;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private AssignmentDto assignment;


    public TimeRecordDto(Integer id, Integer time, LocalDate date, AssignmentDto assignment){
        this.id=id;
        this.time=time;
        this.assignment=assignment;
        this.date=date;
    }


    public static class Builder{
        private Integer id;
        private  Integer time;
        private LocalDate date;
        private AssignmentDto assignment;


    public Builder(Integer id,Integer time, LocalDate date, AssignmentDto assignment){
        this.id=id;
        this.time=time;
        this.assignment=assignment;
        this.date=date;
    }

    public Builder(){}
    public Builder id(Integer id){
        this.id=id;
        return this;
    }
    public Builder time(Integer time){
        this.time=time;
        return this;
    }

    public Builder date(LocalDate date){
        this.date=date;
        return this;
    }

    public Builder assignment(AssignmentDto assignment){
        this.assignment=assignment;
        return this;
    }

    public TimeRecordDto build(){
       return new TimeRecordDto(id,time,date,assignment);
    }

    }
}
