package com.miniProject.miniProject.controller;

import com.miniProject.miniProject.dto.ProjectDto;
import com.miniProject.miniProject.filter.ProjectFilter;
import com.miniProject.miniProject.implementation.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
// fetch işlemi yapıldığında oluşan hatayı engellemek için, güvenli bir yerden geldiğini belirtmek için
@CrossOrigin(origins = "*", maxAge=3600)
public class ProjectController {


    private final ProjectService projectService;


    @GetMapping("getAllProjects")
    public ResponseEntity<List<ProjectDto>> getAllProjects(){
      return projectService.getAllProjects();
    }


    //http://localhost:8081/project/allProjects?pageNumber=1&pageSize=4
    //SOR RESPONSE ENTİTY OLMADAN ÇALIŞMIYOR NEDENNN?
    @GetMapping("/all")
    public ResponseEntity<Page<ProjectDto>> getProjectListWithPagination(Pageable pageable) {
        return projectService.getProjectListWithPagination(pageable);
    }


    @GetMapping("/get")
    public ResponseEntity<ProjectDto> getProjectById(@RequestParam Integer id) {
        return projectService.getProjectById(id);
    }


    //http://localhost:8081/project/id/getProject?supervisorName=Zekiye
    @GetMapping("/id/getProject")
    public ResponseEntity<List<ProjectDto>> getProjectBySupervisorId(@RequestParam Integer supervisorId){
        return projectService.getProjectBySupervisorId(supervisorId);
    }

    //http://localhost:8081/project/name/getProject?supervisorName=Zekiye
    @PostMapping("/name/getProject")
    public ResponseEntity<List<ProjectDto>> getProjectBySupervisorName(@RequestBody ProjectFilter projectFilter ){
        return projectService.getProjectBySupervisorName(projectFilter);
    }

    @PostMapping("name/getProjectN")
    public ResponseEntity<List<ProjectDto>> getProjectByName(@RequestBody ProjectFilter projectFilter){
        return projectService.getProjectByName(projectFilter);
    }




}
