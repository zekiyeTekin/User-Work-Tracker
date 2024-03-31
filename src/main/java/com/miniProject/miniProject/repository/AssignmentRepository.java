package com.miniProject.miniProject.repository;

import com.miniProject.miniProject.dto.ProjectDto;
import com.miniProject.miniProject.dto.UserDto;
import com.miniProject.miniProject.entity.Assignment;
import com.miniProject.miniProject.entity.Project;
import com.miniProject.miniProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer>, PagingAndSortingRepository<Assignment,Integer>, JpaSpecificationExecutor<Assignment> {



    List<Assignment> findAssignmentsByProject_SupervisorId(Integer supervisorId);



}

