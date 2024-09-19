package com.springset2.redis.com.springset2.redis.doa;

import com.springset2.redis.com.springset2.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class
UserDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "USER312412";


    //save user

    public User save(User user) {
        redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
        return user;
    }

    public User get(String userId) {

        return (User) redisTemplate.opsForHash().get(KEY, userId);
    }

    //find all

    public Map<Object, Object> findAll() {

        return redisTemplate.opsForHash().entries(KEY);
    }

    // delete

    public void delete(String userId) {

        redisTemplate.opsForHash().delete(KEY, userId);
    }
    // Paginated find all users
    public List<User> findAllPaginated(int page, int size) {
        Map<Object, Object> all = findAll();
        return all.values().stream()
                .map(value -> (User) value)
                .skip(page * size)
                .limit(size)
                .collect(Collectors.toList());
    }
    
    


}
