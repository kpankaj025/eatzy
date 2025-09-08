package com.foodie.eatzy.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foodie.eatzy.dto.FileData;

@Service
public class FileUploadService {

    public FileData uploadFile(MultipartFile file, String path) throws IOException {

        if (path.isEmpty()) {
            throw new RuntimeException("Invalid  path");
        }

        Path folderPath = Paths.get(path.substring(0, path.lastIndexOf("/") + 1));
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        Path filePath = Paths.get(path);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String fileName = path.substring(path.lastIndexOf("/") + 1);
        FileData fileData = new FileData(fileName, path);

        return fileData;
    }

}
