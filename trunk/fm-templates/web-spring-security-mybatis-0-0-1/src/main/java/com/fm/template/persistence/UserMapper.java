package com.fm.template.persistence;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fm.template.domain.User;

public interface UserMapper {

	User getUserByUsername(String username) throws UsernameNotFoundException;

}