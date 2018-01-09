1、跑项目时请先查看当前文件
2、访问项目路径：我的tomcat更改了端口
 http://localhost:8888/room/chat.jsp  
3、各位注意一下，小编这里使用的字符集合是GBK
4、小编这里使用的环境：
	jdk1.7 + tomcat8
	所以当前目录中WEB-INF中的
			   |——lib
			       |——tomcat-websocket.jar 是可以省略的 
5、以上内容都属于头条号：IT蛇精病原创，欢迎品鉴
6、聊天方式：
	@用户名: 实现私聊
	bt:用户名  实现T人
	rb:用户名  实现禁言
	还有其他功能 请查看ChatType.java