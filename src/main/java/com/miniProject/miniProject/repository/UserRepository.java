package com.miniProject.miniProject.repository;

import com.miniProject.miniProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> , JpaSpecificationExecutor<User>{





}
