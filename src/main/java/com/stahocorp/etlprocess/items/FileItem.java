package com.stahocorp.etlprocess.items;

import com.stahocorp.etlprocess.enums.FileType;

import java.util.Date;

public class FileItem {
    private String fileName;
    private Date dateFetched;
    private FileType fileType;
    private Long itemId;
    private String supplierName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDateFetched() {
        return dateFetched;
    }

    public void setDateFetched(Date dateFetched) {
        this.dateFetched = dateFetched;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
