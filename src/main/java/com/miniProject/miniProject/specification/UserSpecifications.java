package com.miniProject.miniProject.specification;

import com.miniProject.miniProject.entity.User;
import com.miniProject.miniProject.filter.UserFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecifications {

    public static Specification<User> searchUser(UserFilter userFilter){
        return (Root < User > root,CriteriaQuery < ?>query, CriteriaBuilder builder ) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if(userFilter.getName() != null && !userFilter.getName().isEmpty()){
                predicateList.add(builder.like(builder.lower(root.get("name")),"%" + userFilter.getName().toLowerCase()+"%"));
            }
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
