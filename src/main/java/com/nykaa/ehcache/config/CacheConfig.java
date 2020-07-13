package com.nykaa.ehcache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.nykaa.ehcache.data.Customer;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.event.EventType;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.cache.Caching;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

//    if this code enabled, it will override existing cacheManager bean
    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(1L, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager caeffineCacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.getCache("myCache");
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean
    @Primary
    public JCacheCacheManager cacheManager() {

        javax.cache.CacheManager cacheManager = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider").getCacheManager();

        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(new CustomCacheEventLogger(),
                        EventType.CREATED,
                        EventType.UPDATED,
                        EventType.EVICTED,
                        EventType.EXPIRED,
                        EventType.REMOVED)
                .unordered().asynchronous();

        CacheConfiguration<String, Customer> customerCacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, Customer.class,
                        ResourcePoolsBuilder.heap(3))
                .withExpiry(Expirations.timeToLiveExpiration(org.ehcache.expiry.Duration.of(5L, TimeUnit.MINUTES)))
                .add(cacheEventListenerConfiguration)
                .build();
        cacheManager.createCache("customerCache", Eh107Configuration.fromEhcacheCacheConfiguration(customerCacheConfiguration));
        return new JCacheCacheManager(cacheManager);
    }
}
