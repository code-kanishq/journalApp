package com.springboot.journalApp.service;

import com.springboot.journalApp.entity.User;
import com.springboot.journalApp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService
{
    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User user)
    {
        userRepo.save(user);
    }

    public boolean saveNewUser(User user)
    {
        try
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepo.save(user);
            return true;
        }
        catch (Exception e)
        {
            log.info("hahahahaha");
            log.trace("hahahahaha");
            log.warn("hahahahaha");
            log.error("Error occurred for {}", user.getUserName());
            log.debug("hahahahaha");
            return false;
        }
    }

    public void saveAdmin(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER", "ADMIN"));
        userRepo.save(user);
    }

    public List<User> getAllUsers()
    {
        return userRepo.findAll();
    }

    public Optional<User> findByID(ObjectId id)
    {
        return userRepo.findById(id);
    }

    public void deleteById(ObjectId id)
    {
        userRepo.deleteById(id);
    }

    public User findByUserName(String userName)
    {
        return userRepo.findByUserName(userName);
    }
}
