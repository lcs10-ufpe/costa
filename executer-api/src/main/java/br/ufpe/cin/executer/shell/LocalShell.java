package br.ufpe.cin.executer.shell;

import java.io.IOException;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Component;

@Component
public class LocalShell {

    public void executeCommand(final String down, final String up) {

        try {

            final String commands[] = new String[] { "src/main/resources/nginx.sh", down, up };

            final ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.inheritIO();
            final Process process = processBuilder.start();

            StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Program Exit Successfully");
            } else {
                System.out.println("An Error Occured");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
