package com.miniProject.miniProject.implementation;

import com.miniProject.miniProject.dto.AssignmentDto;
import com.miniProject.miniProject.dto.AssignmentForFuncDto;
import com.miniProject.miniProject.filter.AssignmentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssignmentService {

    ResponseEntity<Page<AssignmentDto>> getAllAssignmentWithPagination(Pageable pageable);

    ResponseEntity<AssignmentDto> getAssignmentById(Integer id);

    ResponseEntity<List<AssignmentForFuncDto>> getSupervisorAndProjectByUserId(Integer supervisorId);

    ResponseEntity<List<AssignmentDto>> getUserAndProjectBySupervisor(AssignmentFilter assignmentFilter);

    //ResponseEntity<List<AssignmentDto>> getByUserSurname(AssignmentFilter assignmentFilter);

}
