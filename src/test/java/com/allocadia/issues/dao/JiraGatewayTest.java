package com.allocadia.issues.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JiraGatewayTest {

    @Autowired
    private JiraGateway jiraGateway;
    
    @Test
    public void testConnect() {
        jiraGateway.test();
    }
}
