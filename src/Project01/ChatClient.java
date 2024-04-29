package Project01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345);
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("닉네임을 입력하세요:");
            String nickname = userIn.readLine();
            out.println(nickname);

            String line;
            while ((line = userIn.readLine()) != null) {
                if ("/history".equals(line)) {
                    // 서버에게 채팅 내역 요청
                    out.println("/history");
                    // 서버로부터 채팅 내역 받아서 화면에 출력
                    while ((line = serverIn.readLine()) != null) {
                        System.out.println(line);
                    }
                } else {
                    out.println(line);
                }
            }

            // 자원 해제
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}