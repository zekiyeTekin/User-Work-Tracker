package com.miniProject.miniProject.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) //assignmentDtoListi yazdıktan sonra tekrar user diyp başlamasın diye koydum zaten null du o değer.
public class AssignmentDto {

    private Integer id;
    private ProjectDto project;
    private UserDto user;

    public AssignmentDto(Integer id,ProjectDto project, UserDto user){
        this.id = id;
        this.project = project;
        this.user = user;
    }
    public AssignmentDto() {
    }

    public static class Builder{
        private Integer id;
        private ProjectDto project;
        private UserDto user;

        public Builder(Integer id, ProjectDto project, UserDto user){
            this.id = id;
            this.project = project;
            this.user = user;
        }

        public Builder(){}

        public Builder id(Integer id){
            this.id = id;
            return this;
        }
        public Builder project(ProjectDto project){
            this.project = project;
            return this;
        }

        public Builder user(UserDto user){
            this.user = user;
            return this;
        }

        public AssignmentDto build(){
            return new AssignmentDto(id,project,user);
        }


    }
    
}
