package com.stahocorp.etlprocess.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@PropertySource("classpath:application.properties")
public class EtlProperties {

    @Value("${files.input}")
    private String inputDir;

    @Value("${files.working}")
    private String workingDir;

    @Value("${files.done}")
    private String doneDir;

    public String getInputDir() {
        return inputDir;
    }

    public Path getInputDirAsPath(){
        return Paths.get(".", inputDir);
    }

    public Path getWorkingDirAsPath(){
        return Paths.get(".", workingDir);
    }

    public Path getDoneDirAsPath(){
        return Paths.get(".", doneDir);
    }
}
