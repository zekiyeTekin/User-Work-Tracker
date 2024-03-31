package com.miniProject.miniProject.repository;

import com.miniProject.miniProject.entity.Project;
import com.miniProject.miniProject.filter.ProjectFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> , PagingAndSortingRepository<Project,Integer>, JpaSpecificationExecutor<Project> {

    List<Project> findBySupervisor_Id(Integer supervisorId);

    //List<Project> findBySupervisor_Name(ProjectFilter projectFilter);

    //List<Project> findBySupervisor_Name (Specification<ProjectFilter> specification);




}
