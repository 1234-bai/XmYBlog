package com.Controller;

import com.Util.CONSTANTS;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Locale;

public class ImageUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute(CONSTANTS.USER_DATA.USERNAME);

        //判断上传表单是否是带文件表单
        if(!ServletFileUpload.isMultipartContent(request)){
            return;
        }

        //创建文件保存路径
        String uploadPath = this.getServletContext().getRealPath(CONSTANTS.IMAGE_UPLOAD.RELATIVE_PATH);
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        //缓存
        String tempPath = this.getServletContext().getRealPath(CONSTANTS.IMAGE_UPLOAD.RELATIVE_TMP_PATH);
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
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for(FileItem fileItem : fileList){
            if(fileItem.isFormField()){continue;}
            String uploadFileName = fileItem.getName();
            String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
            fileType = fileType.toLowerCase(Locale.ROOT);
            String realPath = uploadPath + "/" + username + fileType;
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
        response.getWriter().write("{\"success\":true}");
    }
}
