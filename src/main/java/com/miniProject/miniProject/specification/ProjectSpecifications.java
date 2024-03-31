package com.miniProject.miniProject.specification;

import com.miniProject.miniProject.entity.Project;
import com.miniProject.miniProject.filter.ProjectFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class ProjectSpecifications {

    public static Specification<Project> filterBySupervisorName(ProjectFilter projectFilter) {
        return (Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if (projectFilter.getSupervisor() != null && !projectFilter.getSupervisor().isEmpty())
                {
                    predicateList.add(builder.like(builder.lower(root.get("supervisor").get("name")), "%" + projectFilter.getSupervisor().toLowerCase() + "%"));
                }
                return builder.and(predicateList.toArray(new Predicate[0]));
        };

    }


    public static Specification<Project> filterByName(ProjectFilter projectFilter){
        return (Root < Project > root, CriteriaQuery < ?> query, CriteriaBuilder builder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if (projectFilter.getName() != null && !projectFilter.getName().isEmpty()){
                predicateList.add(builder.like(builder.lower(root.get("name")), "%" +projectFilter.getName().toLowerCase() + "%"));
            }
          return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
