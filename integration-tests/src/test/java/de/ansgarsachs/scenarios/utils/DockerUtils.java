package de.ansgarsachs.scenarios.utils;

import cucumber.runtime.CucumberException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * com.cgm.life.scenarios.utils.DockerUtils
 * <p>
 * Provides basic functionality to interact with the local docker instance.
 * </p>
 *
 * @author Rouven Himmelstein &lt;rouven.himmelstein@cgm.com&gt;
 * @since 06.12.2019
 */
public class DockerUtils {
    private DockerUtils() {
    }

    /**
     * Kills the specified docker service
     *
     * @param serviceName the name of the service to be killed
     */
    public static void killService(String serviceName) {
        executeCommand("docker kill " + serviceName);
    }

    /**
     * Starts the specified docker service
     *
     * @param serviceName the name of the service to be started
     */
    public static void startService(String serviceName) {
        executeCommand("docker start " + serviceName);
    }

    /**
     * Executes a given command in the local shell.
     *
     * <p>
     * Blocks until the command is executed.
     * </p>
     *
     * @param command to be executed
     * @return a response string of the executed command
     */
    public static String executeCommand(String command) {
        StringBuilder result = new StringBuilder();

        try {
            ProcessBuilder pb = new ProcessBuilder(command.split(" ")).redirectErrorStream(true);
            Process process = pb.start();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        break;
                    }
                    result.append(line).append("\n");
                }
            }
            Thread.sleep(1100);
        } catch (IOException | InterruptedException e) {
            throw new CucumberException(e);
        }

        return result.toString();
    }
}
