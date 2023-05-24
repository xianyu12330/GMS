package com.example.gms;

import com.example.gms.dao.UserMapper;
import com.example.gms.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GmsApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
//        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

}
