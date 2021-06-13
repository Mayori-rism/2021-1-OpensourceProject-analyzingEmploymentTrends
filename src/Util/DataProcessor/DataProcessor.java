package Util.DataProcessor;

import java.lang.*;
import java.io.*;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

public class DataProcessor {
    public DataProcessor(){

    }
    public void callProcessor(String argv1, String argv2) {
        System.out.println("Python Call");
        String[] command = new String[4];
        command[0] = "python";
        command[1] = "./Resource/Python/map.py";
        command[2] = argv1;
        command[3] = argv2;


        try {
            execPython(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execPython(String[] command) throws IOException, InterruptedException {
        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; i++) {
            System.out.println(command[i]);
            commandLine.addArgument(command[i]);
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(pumpStreamHandler);

        int result = executor.execute(commandLine);
        System.out.println("result: " + result);
        System.out.println("output: " + outputStream.toString());
    }
}
