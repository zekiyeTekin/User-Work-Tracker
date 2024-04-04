package com.miniProject.miniProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AssignmentForFuncDto {
    private String projectName;
    private String userName;
    private Integer time;
    private LocalDate date;

    public AssignmentForFuncDto(String projectName,String userName,Integer time, LocalDate date){
        this.projectName = projectName;
        this.userName = userName;
        this.time = time;
        this.date = date;

    }
    public AssignmentForFuncDto() {
    }

    public static class Builder{
        private String projectName;
        private String userName;
        private Integer time;
        private LocalDate date;

        public Builder(String projectName, String userName,Integer time, LocalDate date){
            this.projectName = projectName;
            this.userName = userName;
            this.time = time;
            this.date = date;

        }

        public Builder(){}

        public AssignmentForFuncDto.Builder userName(String userName){
            this.userName = userName;
            return this;
        }
        public AssignmentForFuncDto.Builder projectName(String projectName){
            this.projectName = projectName;
            return this;
        }

        public AssignmentForFuncDto.Builder time(Integer time){
            this.time = time;
            return this;
        }

        public AssignmentForFuncDto.Builder date(LocalDate date){
            this.date= date;
            return this;
        }


        public AssignmentForFuncDto build(){
            return new AssignmentForFuncDto(projectName, userName,time,date);
        }


    }

}
