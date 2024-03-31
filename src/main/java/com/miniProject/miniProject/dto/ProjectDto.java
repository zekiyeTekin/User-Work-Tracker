package com.miniProject.miniProject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDto {

    private Integer id;
    private String name;
    private UserDto supervisor;

    public ProjectDto(Integer id, String name, UserDto supervisor){
        this.id = id;
        this.name = name;
        this.supervisor = supervisor;
    }

    public static class Builder{
        private Integer id;
        private String name;
        private UserDto supervisor;


        public Builder(Integer id, String name, UserDto supervisor){
            this.id = id;
            this.name = name;
            this.supervisor = supervisor;
        }

        public Builder(){}

        public Builder id(Integer id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder supervisor(UserDto supervisor){
            this.supervisor = supervisor;
            return this;
        }


        public ProjectDto build(){
            return new ProjectDto(id,name,supervisor);
        }

    }





}
