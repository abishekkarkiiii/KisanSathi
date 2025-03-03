package Kisan.Sathi.Model;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;

@Component
public class FileOperationsForProject {
     public String path;
 public String saveImageInServer(MultipartFile file, String name,String path) {
     String exactTime = null;
     try (InputStream inputStream = file.getInputStream()) {
         byte[] data = inputStream.readAllBytes();
         exactTime = ProfileModel.getCurrentDateTime();
         this.path = "E:\\KisanSathi\\FarmersProduct" + "\\" + name + exactTime + ".jpg";
         FileOutputStream writer = new FileOutputStream(this.path);
         writer.write(data);
     } catch (Exception e) {
         e.fillInStackTrace();
     }

     return name + exactTime;
 }


}
