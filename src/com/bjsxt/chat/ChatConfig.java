package com.bjsxt.chat;
import java.util.Set;
import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
/**
 * ���ýӿ�(ʵ����ڵ�ɨ��) ������Ŀ����ʱ�Զ����ã�����������listener
 * @author  ͷ���ţ�IT�߾���ԭ��
 *
 */
public class ChatConfig implements ServerApplicationConfig{
	/**
	 * ����ע��:������ ��
	 */
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned ) {
		System.out.println("ɨ���ն��豸..."+scanned.size());
		
		//ҵ���߼�:���Զ�Set�е��豸���д������ն��豸���й���		
		
		return scanned; //���뽫�豸���Ϸ���
	}
	
	/**
	 *���ڽӿ�: ��ͳ �Ͻ� 
	 */
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {
		return null;
	}
}