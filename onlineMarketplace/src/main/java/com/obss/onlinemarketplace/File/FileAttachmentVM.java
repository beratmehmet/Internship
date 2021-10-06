package com.obss.onlinemarketplace.File;

import lombok.Data;

@Data
public class FileAttachmentVM {
    private String name;

    private String fileType;

    public FileAttachmentVM(FileAttachment fileAttachment) {
        this.name = fileAttachment.getName();
        this.fileType = fileAttachment.getFileType();
    }
}
