package fhv.team11.project.ems.commons.cache;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class CacheManager {

    public <T> void save(CacheKey<T> key, T value) {

    }

    public <T> void save(CacheKey<T> key, T value, Duration timeToLive) {

    }

    public <T> Optional<T> get(CacheKey<T> key) {
        return Optional.empty();
    }

    public boolean remove(CacheKey<?> key) {
        return false;
    }

    public void clear(Long userId) {

    }
}
