package com.Controller;

import com.Service.FileUploadService;
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


       if(FileUploadService.imageUpload(
               request,
               getServletContext().getRealPath(CONSTANTS.IMAGE_UPLOAD.RELATIVE_PATH),
               getServletContext().getRealPath(CONSTANTS.IMAGE_UPLOAD.RELATIVE_TMP_PATH),
               username
       )){
            response.getWriter().write("{\"success\":true}");
       } else{
           response.getWriter().write("{\"success\":false}");
       }

    }
}
