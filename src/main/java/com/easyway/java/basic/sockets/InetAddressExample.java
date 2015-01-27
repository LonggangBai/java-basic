package com.easyway.java.basic.sockets;


import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * InetAddress 测试
 * @author suifeng
 *
 */
public class InetAddressExample {

	public static void main(String[] args) {
		// 获取网络接口和本机关联的地址
		try {
			Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
			
			if(interfaceList == null)
			{
				System.out.println("-----------No networkinterface founded-----------");
			}
			else
			{
				System.out.println("检查本地网卡设置：");
				while(interfaceList.hasMoreElements())
				{
					// 获取到一块网卡
					NetworkInterface networkInterface = interfaceList.nextElement();
					
					// 获取此网卡的地址
					Enumeration<InetAddress> addressList = networkInterface.getInetAddresses();
					
					// 空列表检测
					if(!addressList.hasMoreElements())
					{
						continue;
						//System.out.println("\t No address for this interface");
					}
					
					// 数据每一块网卡的名称、IP类型及其地址
					System.out.println(networkInterface.getName());
					while(addressList.hasMoreElements())
					{
						InetAddress address = addressList.nextElement();
						System.out.println("\tAddress "+((address instanceof Inet4Address) 
								? "(IPV4)"
								: (address instanceof Inet6Address) ? "(IPV6)":"(?)" )+"\t"+address.getHostAddress());
					}
				}
					
			}
		} catch (SocketException e) {
			System.out.println("Error get network interface :"+e.getMessage());
			e.printStackTrace();
		}
		
		// 对于从命令行的输入数据打印出主机名及地址
		System.out.println("");
		for(String host : args)
		{
			System.out.println("Host:"+host);
			try {
				InetAddress[] addressList = InetAddress.getAllByName(host);
				
				for (InetAddress address : addressList) {
					System.out.println("\t"+address.getHostName()+"/"+address.getHostAddress());
				}
			} catch (UnknownHostException e) {
				System.out.println("unable to find address for:"+host);
			}
		}
	}

}
