package com.stahocorp.etlprocess.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMover {
    public static void move(Path i, Path o) throws IOException {
        if (!Files.exists(i.getParent())) {
            throw new IOException("Input directory doesn't exist");
        }

        if (!Files.exists(o.getParent())) {
            Files.createDirectories(o.getParent());
        }

        Files.move(i, o);
    }
}
