//package com.avoworld.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import jakarta.annotation.PostConstruct;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Service
//public class FileStorageService {
//
//    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);
//
//    @Value("${file.upload-dir}")
//    private String uploadsDir;
//
//    private Path rootLocation;
//
//    @PostConstruct
//    public void initializeRootLocation() {
//        try {
//            this.rootLocation = Paths.get(uploadsDir).toAbsolutePath().normalize();
//            Files.createDirectories(rootLocation);
//        } catch (IOException e) {
//            logger.error("Could not initialize storage: {}", e.getMessage());
//            throw new RuntimeException("Could not initialize storage: " + e.getMessage(), e);
//        }
//    }
//
//    public String store(MultipartFile file) {
//        String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
//        try {
//            if (file.isEmpty()) {
//                throw new RuntimeException("Failed to store empty file.");
//            }
//
//            if (filename.contains("..")) {
//                // This is a security check
//                throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
//            }
//
//            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
//            return filename;
//        } catch (IOException e) {
//            logger.error("Failed to store file: {}", e.getMessage());
//            throw new RuntimeException("Failed to store file: " + e.getMessage(), e);
//        }
//    }
//
//    public Path load(String filename) {
//        return rootLocation.resolve(filename);
//    }
//
//    public String getFileUrl(String filename) {
//        return ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/uploads/")
//                .path(filename)
//                .toUriString();
//    }
//
//    public String saveProfileImage(MultipartFile profileImg) throws IOException {
//        String filename = UUID.randomUUID().toString() + "-" + profileImg.getOriginalFilename();
//        Path uploadsPath = rootLocation;
//
//        // Ensure the directory exists
//        if (!Files.exists(uploadsPath)) {
//            Files.createDirectories(uploadsPath);
//        }
//
//        String filePath = uploadsPath.resolve(filename).toString();
//        File dest = new File(filePath);
//        profileImg.transferTo(dest);
//
//        // Return the URL or path of the saved file
//        return getFileUrl(filename);
//    }
//}
