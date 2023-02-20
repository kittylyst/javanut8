package javanut8.ch10;

import javanut8.ch05.Reaper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

public class FilesExample {

    public static void setPathToTmpJar() throws IOException {
        Path tmpdir = Files.createTempDirectory(Paths.get("/tmp"), "tmp-test");
        try (InputStream in = FilesExample.class.getResourceAsStream("/resources.txt")) {
            Path copied = tmpdir.resolve("copied-resource.txt");
            Files.copy(in, copied, StandardCopyOption.REPLACE_EXISTING);
            // ... work with the copy
        }
        // Clean up when done...
        Files.walkFileTree(tmpdir, new Reaper());
    }

    void run2() throws IOException {
        var tempJar = Path.of("sample.jar");
        try (var workingFS =
                     FileSystems.newFileSystem(tempJar)) {

            Path pathForFile = workingFS.getPath("/hello.txt");
            Files.write(pathForFile,
                    List.of("Hello World!"),
                    Charset.defaultCharset(),
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        }

    }
}
