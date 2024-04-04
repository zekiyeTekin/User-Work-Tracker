package com.miniProject.miniProject.specification;


import com.miniProject.miniProject.entity.TimeRecord;
import com.miniProject.miniProject.filter.TimeRecordFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeRecordSpecification {


    public static Specification<TimeRecord> searchRecordByDate(TimeRecordFilter timeRecordFilter){
        return (Root< TimeRecord > root, CriteriaQuery < ?> query, CriteriaBuilder builder) -> {

            /*
            Date date = timeRecordFilter.getDate();
            Timestamp timestamp = new Timestamp(date.getTime());
             */

            List<Predicate> predicateList = new ArrayList<>();

            if (timeRecordFilter.getDate() != null )
            {
                predicateList.add(builder.equal(root.get("date"),timeRecordFilter.getDate()));
            }
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }


    public static Specification<TimeRecord> searchRecordByAssignment(TimeRecordFilter timeRecordFilter){

        return (Root< TimeRecord > root, CriteriaQuery < ?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (timeRecordFilter.getAssignment() != null && !timeRecordFilter.getAssignment().isEmpty()){
                predicateList.add(builder.like(builder.lower(root.get("assignment").get("project").get("name")),"%" +timeRecordFilter.getAssignment().toLowerCase() + "%"));

            }
            return builder.and(predicateList.toArray(new Predicate[0]));
        };

    };

    public static Specification<TimeRecord> searchRecordWithAll(TimeRecordFilter timeRecordFilter){

        return (Root <TimeRecord> root, CriteriaQuery <?> query, CriteriaBuilder builder) -> {

                List<Predicate> predicateList = new ArrayList<>();
            if( timeRecordFilter.getDate() != null){
                predicateList.add(builder.equal(root.get("date"),timeRecordFilter.getDate()));
            }
            if( timeRecordFilter.getProjectName() != null && !timeRecordFilter.getProjectName().isEmpty()){
                predicateList.add(builder.like(builder.lower(root.get("assignment").get("project").get("name")),"%" +timeRecordFilter.getProjectName().toLowerCase() + "%"));
            }
            if(timeRecordFilter.getUserName() != null && !timeRecordFilter.getUserName().isEmpty()){
                predicateList.add(builder.like(builder.lower(root.get("assignment").get("user").get("name")),"%" +timeRecordFilter.getUserName().toLowerCase() + "%"));
            }
            if( timeRecordFilter.getSupervisorName() != null && !timeRecordFilter.getSupervisorName().isEmpty()){
                predicateList.add(builder.like(builder.lower(root.get("assignment").get("project").get("supervisor").get("name")),"%" +timeRecordFilter.getSupervisorName().toLowerCase() + "%"));
            }
            if( timeRecordFilter.getTime() !=null){
                predicateList.add(builder.equal(root.get("time"),timeRecordFilter.getTime()));
            }
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }




}
