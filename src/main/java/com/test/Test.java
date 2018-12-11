package com.test;

import java.util.Optional;


public class Test {
    public static void main(String[] args) {
        System.out.println( "hello world" );
        System.out.println( "我在分支编程" );
    }
    @org.junit.Test
    public void test(){
        Optional<String> opt = Optional.of("Baeldung");
//        assertTrue(opt.isPresent());
//
//        opt = Optional.ofNullable(null);
//        assertFalse(opt.isPresent());

        opt.ifPresent( name -> System.out.println(name.length()));
    }
}
