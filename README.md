# Small Demos
##1:User Login and Logout 
Using LoginServlet to create a simplest Webapp of User Login and Logout.
##2:FileDownload
Using DownloadServlet to download file from server side.  
**文件下载案例需求：**  
1：页面显示超链接  
2：点击超链接后弹出下载提示框  
3：完成图片文件下载分析:   
1：使用响应头设置资源的打开方式：contentdisposition:attachment;filename=xx 
步骤：  
	1：创建download.html页面，使href属性指向Servlet，传递资源名称filename  
	2：指定response响应头content-disposition  
	3：定义Servlet：获取文件名称-》使用字节输入流加载文件进内存-》使用字节输出流写出到response输出流  
问题：  
	解决中文文件名乱码  
		：获取客户端浏览器的版本信息  
		：根据不同的版本信息，设置不同的filename编码方式 

