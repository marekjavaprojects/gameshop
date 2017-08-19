package com.gameshop.service;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gameshop.dao.UserDAO;
import com.gameshop.entity.UserRole;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(final String username)
               throws UsernameNotFoundException {

		com.gameshop.entity.User user = userDAO.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);


	}

	
	private User buildUserForAuthentication(com.gameshop.entity.User user,
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(),
			user.getPassword(), user.isEnabled(),
                        true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDao) {
		this.userDAO = userDao;
	}

}