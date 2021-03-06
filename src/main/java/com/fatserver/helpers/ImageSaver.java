package com.fatserver.helpers;

import com.fatserver.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Victor on 20.02.2018.
 * Class for storing images in file system
 */
public class ImageSaver {

    public static void saveImage(User user, MultipartFile image) {
        String path =System.getProperty("user.dir") + "/data/Users/"
                + user.getName()+user.getId()+"/";
        File filePath = new File(path);
        user.setPathToImage(path+image.getOriginalFilename());
        System.err.println(user.getPathToImage());
;        filePath.mkdirs();
        try {
            // Get the file and
            // Save it somewhere
            byte[] bytes = image.getBytes();
            Path pathTo = Paths.get(path+image.getOriginalFilename());
            Files.write(pathTo, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
