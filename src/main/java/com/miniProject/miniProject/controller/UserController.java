package com.miniProject.miniProject.controller;

import com.miniProject.miniProject.dto.UserDto;
import com.miniProject.miniProject.filter.UserFilter;
import com.miniProject.miniProject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("user")
@CrossOrigin(origins = "*", maxAge=3600)
public class UserController {

    @Autowired
    UserServiceImpl userService;



    @GetMapping("getUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/getUsersWithPaging")
    public ResponseEntity<Page<UserDto>> getAllUsersWithPagination(Pageable pageable){
        return userService.getAllUsersWithPagination(pageable);
    }


    @GetMapping("/get")
    public ResponseEntity<UserDto> getUserById(@RequestParam Integer id){
       return userService.getUserById(id);
    }


    @PostMapping("/get/users")
    public ResponseEntity<List<UserDto>> getUserBySearchName(@RequestBody UserFilter userFilter){
        return userService.getUserBySearchName(userFilter);
    }



}
