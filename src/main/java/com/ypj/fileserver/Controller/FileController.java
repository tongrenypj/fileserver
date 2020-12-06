package com.ypj.fileserver.Controller;
import com.ypj.fileserver.Service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 下载日志接口
     *
     * @param name
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/download/{name}")
    public String fileDownload(@PathVariable String name, HttpServletResponse response) throws Exception {
        return fileService.fileDownload(name, response);
    }


    @PostMapping(value = "/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        return fileService.fileUpload(file);
    }
}