package pe.abelmiraval.tenpo.infraestructure.config.cache;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import io.github.bucket4j.grid.hazelcast.HazelcastProxyManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.abelmiraval.tenpo.infraestructure.config.ratelimit.RateLimiterKey;

@Configuration
public class CacheConfig {
    @Bean
    public HazelcastInstance hazelcastInstance() {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        instance.getConfig().addMapConfig(new MapConfig("rate-limiters"));
        instance.getConfig().getNetworkConfig().getRestApiConfig().setEnabled(true);
        return instance;
    }
    @Bean
    public IMap<RateLimiterKey, byte[]> rateLimitingMap(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getMap("rate-limiters");
    }
    @Bean
    public HazelcastProxyManager<RateLimiterKey> hazelcastProxyManager() {
        return new HazelcastProxyManager<RateLimiterKey>(hazelcastInstance().getMap("rate-limiters"));
    }
    @Bean
    public ClientConfig clientConfig() {
        ClientConfig cfg = ClientConfig.load();
        cfg.setClusterName("statsCluster");
        return cfg;
    }
    @Bean
    CacheManager hazelcastCacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }

}