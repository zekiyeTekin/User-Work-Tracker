package com.miniProject.miniProject.specification;

import com.miniProject.miniProject.entity.Assignment;
import com.miniProject.miniProject.filter.AssignmentFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AssignmentSpecifications {

    //body e project: "Nur" gibi gireceksin onun dışında gelmez
    public static Specification<Assignment> filterBySupervisorName(AssignmentFilter assignmentFilter){
        return (Root < Assignment > root, CriteriaQuery < ?> query, CriteriaBuilder builder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if(assignmentFilter.getProject() != null && !assignmentFilter.getProject().isEmpty())
            {
                predicateList.add(builder.like(builder.lower(root.get("project").get("supervisor").get("name")), "%" +assignmentFilter.getProject().toLowerCase() + "%" ));
            }
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }

   /*
    public static Specification<Assignment> filterByUserSurname(AssignmentFilter assignmentFilter){
        return (Root < Assignment > root, CriteriaQuery < ? > query, CriteriaBuilder builder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if(assignmentFilter.getUser() != null && !assignmentFilter.getUser().isEmpty()){
                predicateList.add(builder.like(builder.lower(root.get("user").get("name")), "%" + assignmentFilter.getUser().toLowerCase() + "%"));
            }
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }
    */
}
