package com.miniProject.miniProject.service;

import com.miniProject.miniProject.dto.UserDto;
import com.miniProject.miniProject.entity.User;
import com.miniProject.miniProject.filter.UserFilter;
import com.miniProject.miniProject.implementation.UserService;
import com.miniProject.miniProject.mapper.UserMapper;
import com.miniProject.miniProject.repository.UserRepository;
import com.miniProject.miniProject.specification.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    /*
    //Bu pagination yapmadan önceki tüm userlerı getiren fonksiyonum
    public ResponseEntity<List<UserDto>> getAllUsers(){

        try {
            List<UserDto> userList = userMapper.convertList(userRepository.findAll());
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
     */

    public ResponseEntity<Page<UserDto>> getAllUsersWithPagination(Pageable pageable){
        Page<UserDto> userPage = userMapper.mapPage(userRepository.findAll(pageable));
        if (userPage.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(userPage,HttpStatus.OK);
    }



    public ResponseEntity<UserDto> getUserById(Integer id){

            User users= userRepository.findById(id).orElse(null);
            if (users == null){
                return ResponseEntity.notFound().build();
            }
        return new ResponseEntity<>(userMapper.toDto(users), HttpStatus.OK);
    }


    public ResponseEntity<List<UserDto>> getUserBySearchName(UserFilter userFilter) {

        return new ResponseEntity<>(userMapper.convertList(userRepository.findAll(UserSpecifications.searchUser(userFilter))), HttpStatus.OK);
    }
}
