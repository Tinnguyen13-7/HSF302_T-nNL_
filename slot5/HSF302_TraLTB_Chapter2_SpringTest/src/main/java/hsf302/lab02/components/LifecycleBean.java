package hsf302.lab02.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifecycleBean {
    private boolean initialized = false;

    @PostConstruct
    public void init() {
        initialized = true;
        System.out.println("[LIFECYCLE] LifecycleBean initialized");
    }

    @PreDestroy
    public void cleanup() {
        initialized = false;
        System.out.println("[LIFECYCLE] LifecycleBean destroyed");
    }

    public boolean isInitialized() {
        return initialized;
    }
}
