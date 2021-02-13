package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationProvider {

  private UserMapper userMapper;
  private HashService hashService;

  public AuthenticationService(UserMapper userMapper, HashService hashService) {
    this.userMapper = userMapper;
    this.hashService = hashService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    return null; //TODO
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return false;
  }
}
