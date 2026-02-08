package com.foodie.eatzy.service.imple;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodie.eatzy.entity.User;
import com.foodie.eatzy.exception.ResourceNotFoundException;
import com.foodie.eatzy.repository.UserRepository;
import com.foodie.eatzy.security.CustomUserDetail;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepo;

    public CustomUserDetailService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        CustomUserDetail user1 = new CustomUserDetail(user);
        return user1;

    }

}
