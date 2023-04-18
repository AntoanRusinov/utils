package utility.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@Service
public class FileMergerService {

    public void mergeFiles(String sourcePath) throws IOException {
        String userHome = System.getProperty("user.home");
        Path desktopPath = Paths.get(userHome, "Desktop");
        Path mergedFilesDirectory = desktopPath.resolve("mergedFiles");
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
    }
}
