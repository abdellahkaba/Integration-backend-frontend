package com.isi.demo.departement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartementMapperTest {

    @BeforeEach
    void setUp(){
        System.out.println("Inside the before each method");
    }
    @AfterEach
    void tearDown(){
        System.out.println("Inside the after each method");
    }
    @Test
    public void test1(){
        System.out.println("My first test method");
    }
    @Test
    public void test2(){
        System.out.println("My second test method");
    }

}