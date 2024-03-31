package com.miniProject.miniProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)// bunu yazınca supervisorun içinde tekrar assignmentList yazdırmadı o zaten null dönüyordu.
public class UserDto {

    @JsonIgnore
    private Integer id;
    private String name;
    private String surname;
    private List<AssignmentDto> assignmentDtoList;

    public UserDto(Integer id, String name, String surname, List<AssignmentDto> assignmentDtoList){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.assignmentDtoList = assignmentDtoList;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String surname;
        private List<AssignmentDto> assignmentDtoList;

        public Builder() {
        }

        public Builder(Integer id, String name, String surname,List<AssignmentDto> assignmentDtoList) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.assignmentDtoList = assignmentDtoList;
        }

        public Builder id(Integer id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }
        public UserDto.Builder assignmentDtoList(List<AssignmentDto> assignmentDtoList){
            this.assignmentDtoList = assignmentDtoList;
            return this;
        }
        public UserDto build(){
            return new  UserDto(id,name,surname,assignmentDtoList);
        }
    }

}
