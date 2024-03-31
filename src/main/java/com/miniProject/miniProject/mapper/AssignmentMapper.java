package com.miniProject.miniProject.mapper;

import com.miniProject.miniProject.dto.AssignmentDto;
import com.miniProject.miniProject.entity.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AssignmentMapper {


    @Autowired
    ProjectMapper projectMapper;

   /*
    UserMapper userMapper = new UserMapper();
    public AssignmentDto toDto(Assignment assignment){

        return new AssignmentDto.Builder()
                .id(assignment.getId())
                .user(userMapper.toDto(assignment.getUser()))
                .project(projectMapper.toDto(assignment.getProject()))
                .build();
    }
    public List<AssignmentDto> convertList(List<Assignment> assignmentList){
        List<AssignmentDto> assignmentDtoList = new ArrayList<>();
        for(Assignment assignment : assignmentList){
            assignmentDtoList.add(toDto(assignment));
        }
        return assignmentDtoList;
    }

    */

    public AssignmentDto toDtoWithoutUser(Assignment assignment){

        return new AssignmentDto.Builder()
                .id(assignment.getId())
                .project(projectMapper.toDto(assignment.getProject()))
                .build();
    }

    public List<AssignmentDto> convertListWithoutUser(List<Assignment> assignmentList){
        List<AssignmentDto> assignmentDtoList = new ArrayList<>();

        for(Assignment assignment : assignmentList){
            assignmentDtoList.add(toDtoWithoutUser(assignment));
        }
        return assignmentDtoList;
    }

    public Page<AssignmentDto> mapPage(Page<Assignment> assignmentPage){
        return new PageImpl<>(convertListWithoutUser(assignmentPage.getContent()), assignmentPage.getPageable(), assignmentPage.getTotalElements());
    }


    }
