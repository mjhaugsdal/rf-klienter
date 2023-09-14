package io.github.mjhaugsdal.rest;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.awaitility.Awaitility;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.Map;

public class CustomResource implements QuarkusTestResourceLifecycleManager {


    static GenericContainer<?> testContainer = new GenericContainer<>(DockerImageName.parse("quarkus-rest/quarkus-rest-test-server:test"))
            .withExposedPorts(8082)
            .withReuse(true);;

    @Override
    public Map<String, String> start() {
        testContainer.start();
//        String url = testContainer.getHost() + ":" + testContainer.getFirstMappedPort();

        return ImmutableMap.of("test.container.port", String.valueOf(testContainer.getFirstMappedPort()));
//        return null;
    }

    @Override
    public void stop() {
        Awaitility.await().pollDelay(Duration.ofMinutes(1)).until(() -> true);
//        testContainer.stop();
    }
}
