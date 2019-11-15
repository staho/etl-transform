package com.stahocorp.etlprocess.services;

import com.stahocorp.etlprocess.config.EtlProperties;
import com.stahocorp.etlprocess.files.FileMover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class WatcherTask {

    @Autowired
    private EtlProperties etlProperties;

    @Scheduled(fixedRate = 5000)
    public void readFileList() {
        try (Stream<Path> walk = Files.walk(etlProperties.getInputDirAsPath())) {

            List<Path> result = walk.filter(Files::isRegularFile).collect(Collectors.toList());

            result.forEach(f -> {
                try {
                    FileMover.move(f, etlProperties.getWorkingDirAsPath().resolve(f.getFileName()));
                } catch (IOException e) {
                    System.out.println("Failed to move file " + f.toString());
                    e.printStackTrace();
                }
            });

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Scheduled(fixedRate = 5000)
//    public void transformFiles() {
//        try (Stream<Path> walk = Files.walk(etlProperties.getWorkingDirAsPath())) {
//
//            List<Path> result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
//
//            result.forEach(f -> {
//                try {
//
//                } catch (IOException e) {
//                    System.out.println("Failed to move file " + f.toString());
//                    e.printStackTrace();
//                }
//            });
//
//            result.forEach(System.out::println);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
