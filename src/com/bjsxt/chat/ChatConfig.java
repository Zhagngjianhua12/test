package com.bjsxt.chat;
import java.util.Set;
import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
/**
 * 配置接口(实现入口点扫描) ，随项目启动时自动调用，类似于其他listener
 * @author  头条号：IT蛇精病原创
 *
 */
public class ChatConfig implements ServerApplicationConfig{
	/**
	 * 基于注解:零配置 简单
	 */
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned ) {
		System.out.println("扫描终端设备..."+scanned.size());
		
		//业务逻辑:可以对Set中的设备进行处理，对终端设备进行过滤		
		
		return scanned; //必须将设备集合返回
	}
	
	/**
	 *基于接口: 传统 严谨 
	 */
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {
		return null;
	}
}