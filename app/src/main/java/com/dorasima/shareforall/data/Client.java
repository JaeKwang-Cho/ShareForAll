package com.dorasima.shareforall.data;

import com.dorasima.shareforall.data.model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class Client extends Thread {
    Message message = new Message();

    public Client(Message message)
    {
        this.message = message;
    }

    public static String getLocalIpAddress()
    {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void run()
    {
        Socket socket = null;            //Server와 통신하기 위한 Socket
        BufferedReader in = null;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
        PrintWriter out = null;            //서버로 내보내기 위한 출력 스트림
        InetAddress ia = null;
        int port = 50001;

        //서버로 접속하고 인풋스티림을 지정하는 부분
        try
        {
            while(true)
            {
                //ia = InetAddress.getByName(getLocalIpAddress());//서버로 접속 자동을 본인 아이피를 가져옴
                ia = InetAddress.getByName("192.168.56.1");//서버로 접속 테스트시 자신의 아이피를 넣어주면 됩니다.
                socket = new Socket(ia, port);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());//직렬화를 위한 객체 생성
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //서버로부터 메시지를 받기위한 버퍼
                objectOutputStream.writeObject(message); // 전송을 위한 객체 직렬화
                objectOutputStream.flush(); //직렬화를 끝낸 메시지를 타겟 서버로 전송
                String returnMsg = in.readLine();
                System.out.println(returnMsg);

                //객체 정리하는 부분
                objectOutputStream.close();
                socket.close();
                if(returnMsg.equals("1"))
                    break;
            }
        } catch(IOException e) {System.err.println("서버 접속 오류, 접속IP : "+ ia + " 접속 포트 : " + port);}
    }
}
