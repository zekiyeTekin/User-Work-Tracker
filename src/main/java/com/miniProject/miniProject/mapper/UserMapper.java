package com.miniProject.miniProject.mapper;

import com.miniProject.miniProject.dto.UserDto;
import com.miniProject.miniProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserMapper {



    @Autowired
    AssignmentMapper assignmentMapper;

    public UserDto toDto(User user) {

        return new UserDto.Builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .assignmentDtoList(assignmentMapper.convertListWithoutUser(user.getAssignmentList()))
                .build();
    }

    public UserDto toDtoWithoutAssignmentList(User user){

        return new UserDto.Builder()
                .id(user.getId())
                .surname(user.getSurname())
                .name(user.getName())
                .build();
    }

    public List<UserDto> convertList(List<User> userList){
        List<UserDto> userDtoList = new ArrayList<>();
        for (User users : userList){
            userDtoList.add(toDto(users));
        }
        return userDtoList;
    }

    public Page<UserDto> mapPage(Page<User> userPage){
        return new PageImpl<>(convertList(userPage.getContent()),userPage.getPageable(), userPage.getTotalElements());
    }
}
