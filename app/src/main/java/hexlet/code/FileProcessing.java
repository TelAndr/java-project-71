package hexlet.code;

import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(name = "fileProcessing", mixinStandardHelpOptions = true, version = "fileProcessing 1.0",
        description = "Описание приложения и его функциональности.")
class FileProcessing implements Runnable {
    @CommandLine.Parameters(index = "0", description = "The path to the first file.")
    private File firstFile;

    @CommandLine.Parameters(index = "1", description = "The path to the second file.")
    private File secondFile;

    @Override
    public void run() {
        if (firstFile.isFile() && secondFile.isFile()) {
            System.out.println("Both files exist.");
            // Additional processing logic here
        } else {
            System.out.println("One or both files do not exist.");
        }
    }
}
