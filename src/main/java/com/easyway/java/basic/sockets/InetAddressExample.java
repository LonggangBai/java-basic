package com.easyway.java.basic.sockets;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 
 * <pre>
 * 
 * 
 * 
 * OSI 七层模型称为开放式系统互联参考模型 OSI 七层模型是一种框架性的设计方法   OSI 
 * 七层模型通过七个层次化的结构模型使不同的系统不同的网络之间实现可靠的通讯，因此其最主  要的功能使就是帮助不同类型的主机实现数据传输  
 *  	物理层 ： O S I 模型的最低层或第一层，该层包括物理连网媒介，如电缆连线连接器。物理层的协议产生并检测电压以便发送和接收携带数据的信号。
 * 在你的桌面P C 上插入网络接口卡，你就建立了计算机连网的基础。换言之，你提供了一个物理层。尽管物理层不提供纠错服务，但它能够设定数据传输速率并监测数据出错率。
 * 网络物理问题，如电线断开，将影响物理层。  
 *   	数据链路层： O S I 模型的第二层，它控制网络层与物理层之间的通信。它的主要功能是如何在不可靠的物理线路上进行数据的可靠传递。为了保证传输，
 * 从网络层接收到的数据被分割成特定的可被物理层传输的帧。帧是用来移动数据的结构包，它不仅包括原始数据，还包括发送方和接收方的网络地址以及纠错和控制信息。
 * 其中的地址确定了帧将发送到何处，而纠错和控制信息则确保帧无差错到达。   数据链路层的功能独立于网络和它的节点和所采用的物理层类型，它也不关心是否正在运行
 * Wo r d 、E x c e l 或使用I n t e r n e t 。有一些连接设备，如交换机，由于它们要对帧解码并使用帧信息将数据发送到正确的接收方，所以它们是工作在数据链路层的。   
 *      网络层： O S I 模型的第三层，其主要功能是将网络地址翻译成对应的物理地址，并决定如何将数据从发送方路由到接收方。   网络层通过综合考虑发送优先权、网络拥塞程度、
 * 服务质量以及可选路由的花费来决定从一个网络中节点A 到另一个网络中节点B 的最佳路径。由于网络层处理路由，而路由器因为即连接网络各段，并智能指导数据传送，属于网络层。
 * 在网络中，“路由”是基于编址方案、使用模式以及可达性来指引数据的发送。  
 *      传输层： O S I 模型中最重要的一层。传输协议同时进行流量控制或是基于接收方可接收数据的快慢程度规定适当的发送速率。除此之外，传输层按照网络能处理的最大尺寸
 * 将较长的数据包进行强制分割。例如，以太网无法接收大于1 5 0 0 字节的数据包。发送方节点的传输层将数据分割成较小的数据片，同时对每一数据片安排一序列号，以
 * 便数据到达接收方节点的传输层时，能以正确的顺序重组。该过程即被称为排序。   工作在传输层的一种服务是 T C P / I P 协议套中的T C P （传输控制协议），另一
 * 项传输层服务是I P X / S P X 协议集的S P X （序列包交换）。  
 *      会话层： 负责在网络中的两节点之间建立和维持通信。 会话层的功能包括：建立通信链接，保持会话过程通信链接的畅通，同步两个节点之间的对 话，决定通信是否被中断以及
 * 通信中断时决定从何处重新发送。    
 *      表示层： 应用程序和网络之间的翻译官，在表示层，数据将按照网络能理解的方案进行格式化；这种格式化也因所使用网络的类型不同而不同。  
 * 表示层管理数据的解密与加密，如系统口令的处理。例如：在 Internet上查询你银行账户，使用的即是一种安全连接。你的账户数据在发送前被加密，在网络的另一端，
 * 表示层将对接收到的数据解密。除此之外，表示层协议还对图片和文件格式信息进行解码和编码。  
 *  	应用层： 负责对软件提供接口以使程序能使用网络服务。术语“应用层”并不是指运行在网络上的某个特别应用程序 ，应用层提供的服务包括文件传输、文件管理以及电子邮件的信息处理。
 * </pre>
 * 
 * InetAddress 测试
 * 
 * @author suifeng
 * 
 */
public class InetAddressExample {

	public static void main(String[] args) {
		// 获取网络接口和本机关联的地址
		try {
			Enumeration<NetworkInterface> interfaceList = NetworkInterface
					.getNetworkInterfaces();

			if (interfaceList == null) {
				System.out
						.println("-----------No networkinterface founded-----------");
			} else {
				System.out.println("检查本地网卡设置：");
				while (interfaceList.hasMoreElements()) {
					// 获取到一块网卡
					NetworkInterface networkInterface = interfaceList
							.nextElement();

					// 获取此网卡的地址
					Enumeration<InetAddress> addressList = networkInterface
							.getInetAddresses();

					// 空列表检测
					if (!addressList.hasMoreElements()) {
						continue;
						// System.out.println("\t No address for this interface");
					}

					// 数据每一块网卡的名称、IP类型及其地址
					System.out.println(networkInterface.getName());
					while (addressList.hasMoreElements()) {
						InetAddress address = addressList.nextElement();
						System.out
								.println("\tAddress "
										+ ((address instanceof Inet4Address) ? "(IPV4)"
												: (address instanceof Inet6Address) ? "(IPV6)"
														: "(?)") + "\t"
										+ address.getHostAddress());
					}
				}

			}
		} catch (SocketException e) {
			System.out
					.println("Error get network interface :" + e.getMessage());
			e.printStackTrace();
		}

		// 对于从命令行的输入数据打印出主机名及地址
		System.out.println("");
		for (String host : args) {
			System.out.println("Host:" + host);
			try {
				InetAddress[] addressList = InetAddress.getAllByName(host);

				for (InetAddress address : addressList) {
					System.out.println("\t" + address.getHostName() + "/"
							+ address.getHostAddress());
				}
			} catch (UnknownHostException e) {
				System.out.println("unable to find address for:" + host);
			}
		}
	}

}
