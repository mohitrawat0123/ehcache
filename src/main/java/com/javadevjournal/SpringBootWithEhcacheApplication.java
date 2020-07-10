package com.javadevjournal;

import org.ehcache.xml.XmlConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWithEhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWithEhcacheApplication.class, args);
    }

//    @Bean
//    CacheManager cacheManager(){
//        new XmlConfiguration(getClass().getResource("/cache/ehcache.xml"));
//        return null;
//    }
}
