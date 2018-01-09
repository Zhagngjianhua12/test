//创建聊天对象
var Chat = {};
//登录界面
Chat.login=function(){
		layer.open({
					title : '用户登录',
					id:'mylayer',
					type : 1,
					shade : 0.6, //遮罩透明度
					moveType : 1, //拖拽风格，0是默认，1是传统拖动
					shift : 1, //0-6的动画形式，-1不开启
					closeBtn : false, //不显示关闭按钮
					skin : 'layui-layer-lan', //加上边框
					area : [ '360px', '240px' ], //宽高
					content : '<div id="login" class="logdiv big"><form id="myform" action="user" method="post">'
							+ '用户名:<input class="uname" name="uname" placeholder="输入用户名" /><br/>'
							+ '<img class="img" src="img/logo.jpg" onclick="return $(\'#myform\').submit();"/></form></div>'
		});
		
		//注册enter事件
		$(".uname").keydown(function(e){
			 if(e.which==13){ //enter	 					
				 $('#myform').submit();				 
			 }							 
		 });
}
//---------------webSocket相关代码--------------------------------
//建立连接 
Chat.socket = null;
Chat.connect=(function(host){
		/*创建WebSocket对象，同时建立连接考虑浏览器兼容性*/
	 	if ('WebSocket' in window) {
	 		Chat.socket=new WebSocket(host);
	    } else if ('MozWebSocket' in window) {
	    	Chat.socket=new MozWebSocket(host);
	    } else {
	        Console.log('Error: 浏览器不支持websocket.');
	        return;
	    }
	 	
	 	/*打开连接成功,准备发送消息*/
	 	Chat.socket.onopen=function(evt){		 		
	 		//注册键盘事件	 		
	 		 $(".chat__input").keydown(function(e){
				 if(e.which==13){ //e.which==13&&e.ctrlKey ctrl+enter	 					
					 Chat.sendMsg(); //发送 信息						 
				 }							 
			 });	 		
	 		//发送信息
	 	}
	 	
	 	/*接收服务器响应信息*/
	 	Chat.socket.onmessage=function(evt){
	 		//分析结果
	 		Chat.analystMsg(evt.data);
	 	}
	 	
	 	/*退出聊天室*/
	 	Chat.socket.onclose=function(){
	 		//alert("滚，你被踢了...");
	 		$(".chat__input").val("踢死你");
			$(".chat__input").attr("disabled",true); 
	 		//console.log('退出了....');
			//ajax 退出 
			exit();
	 	}
});

//发送消息
Chat.sendMsg=(function(){
	//获取值
	var str=$('.chat__input').val();
	//发送信息
	Chat.socket.send(str);
	//情况内容
	$('.chat__input').val("");	
});

//分析消息
Chat.analystMsg =function(message){
	
	message=jQuery.parseJSON(decodeURI(message));
	//登录
	if(message.type=="login"){			
		//显示会员列表
 		listMember(message.members);
		$('.chat__messages').append(append('sys',message.content,true));
		return ;			
	}else if(message.type=="logout"){//退出
		//显示会员列表
 		listMember(message.members);
		$('.chat__messages').append(append('sys',message.content,true));
		return ;
	}else if(message.type=="group"){//群聊
		$('.chat__messages').append(append(message.from,message.content,true));	
		return ;
	}else if(message.type=="secret"){//私聊
		if(message.from==uname){
			$('.chat__messages').append(append(message.from,"您悄悄地对"+message.to+"说:"+message.content,false));	
		}else{
			$('.chat__messages').append(append(message.from,message.from+"悄悄地对您说:"+message.content,false));	
		}
		return ;
	}else if(message.type=="advert"){ //系统消息
		$('.chat__messages').append(append('sys',"系统消息:"+message.content,false));
	}else if(message.type=="forbit"){ //禁言
		$(".chat__input").val("别捣蛋了...暂停说话");
		$(".chat__input").attr("disabled",true); 
	}else if(message.type=="rid"){ //解除禁言
		$(".chat__input").val("乖乖听话");
		$(".chat__input").attr("disabled",false); 
	}
}



/*拼接字符串*/
function append(nickName,content,secret){
	var str='<div class="chat__msgRow"><div class="chat__message ';
	if(secret){ //私密聊天
		str+='mine';
	}else{
		str+='notMine';
	}				
	str+='"><img src="img/'+nickName+'.png" title="'+nickName+'" />'+content+'</div></div>';
	
	
	return str;
}
/*
 *列出在线用户 
 * @param members
 */
function listMember(members){	
	$('.sidebar-content').html('');
	$('#online').html('');
	var msStr ="";
	$.each(members,function(n,value) { 
			msStr+= appendMember(value);
        });  
	$('.sidebar-content').append(msStr);  
	$('#online').html(members.length);
	
}
/*
 * exit
 */
function exit(){
	 $.ajax({     
		    url:'user',     
		    type:'post',     
		    data:'action=leave',     
		    async : false, //默认为true 异步     
		    error:function(){     
		       alert('error');     
		    },     
		    success:function(data){  
		    	if(data=="leave"){
		    		location.reload();
		    	}
	 	    }
	 });  
}


/*拼接字符串*/
function appendMember(nickName){
	var str='<div class="contact"><img  title="'+nickName+'" src="img/'+nickName+'.png" class="contact__photo" />'+
	'<span	class="contact__name">'+nickName+'</span><span'+
			'class="contact__status online"></span></div>';
	return str;
}
