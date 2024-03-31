package com.miniProject.miniProject.service;

import com.miniProject.miniProject.dto.ProjectDto;
import com.miniProject.miniProject.entity.Project;
import com.miniProject.miniProject.filter.ProjectFilter;
import com.miniProject.miniProject.mapper.ProjectMapper;
import com.miniProject.miniProject.implementation.ProjectService;
import com.miniProject.miniProject.repository.ProjectRepository;
import com.miniProject.miniProject.specification.ProjectSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

   // @Autowired
   private final ProjectRepository projectRepository;

   // @Autowired
   private final ProjectMapper projectMapper;

    /*

    public ResponseEntity<List<ProjectDto>> getAllProjects(int pageNumber, int pageSize){

        try{
            return new ResponseEntity<>(projectMapper.convertList(projectRepository.findAll()), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
     */
    @Override
    public ResponseEntity<Page<ProjectDto>> getProjectListWithPagination(Pageable pageable) {

        Page<ProjectDto> projectPage = projectMapper.mapPage(projectRepository.findAll(pageable));
        if (projectPage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
            return new ResponseEntity<>(projectPage,HttpStatus.OK);

    }

    public ResponseEntity<ProjectDto> getProjectById(Integer id) {

        Project project1 = projectRepository.findById(id).orElse(null);

        if (project1 == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(projectMapper.toDto(project1), HttpStatus.OK);
    }

    public ResponseEntity<List<ProjectDto>> getProjectBySupervisorId(Integer supervisorId){

        List<ProjectDto> projectList = projectMapper.convertList(projectRepository.findBySupervisor_Id(supervisorId));
        if (projectList.isEmpty()){
            return new ResponseEntity<>(projectList,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }


    public ResponseEntity<List<ProjectDto>> getProjectBySupervisorName(ProjectFilter projectFilter) {
        return new ResponseEntity<>(projectMapper.convertList(projectRepository.findAll(ProjectSpecifications.filterBySupervisorName(projectFilter))), HttpStatus.OK);
    }

    public ResponseEntity<List<ProjectDto>> getProjectByName(ProjectFilter projectFilter){
        return new ResponseEntity<>((projectMapper.convertList(projectRepository.findAll(ProjectSpecifications.filterByName(projectFilter)))),HttpStatus.OK);
    }
}
