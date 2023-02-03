package com.example.pathfinderwebapp;

import com.example.pathfinderwebapp.repositories.RouteRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;

}
