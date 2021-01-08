package com.purchase.controller.log;

import com.purchase.controller.log.vo.FileListVO;
import com.purchase.utils.WebPathUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;

/**
 * @author Miracle
 * @date 2020/12/20 0:44
 */
@Controller
@RequestMapping("logs")
public class SystemLogsController {


    @RequestMapping(value = "/logsList")
    public void userInfoIndex(Model model, HttpServletRequest request, HttpServletResponse response, String filePath,String passWord,String userName) throws IOException, NoSuchAlgorithmException {
        if(passWord==null||!userName.equals("admin")||!passWord.equals("203533")){
            return;
        }
        String path=System.getProperty("user.dir")+"\\logs\\";
        String domain= WebPathUtil.getWebBasePath(request);
        File file=null;
        if(filePath!=null){
            filePath= new String(Base64.getDecoder().decode(filePath));
            file=new File(System.getProperty("user.dir")+"\\logs\\"+filePath+"\\");
        }else{
            file=new File(System.getProperty("user.dir")+"\\logs\\");
        }
        if(file==null){
            return;
        }
        if(!file.exists()){
            return;
        }
        File[] files=file.listFiles();
        if(files.length==0){
            return;
        }
        List<FileListVO> fileListVOList=new ArrayList<>();
        for (File fileInfo : files) {
            FileListVO fileListVO=new FileListVO();
            fileListVO.setFileName(fileInfo.getName());
            fileListVO.setFilePath(fileInfo.getPath().replace(path,""));
            if(fileInfo.isFile()){
                fileListVO.setIsFile(1);
            }else{
                fileListVO.setIsFile(2);
            }
            fileListVO.setLastModified(fileInfo.lastModified());
            fileListVOList.add(fileListVO);

        }
        fileListVOList.sort(Comparator.comparing(FileListVO::getLastModified).reversed());
        StringBuffer sb=new StringBuffer();
        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"en\">");
        sb.append("<body>");
        for (FileListVO fileListVO : fileListVOList) {
            if(fileListVO.getIsFile().equals(1)){
                sb.append("<h1 style=\"margin-left: 200px;\"><a href=\""+domain+"\\logs\\logs\\"+fileListVO.getFilePath()+"\">"+fileListVO.getFileName()+"\t"+fileListVO.getLastModified()+"</a></h1>");
            }else{
                sb.append("<h1 style=\"margin-left: 200px;\"><a href=\""+domain+"\\logs\\logsList?userName=admin&passWord=203533&filePath="+Base64.getEncoder().encodeToString(fileListVO.getFilePath().getBytes())+"\">"+fileListVO.getFileName()+"\t"+fileListVO.getLastModified()+"</a></h1>");
            }
        }
        sb.append("</body>");
        sb.append("</html>");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(sb.toString());
    }




}
