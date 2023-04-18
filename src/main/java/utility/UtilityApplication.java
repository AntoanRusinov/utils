package utility;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utility.service.FileMergerService;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class UtilityApplication implements CommandLineRunner {

    private final FileMergerService fileMergerService;

    public static void main(String[] args) {
        SpringApplication.run(UtilityApplication.class, args);
    }


    @Override
    public void run(String... args) throws IOException {

        fileMergerService.mergeFiles("C:\\Users\\35989\\Desktop\\DCIM");
        System.out.println("Files merged successfully.");

    }

}
