package com.nykaa.ehcache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    //if this code enabled, it will override existing cacheManager bean
//    @Bean
//    public Caffeine caffeineConfig() {
//        return Caffeine.newBuilder().expireAfterWrite(1L, TimeUnit.MINUTES);
//    }
//
//    @Bean
//    public CacheManager cacheManager(Caffeine caffeine) {
//        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
//        caffeineCacheManager.getCache("myCache");
//        caffeineCacheManager.setCaffeine(caffeine);
//        return caffeineCacheManager;
//    }
}
