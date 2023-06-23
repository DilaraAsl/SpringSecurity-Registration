package com.kos.service;

import com.kos.dto.UserDto;
import com.kos.entity.User;

public interface UserService {
    User registerNewUserAccount(UserDto userDto);
}
