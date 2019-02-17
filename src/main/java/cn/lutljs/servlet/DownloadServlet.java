package cn.lutljs.servlet;

import cn.lutljs.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件下载案例
 */
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取文件名称
        String filename = request.getParameter("filename");
        //使用字节输入流加载文件进内存
//        获取文件服务器路径
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/img/" + filename);
        //用字节输入流关联
        FileInputStream fileInputStream = new FileInputStream(realPath);

        //设置响应头
        //设置响应头类型
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);
        //获取客户端浏览器版本信息
        String agent = request.getHeader("user-agent");
        //解决中文文件名问题
        filename = DownLoadUtils.getFileName(agent, filename);
//        设置响应头打开方式
        response.setHeader("content-disposition","attachment;filename="+filename);

        //将输入流的数据写出到输出流当中
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];//字节数组作为缓冲区
        int len=0;
        while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
        }
        fileInputStream.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
