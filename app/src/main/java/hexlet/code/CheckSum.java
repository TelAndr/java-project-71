package hexlet.code;

import picocli.CommandLine;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@CommandLine.Command(name = "checksum", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Prints the checksum (SHA-256 by default) of a file to STDOUT.")
class CheckSum implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "The file whose checksum to calculate.")
    private File file;
    private int newNumVar = 1;
    @CommandLine.Option(names = {"-a", "--algorithm"}, description = "MD5, SHA-1, SHA-256, ...")
    private String algorithm = "SHA-256"; //

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        byte[] fileContents = Files.readAllBytes(file.toPath());
        byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
        System.out.printf("%0" + (digest.length * 2) + "x%n", new BigInteger(1, digest));
        return 0;
    }
}
