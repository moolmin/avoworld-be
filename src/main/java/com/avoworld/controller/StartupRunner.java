//package com.avoworld.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StartupRunner implements CommandLineRunner {
//
//    @Value("${JWT_KEY}")
//    private String jwtKey;
//
//    @Value("${DB_URL}")
//    private String datasourceUrl;
//
//    @Value("${DB_USERNAME}")
//    private String datasourceUsername;
//
//    @Value("${DB_PASSWORD}")
//    private String datasourcePassword;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("JWT Key: " + jwtKey);
//        System.out.println("Datasource URL: " + datasourceUrl);
//        System.out.println("Datasource Username: " + datasourceUsername);
//        System.out.println("Datasource Password: " + datasourcePassword);
//    }
//}
