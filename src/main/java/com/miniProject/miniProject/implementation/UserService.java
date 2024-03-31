package com.miniProject.miniProject.implementation;

import com.miniProject.miniProject.dto.UserDto;
import com.miniProject.miniProject.filter.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
   ResponseEntity< Page<UserDto>> getAllUsersWithPagination(Pageable pageable);
   ResponseEntity<List<UserDto>> getUserBySearchName(UserFilter userFilter);

}
