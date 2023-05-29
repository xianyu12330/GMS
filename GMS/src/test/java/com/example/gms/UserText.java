package com.example.gms;

import com.example.gms.dao.LoginMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.applet.Main;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)//启动类类模板
public class UserText {
@Autowired
    LoginMapper loginMapper;
    @Test
    public void Test(){
//
    }
}
