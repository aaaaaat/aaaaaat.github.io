package org.javaboy.vhr.util.网络编程.UDP协议;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * TODO DatagramSocket代表声明一个UDP协议的Socket
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/6 2:06 下午
 */
public class UDPServer {
    public static void main(String[] args) {
        try {
           //DatagramSocket代表声明一个UDP协议的Socket
            DatagramSocket socket = new DatagramSocket(8888); //byte数组用于数据存储。
            byte[] car = new byte[1024];

            //DatagramPacket 类用来表示数据报包DatagramPacket
            DatagramPacket packet = new DatagramPacket(car, car.length);
            //创建DatagramPacket的receive()方法来进行数据的接收
            //等待接收一个socket请求后才执行后续操作;
            System.out.println("等待UDP协议传输数据");
            socket.receive(packet);
            //packet.getLength返回将要发送或者接收的数据的长度。
            int length = packet.getLength();
            System.out.println("啥东西来了:" + new String(car, 0, length)); socket.close();
            System.out.println("UDP协议Socket接受成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}