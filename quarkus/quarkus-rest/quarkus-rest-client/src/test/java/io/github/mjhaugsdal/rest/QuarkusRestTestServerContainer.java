package io.github.mjhaugsdal.rest;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class QuarkusRestTestServerContainer implements QuarkusTestResourceLifecycleManager {

    static GenericContainer<?> testContainer = new GenericContainer<>(DockerImageName.parse("quarkus-rest/quarkus-rest-test-server:test"))
            .withExposedPorts(8082)
            .withReuse(true);
    ;

    @Override
    public Map<String, String> start() {
        testContainer.start();
        return ImmutableMap.of("test.container.port", String.valueOf(testContainer.getFirstMappedPort()));
    }

    @Override
    public void stop() {
        testContainer.stop();
    }
}
