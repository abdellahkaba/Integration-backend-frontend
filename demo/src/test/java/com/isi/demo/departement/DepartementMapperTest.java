package com.isi.demo.departement;

import org.junit.jupiter.api.*;

class DepartementMapperTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inside Before All method");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Inside After All method");
    }

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