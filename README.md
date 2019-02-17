# Small Demos
## 1:用户登录退出   
Using LoginServlet to create a simplest Webapp of User Login and Logout.  
登录成功显示上次登录时间。

## 2:文件下载
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
## 3:使用cookie判断是否是第一次登录
记住上一次访问时间：  
　　访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。  
　　如果不是第一次访问，则提示：欢迎回来，您上次的访问时间为：显示字符串。  
分析：  
　　访问Servlet，使用request对象获取它所有的cookies，遍历，判断是否存在名为lastTime的cookie  
　　　存在：获取lastTime的值并写回客户端，同时获取当前时间更新名lastTime的cookie，写回客户端。  
　　　不存在：创建名为lastTime，值为当前时间的cookie，并写回客户端。


