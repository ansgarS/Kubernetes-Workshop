package de.ansgarsachs.scenarios.utils;

import cucumber.runtime.CucumberException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.apache.http.HttpStatus;
import org.apache.http.conn.HttpHostConnectException;
import org.yaml.snakeyaml.Yaml;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * com.cgm.life.scenarios.utils.RestUtils
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 13.11.19
 */
public class RestUtils {
    private static Map<String, Object> testConfig = readConfig();

    /**
     * Checks if a given app in the playground is running
     *
     * <p>
     *  Throws an assertion error if app is not running
     * </p>
     *
     * @param appName to be verified
     */
    public static void assertAppIsRunning(String appName) {
        try {
            Map<String, Object> appConfig = getAppConfig(appName);
            String healthCheckUrl = resolveUrlPath(appName, (String) appConfig.get("healthpath"));

            assertThat(Unirest.get(healthCheckUrl).asString().isSuccess())
                    .as("Cannot reach this endpoint: %s", healthCheckUrl)
                    .isTrue();
        } catch (MalformedURLException | URISyntaxException | UnirestException e) {
            throw new CucumberException(e.getMessage());
        }
    }

    /**
     * Resolves an URI fpr a given app and path
     *
     * @param appName to connect top
     * @param path    as appendix of the basepath
     * @return the composed URI
     * @throws java.net.URISyntaxException    if uri is not valid
     * @throws java.net.MalformedURLException if uri is not valid
     */
    public static String resolveUrlPath(String appName, String path) throws URISyntaxException, MalformedURLException {
        URI uri = getAppBaseUrl(appName).toURI();

        return uri
                .resolve(uri.getPath() + path)
                .toString();
    }

    /**
     * Reads a baseURL from config for a given app in playground
     *
     * @param appName used for lookup
     * @return the app baseUrl
     * @throws java.net.MalformedURLException if URL is not valid
     */
    private static URL getAppBaseUrl(String appName) throws MalformedURLException {
        Map<String, Object> appConfig = getAppConfig(appName);

        return new URL(
                "http",
                (String) appConfig.get("host"),
                (Integer) appConfig.get("port"),
                (String) appConfig.get("basepath")
        );
    }

    /**
     * Accesses the config a specific app in playground
     *
     * @param appName used for lookup
     * @return the app's config
     */
    @SuppressWarnings(value = "unchecked")
    public static Map<String, Object> getAppConfig(String appName) {
        return (Map<String, Object>) ((Map<String, Object>) testConfig.get("containers")).get(appName);
    }

    /**
     * Reads and parses the test config
     *
     * @return the parsed config
     */
    public static Map<String, Object> readConfig() {
        InputStream inputStream = RestUtils.class
                .getClassLoader()
                .getResourceAsStream("config.yml");

        return new Yaml().load(inputStream);
    }
}
