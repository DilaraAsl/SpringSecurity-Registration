package com.kos.service.impl;

import com.kos.dto.UserDto;
import com.kos.entity.User;
import com.kos.repository.UserRepository;
import com.kos.service.UserService;
import com.kos.exception.UserAlreadyExistsException;
import com.kos.util.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: "
                    + userDto.getEmail());
        }
        return userRepository.save(mapperUtil.convert(userDto, new User()));
        // the rest of the registration operation
    }

    private boolean emailExists(String email) {
        return userRepository.findUserByEmail(email) != null;
    }
}
