package br.com.low.cost.delivery.api.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@EnableCaching
@Configuration
public class CacheConfiguration implements CachingConfigurer {

	@Value("${spring.cache.ttl:10}")
	private Integer cacheTtl;

	@Override
	public CacheManager cacheManager() {
		final ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {
			@Override
			protected Cache createConcurrentMapCache(final String name) {
				return new ConcurrentMapCache(name,
						CacheBuilder.newBuilder().expireAfterWrite(cacheTtl, TimeUnit.SECONDS).build().asMap(), false);
			}
		};

		return cacheManager;
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}

}
