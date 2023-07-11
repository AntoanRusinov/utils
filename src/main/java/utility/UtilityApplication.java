package utility;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utility.service.FileMergerService;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class UtilityApplication implements CommandLineRunner {

    private final FileMergerService fileMergerService;

    public static void main(String[] args) {
        SpringApplication.run(UtilityApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {

        //Desktop dir with name "DCIM" expected
        var sourcePath = "C:\\Users\\35989\\Desktop\\DCIM";

        System.out.println("Please, enter backup folder name. It should be without spaces: ");
        var folderName = new Scanner(System.in).next();

        fileMergerService.mergeFiles(sourcePath, folderName);
        System.exit(0);
    }

}
