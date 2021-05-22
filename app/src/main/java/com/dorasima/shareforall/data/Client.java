package com.dorasima.shareforall.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;



public class Client {

    public void MsgSende(Message message)
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
                ia = InetAddress.getByName(getIP());//서버로 접속
                System.out.println("서버 접속 시도");
                //ia = InetAddress.getByName("192.168.56.1");//서버로 접속
                socket = new Socket(ia, port);
                System.out.println("서버 접속 완료");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());//직렬화를 위한 객체 생성
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //서버로부터 메시지를 받기위한 버퍼
                objectOutputStream.writeObject(message); // 전송을 위한 객체 직렬화
                System.out.println("객체 직렬화 완료");
                objectOutputStream.flush(); //직렬화를 끝낸 메시지를 타겟 서버로 전송
                System.out.println("전송");
                String returnMsg = in.readLine();
                System.out.println(returnMsg);

                //객체 정리하는 부분
                objectOutputStream.close();
                socket.close();
                if(returnMsg.equals("1"))
                    break;
            }
        } catch(IOException e) {System.err.println("서버 접속 오류, 접속IP :"+ia+" 접속 포트 : " + port);}
    }

    public static String getIP()
    {
        InetAddress local = null;
        String ip = "-1";
        try
        {
            local = InetAddress.getLocalHost();
            ip = local.getHostAddress();
            return ip;
        }
        catch (Exception e) { System.out.println(e); return ip;}
    }
}

