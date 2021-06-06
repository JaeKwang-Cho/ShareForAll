package com.dorasima.shareforall.data;

import com.dorasima.shareforall.data.model.AgoraMessage;
import com.dorasima.shareforall.data.model.CommentMessage;
import com.dorasima.shareforall.data.model.LoggedMessage;
import com.dorasima.shareforall.data.model.LoginAttemptMessage;
import com.dorasima.shareforall.data.model.Message;
import com.dorasima.shareforall.data.model.RegisterMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class Client extends Thread {
    String Type = "-1";
    // 아고라1 커맨트2 로그인3 로그인시도4 회원가입5
    AgoraMessage Agora;
    CommentMessage Comment;
    LoggedMessage Logged;
    LoginAttemptMessage Attempt;
    RegisterMessage Register;

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
        PrintWriter printWriter;
        int port = 50001;

        //서버로 접속하고 인풋스티림을 지정하는 부분
        try
        {
            while(true)
            {
                System.out.println("시작");
                //ia = InetAddress.getByName(getLocalIpAddress());//서버로 접속 자동을 본인 아이피를 가져옴
                ia = InetAddress.getByName("192.168.56.1");//서버로 접속 테스트시 자신의 아이피를 넣어주면 됩니다.
                socket = new Socket(ia, port);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                printWriter.write(Type);
                System.out.println(Type);
                System.out.println("타입 전송");
                printWriter.flush();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());//직렬화를 위한 객체 생성
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //서버로부터 메시지를 받기위한 버퍼
                System.out.println("직렬화");
                System.out.println(Register.getEmail());
                switch (Type) // 전송을 위한 객체 직렬화
                {
                    case "1":
                        objectOutputStream.writeObject(Agora);
                    case "2":
                        objectOutputStream.writeObject(Comment);
                    case "3":
                        objectOutputStream.writeObject(Logged);
                    case "4":
                        objectOutputStream.writeObject(Attempt);
                    case "5":
                        objectOutputStream.writeObject(Register);
                }
                System.out.println("전송");
                objectOutputStream.flush(); //직렬화를 끝낸 메시지를 타겟 서버로 전송
                System.out.println("확인메시지 수신대기");
                String returnMsg = in.readLine();
                System.out.println("확인메시지 수신");
                System.out.println(returnMsg);

                //객체 정리하는 부분
                objectOutputStream.close();
                socket.close();
                if(returnMsg.equals("1"))
                    break;
            }
        } catch(IOException e) {System.err.println("서버 접속 오류, 접속IP : "+ ia + " 접속 포트 : " + port);}
    }

    public void setType1(AgoraMessage agora)
    {
        Type = "1";
        this.Agora = agora;
    }
    public void setType2(CommentMessage comment)
    {
        Type = "2";
        this.Comment = comment;
    }
    public void setType3(LoggedMessage Logged)
    {
        Type = "3";
        this.Logged = Logged;
    }
    public void setType4(LoginAttemptMessage Attempt)
    {
        Type = "4";
        this.Attempt = Attempt;
    }
    public void setType5(RegisterMessage register)
    {
        Type = "5";
        this.Register = register;
    }

}
