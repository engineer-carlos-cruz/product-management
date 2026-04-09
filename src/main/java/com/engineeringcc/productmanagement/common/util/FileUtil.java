package com.engineeringcc.productmanagement.common.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileUtil {

    public String saveImage(MultipartFile file) {
        String uniqueFileName;

        try (InputStream inputStream = file.getInputStream()) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            uniqueFileName = UUID.randomUUID().toString().concat("-").concat(filename);
            Path path = Path.of("uploads/products");

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.copy(inputStream, path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return uniqueFileName;
    }
}
