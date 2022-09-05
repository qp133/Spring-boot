package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        var ctx =SpringApplication.run(InjectionDependencyApplication.class, args);
        BinarySearchImpl binarySearch = ctx.getBean(BinarySearchImpl.class);
        int res = binarySearch.binarySearch(new int[] {2,1,4,6,5},9);
        System.out.println(res);
    }

}