package com.Service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public class FileUploadService {

    public static boolean imageUpload(HttpServletRequest request, String uploadPath, String tempPath, String filename){

        //创建文件保存路径
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        //缓存
        File tempDir = new File(tempPath);
        if(!tempDir.exists()){
            tempDir.mkdir();
        }

        //1.创建文件工厂对象。设置文章临时数据大小和文件枯井
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(tempDir);

        //2.创建文件上传对象
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setHeaderEncoding("UTF-8");

        //3.获取前端上传的文件
        List<FileItem> fileList = null;
        try {
            fileList = fileUpload.parseRequest(request);
            for(FileItem fileItem : fileList){
                if(fileItem.isFormField()){continue;}
                String uploadFileName = fileItem.getName();
                String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));    //获取文件类型
                fileType = fileType.toLowerCase(Locale.ROOT);
                String realPath = uploadPath + "/" + filename + fileType;
                InputStream input = fileItem.getInputStream();
                FileOutputStream output = new FileOutputStream(realPath);
                byte[] buffer = new byte[1024];
                while(input.read(buffer) > 0){
                    output.write(buffer);
                }
                output.close();
                input.close();
                fileItem.delete();
            }
        } catch (FileUploadException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
