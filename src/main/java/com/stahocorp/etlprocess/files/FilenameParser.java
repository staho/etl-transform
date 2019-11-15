package com.stahocorp.etlprocess.files;

import com.stahocorp.etlprocess.enums.FileType;
import com.stahocorp.etlprocess.items.FileItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FilenameParser {

    public static FileItem parseFilename(String fileName) throws RuntimeException{
        List<String> fileNameFragments = Arrays.asList(fileName.split("-"));
        if(fileNameFragments.size() != 4) {
            throw new RuntimeException("FileName is wrong.\nShould be page-itemId-sourceDate-fileType.html");
        }

        FileItem item = new FileItem();
        item.setFileName(fileName);

        item.setSupplierName(fileNameFragments.get(FileNameIndexes.SUPPLIER_NAME.getValue()));
        item.setItemId(Long.parseLong(fileNameFragments.get(FileNameIndexes.ITEM_ID.getValue())));
        item.setDateFetched(getDateFromString(fileNameFragments.get(FileNameIndexes.DATE_FETCHED.getValue())));
        item.setFileType(getFileTypeFromFragment(fileNameFragments));

        return item;
    }

    private static FileType getFileTypeFromFragment(List<String> fileNameFragments) {
        String fileTypeTmp = fileNameFragments
                .get(FileNameIndexes.FILE_TYPE.getValue())
                .split("\\.")[0];

        return FileType.getTypeForString(fileTypeTmp);
    }

    private static Date getDateFromString(String dateAsString){
        Date tempFileDate = new Date();
        try {
            SimpleDateFormat fromFile = new SimpleDateFormat("yyyyMMdd");
            tempFileDate = fromFile.parse(dateAsString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tempFileDate;
    }


    private enum FileNameIndexes {
        SUPPLIER_NAME(0),
        ITEM_ID(1),
        DATE_FETCHED(2),
        FILE_TYPE(3);

        private final int value;

        FileNameIndexes(int x) {
            value = x;
        }

        public int getValue() {
            return value;
        }
    }

}
