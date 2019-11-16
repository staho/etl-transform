package com.stahocorp.etlprocess;

import com.stahocorp.etlprocess.enums.FileType;
import com.stahocorp.etlprocess.files.FilenameParser;
import com.stahocorp.etlprocess.items.FileItem;
import com.stahocorp.etlprocess.items.InfoItem;
import com.stahocorp.etlprocess.transform.InfoFileParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
class EtlProcessApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testInfoParsing_rtx2060() {
        String fileName = "src/test/testFiles/morele-6130061-20191114-info.html";
        try {

            String data = new String(Files.readAllBytes(Paths.get(fileName)));

            InfoFileParser ifp = new InfoFileParser(data);

            InfoItem ii = ifp.parseHtmlToItem();

            assertEquals(6130061, ii.getId(), "Must be 6130061");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInfoParsing_rx570() {
        String fileName = "src/test/testFiles/morele-1766330-20191114-info.html";
        try {

            String data = new String(Files.readAllBytes(Paths.get(fileName)));

            InfoFileParser ifp = new InfoFileParser(data);

            InfoItem ii = ifp.parseHtmlToItem();

            assertEquals(1766330, ii.getId(), "Must be 1766330");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testFileNameParser_info() throws ParseException {
        String fileName = "morele-1766330-20191114-info.html";

        FileItem fileItem = FilenameParser.parseFilename(fileName);

        assertEquals("morele", fileItem.getSupplierName());
        assertEquals(1766330, fileItem.getItemId());

        SimpleDateFormat fromFile = new SimpleDateFormat("yyyyMMdd");

        assertEquals(fromFile.parse("20191114"), fileItem.getDateFetched());
        assertEquals(FileType.INFO, fileItem.getFileType());
    }

    @Test
    void testFileNameParser_opinions() throws ParseException {
        String fileName = "morele-1766330-20191114-opinions.html";

        FileItem fileItem = FilenameParser.parseFilename(fileName);

        SimpleDateFormat fromFile = new SimpleDateFormat("yyyyMMdd");

        assertEquals(fromFile.parse("20191114"), fileItem.getDateFetched());
        assertEquals("morele", fileItem.getSupplierName());
        assertEquals(1766330, fileItem.getItemId());
        assertEquals(FileType.OPINIONS, fileItem.getFileType());
    }

}
