package com.nykaa.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    CacheManager cacheManager(){
//        new XmlConfiguration(getClass().getResource("/cache/ehcache.xml"));
//        return null;
//    }
}
