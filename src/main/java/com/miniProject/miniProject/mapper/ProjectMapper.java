package com.miniProject.miniProject.mapper;

import com.miniProject.miniProject.dto.ProjectDto;
import com.miniProject.miniProject.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectMapper {


    // burdan sonra sonlanacağı için artık static kullan artık döngü yok ama hata veriyor
   UserMapper userMapper = new UserMapper();



    public ProjectDto toDto(Project project){

        return new ProjectDto.Builder()
                .id(project.getId())
                .name(project.getName())
                .supervisor(userMapper.toDtoWithoutAssignmentList(project.getSupervisor()))
                .build();
    }



    public List<ProjectDto> convertList(List<Project> projectList){
        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project project : projectList){
            projectDtoList.add(toDto(project));
        }
        return projectDtoList;
    }

    public Page<ProjectDto> mapPage(Page<Project> projectPage){
        return new PageImpl<>(convertList(projectPage.getContent()),projectPage.getPageable(), projectPage.getTotalElements());

    }




}
