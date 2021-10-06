package com.obss.onlinemarketplace.File;

import com.obss.onlinemarketplace.config.AppConfiguration;
import com.obss.onlinemarketplace.model.Seller;

import org.apache.tika.Tika;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
public class FileService {


    AppConfiguration appConfiguration;
    Tika tika;
    FileAttachmentRepository fileAttachmentRepository;

    public FileService(AppConfiguration appConfiguration, FileAttachmentRepository fileAttachmentRepository) {
        this.appConfiguration = appConfiguration;
        this.fileAttachmentRepository = fileAttachmentRepository;
        this.tika = new Tika();
    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleteProfileImage(String oldImageName) {
        if (oldImageName == null) {
            return;
        }
        deleteFile(Paths.get(appConfiguration.getProfileStoragePath(), oldImageName));
    }

    public String detectType(byte[] arr) {
        return tika.detect(arr);
    }

    public String detectType(String image) {
        byte[] base64encoded = Base64.getDecoder().decode(image);
        return tika.detect(base64encoded);
    }

    public FileAttachment saveProductAttachment(MultipartFile multipartFile) {
        String fileName = generateRandomName();
        String fileType = null;
        File target = new File(appConfiguration.getAttachmentStoragePath() + "/" + fileName);

        try {
            byte[] arr = multipartFile.getBytes();
            OutputStream outputStream = new FileOutputStream(target);
            outputStream.write(arr);
            outputStream.close();
            fileType = detectType(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileAttachment attachment = new FileAttachment();
        attachment.setName(fileName);
        attachment.setDate(new Date());
        attachment.setFileType(fileType);

        return fileAttachmentRepository.save(attachment);
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void cleanUpStorage() {
        Date twentyFourHoursAgo = new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000));
        List<FileAttachment> filesToBeDeleted = fileAttachmentRepository.findByDateBeforeAndProductIsNull(twentyFourHoursAgo);
        for (FileAttachment file : filesToBeDeleted) {

            deleteAttachmentFile(file.getName());
            fileAttachmentRepository.deleteById((file.getId()));
        }
    }

    public void deleteAttachmentFile(String oldFile) {
        if (oldFile == null) {
            return;
        }
        deleteFile(Paths.get(appConfiguration.getAttachmentStoragePath(), oldFile));

    }

    private void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteAllStoredFileForSeller(Seller inDB) {
        List<FileAttachment> filesToBeRemoved = fileAttachmentRepository.findByProductSeller(inDB);
        for (FileAttachment file : filesToBeRemoved) {
            deleteAttachmentFile(file.getName());
        }
    }
}
