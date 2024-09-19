package com.springset2.redis.com.springset2.redis.controllers;

import com.springset2.redis.com.springset2.redis.doa.UserDao;

import com.springset2.redis.com.springset2.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;


    // create user
    @PostMapping
    public User createUser(@RequestBody User user)
     {

        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);


    }

    //get single user

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userDao.get(userId);
    }

    //find all
    @GetMapping
    public List<User> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userDao.findAllPaginated(page, size);
    }

    //delete  user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {

        userDao.delete(userId);
    }

}