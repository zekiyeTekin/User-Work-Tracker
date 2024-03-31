package com.miniProject.miniProject.service;

import com.miniProject.miniProject.dto.AssignmentDto;
import com.miniProject.miniProject.dto.AssignmentForFuncDto;
import com.miniProject.miniProject.entity.Assignment;
import com.miniProject.miniProject.filter.AssignmentFilter;
import com.miniProject.miniProject.implementation.AssignmentService;
import com.miniProject.miniProject.mapper.AssignmentMapper;
import com.miniProject.miniProject.repository.AssignmentRepository;
import com.miniProject.miniProject.specification.AssignmentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssignmentServiceImpl implements AssignmentService {


   private final AssignmentRepository assignmentRepository;

    //@Autowired
    private final AssignmentMapper assignmentMapper;
 /*
    public ResponseEntity<List<AssignmentDto>> getAllAssignment(){
        try{
            List<Assignment> assignmentList = assignmentRepository.findAll();
            if (assignmentList.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(assignmentMapper.convertListWithoutUser(assignmentList), HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
  */
    @Override
    public ResponseEntity<Page<AssignmentDto>> getAllAssignmentWithPagination(Pageable pageable){

            Page<AssignmentDto> assignmentPage = assignmentMapper.mapPage(assignmentRepository.findAll(pageable));
            if (assignmentPage.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(assignmentPage, HttpStatus.OK);

    }



    public ResponseEntity<AssignmentDto> getAssignmentById(Integer id){
        Assignment assignments = assignmentRepository.findById(id).orElse(null);

        if(assignments == null){
            return ResponseEntity.notFound().build();
        }else
            return new ResponseEntity<>(assignmentMapper.toDtoWithoutUser(assignments), HttpStatus.OK);

    }

    //istenen bir supervisorun yer aldğı projedeki proje adı ve kullanıcısı kim
    //bu hata veriyor supervisorId değil de projectDto girince
    public ResponseEntity<List<AssignmentForFuncDto>> getSupervisorAndProjectByUserId(Integer supervisorId) {

        //Integer supervisorId = projectDto.getSupervisor().getId();
        List<Assignment> assignments = assignmentRepository.findAssignmentsByProject_SupervisorId(supervisorId);
        List<AssignmentForFuncDto> result = new ArrayList<>();

        for (Assignment assignment : assignments) {
            AssignmentForFuncDto assignmentForFuncDto = new AssignmentForFuncDto();
            assignmentForFuncDto.setProjectName(assignment.getProject().getName());
            assignmentForFuncDto.setUserName(assignment.getUser().getName() + " " + assignment.getUser().getSurname());
            result.add(assignmentForFuncDto);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    //body e project: "Nur" gibi gireceksin onun dışında gelmez
    public ResponseEntity<List<AssignmentDto>> getUserAndProjectBySupervisor(AssignmentFilter assignmentFilter){
        return new ResponseEntity<>(assignmentMapper.convertListWithoutUser(assignmentRepository.findAll(AssignmentSpecifications.filterBySupervisorName(assignmentFilter))), HttpStatus.OK);
    }

   /*
    public ResponseEntity<List<AssignmentDto>> getByUserSurname(AssignmentFilter assignmentFilter){
        return new ResponseEntity<>(assignmentMapper.convertListWithoutUser(assignmentRepository.findAll(AssignmentSpecifications.filterByUserSurname(assignmentFilter))), HttpStatus.OK);
    }
    */
}

