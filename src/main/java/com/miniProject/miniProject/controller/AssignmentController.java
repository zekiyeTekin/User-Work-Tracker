package com.miniProject.miniProject.controller;

import com.miniProject.miniProject.dto.AssignmentDto;
import com.miniProject.miniProject.dto.AssignmentForFuncDto;
import com.miniProject.miniProject.entity.Assignment;
import com.miniProject.miniProject.filter.AssignmentFilter;
import com.miniProject.miniProject.implementation.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assignment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssignmentController {

    //@Autowired
    private final AssignmentService assignmentService;

  /*
    @GetMapping("getAllAssignment")
    public ResponseEntity<List<AssignmentDto>> getAllAssignment(){
        return assignmentService.getAllAssignment();
    }
   */


    @GetMapping("/getAssignments")
    public ResponseEntity<Page<AssignmentDto>> getAllAssignmentWithPagination(Pageable pageable){
        return assignmentService.getAllAssignmentWithPagination(pageable);
    }



    @GetMapping("/get")
    public ResponseEntity<AssignmentDto> getAssignmentById(@RequestParam Integer id){
        return assignmentService.getAssignmentById(id);
    }


    //istenen bir supervisorun (id) yer aldığı projedeki proje adı ve kullanıcısı kim
    @GetMapping("/get/sp")
    public ResponseEntity<List<AssignmentForFuncDto>> getSupervisorAndProjectByUserId(@RequestParam Integer supervisorId){
        return assignmentService.getSupervisorAndProjectByUserId(supervisorId);
    }

    //body e project: "Nur" gibi gireceksin onun dışında gelmez
    @PostMapping("/getWithName")
    public ResponseEntity<List<AssignmentDto>> getUserAndProjectBySupervisor(@RequestBody AssignmentFilter assignmentFilter){
        return assignmentService.getUserAndProjectBySupervisor(assignmentFilter);
    }

   /*
    @PostMapping("/getWithSurname")
    public ResponseEntity<List<AssignmentDto>> getByUserSurname(@RequestBody AssignmentFilter assignmentFilter){
        return assignmentService.getByUserSurname(assignmentFilter);
    }
    */
}
