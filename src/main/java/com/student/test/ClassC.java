package com.student.test;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClassC {
    @Bean
    public void hello(){
        System.out.println("Hello C");
    }
}
