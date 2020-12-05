package com.ypj.fileserver.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileService {

    public void fileDownload(String name, HttpServletResponse response) throws Exception {
        File file = new File("../logs" + File.separator + name);

        if (!file.exists()) {
            throw new FileNotFoundException(name + "文件不存在");
        }

        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);
        byte[] buffer = new byte[1024];

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        OutputStream os = response.getOutputStream();
        int len = 0;
        while ((len = bis.read(buffer))!= -1) {
            os.write(buffer, 0, len);
        }
        fis.close();
        if (file.exists() && file.isFile() && file.delete()){
            file.delete();
        }
    }

    public String fileUpload(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return "上传失败：" + file.getOriginalFilename();
        }

        String filePath = "../logs";
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File fileUpload = new File(filePath, file.getOriginalFilename());
        try {
            file.transferTo(fileUpload);
            return  "成功上传：" + file.getOriginalFilename();
        } catch (IOException e) {
            throw new FileNotFoundException(file.getOriginalFilename() + "上传日志文件到服务器失败：" + e.toString());
        }
    }
}