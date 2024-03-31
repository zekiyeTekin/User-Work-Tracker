package com.miniProject.miniProject.implementation;

import com.miniProject.miniProject.dto.ProjectDto;
import com.miniProject.miniProject.dto.UserDto;
import com.miniProject.miniProject.entity.Project;
import com.miniProject.miniProject.filter.ProjectFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProjectService {
     ResponseEntity<Page<ProjectDto>> getProjectListWithPagination(Pageable pageable);
     ResponseEntity<ProjectDto> getProjectById(Integer id);

     ResponseEntity<List<ProjectDto>> getProjectBySupervisorId(Integer supervisorId);

     ResponseEntity<List<ProjectDto>> getProjectBySupervisorName(ProjectFilter projectFilter);

     ResponseEntity<List<ProjectDto>> getProjectByName(ProjectFilter projectFilter);



   //ResponseEntity<List<ProjectDto>> getAllProjects();
}
