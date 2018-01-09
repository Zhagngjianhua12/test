<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<title>头条号：IT蛇精病原创 聊天室</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/default.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<script type="text/javascript" src='js/jquery-2.1.4.min.js'></script>
<script type="text/javascript" src='js/stopExecutionOnTimeout.js?t=1'></script>
<script src="js/layer/layer.js"></script>
<script src="js/chat.js"></script>
<style>
#myform {
	border: 1px dotted #aaaaaa;
	padding: 30px 6px 0px 10px;
	width: 300px;
	height:180px;
	font-size: 18px;
	text-align: center;
}
.big {
    padding: 5px 20px;
    padding-top: 5px;
    height: 30px;
    text-transform: uppercase;
    font: bold 14px/16px Arial, sans-serif;
}

.img{
	 padding: 30px 20px;	
	 cursor:pointer;
 }
</style>
<script>
var uname='${uname}';
console.log(uname+"-------------->");
//弹出一个页面层
$(function() {
	if(uname==''){
		Chat.login();	
	}else{
		//连接服务器
		Chat.connect("ws://localhost:8080/room/start?nickname="+'${uname}');			
	}
});	
</script>
</head>
<body>

	<div class="demo">
		<svg class="sidebar" viewBox="0 0 300 500">
	    <path class="s-path" fill="#fff"
				d="M0,0 50,0 a0,250 0 1,1 0,500 L0,500" />
	  </svg>
		<div class="static" >
			<div class="static__text">在线用户数<span style="padding:45px;color:#900;font-size:30px" id="online"></span>				
			</div>
	
		</div>		
		<div class="sidebar-content">				
			
		</div>
		<div class="chat">
			<span class="chat__back"></span>
			<div class="chat__person">
				<span class="chat__online active"></span> <span class="chat__name">蛇精病</span>
			</div>
			<div class="chat__messages">
				<div class="chat__msgRow"></div>				
			</div>
			<input type="text" class="chat__input" placeholder="输入信息，私聊也可以使用@姓名" />
		</div>
		<a style="padding:100px 130px;font-size:20px" id="exit" href="javascript:exit()">退出</a>
	</div>
	
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>

