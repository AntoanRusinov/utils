package utility.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;

@Service
public class FileMergerService {

    public void mergeFiles(String sourcePath, String phoneName) throws IOException {
        String userHome = System.getProperty("user.home");
        Path desktopPath = Paths.get(userHome, "Desktop");
        Path mergedFilesDirectory = desktopPath.resolve("Backup phone " + phoneName + " - " + LocalDate.now());
        Files.createDirectories(mergedFilesDirectory);

        Files.walkFileTree(Paths.get(sourcePath), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!attrs.isDirectory()) {
                    Path targetPath = mergedFilesDirectory.resolve(file.getFileName());
                    Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        // Delete ".AAE" files
        Files.walkFileTree(mergedFilesDirectory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!attrs.isDirectory() && file.getFileName().toString().endsWith(".AAE")) {
                    Files.delete(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        System.out.println("Files merged successfully. They are on your desktop in a folder with name \"" + mergedFilesDirectory.getFileName() + "\"");
    }
}
