package com.miniProject.miniProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AssignmentForFuncDto {
    private String projectName;
    private String userName;

    public AssignmentForFuncDto(String projectName,String userName){
        this.projectName = projectName;
        this.userName = userName;

    }
    public AssignmentForFuncDto() {
    }

    public static class Builder{
        private String projectName;
        private String userName;

        public Builder(String projectName, String userName){
            this.projectName = projectName;
            this.userName = userName;

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



        public AssignmentForFuncDto build(){
            return new AssignmentForFuncDto(projectName, userName);
        }


    }

}
