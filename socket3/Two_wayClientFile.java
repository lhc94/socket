package socket3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Two_wayClientFile {
	
	Socket socket;
	BufferedWriter bw;
	BufferedReader keyboard;
	BufferedReader br;
	
	public Two_wayClientFile() {
		try {
			System.out.println("1.클라이언트 소켓 시작------------------");
			socket  = new Socket("localhost", 10000);// 현재 라인 실행시 -> 서버소켓의 accept() 메서드가 호출
			//소켓과 클라이언트 소켓이 연결이된 상태
			
			System.out.println("2. 버퍼(write) 연결완료-------------------");
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//키보드에 입력받아 쓰고싶다
			System.out.println("3. 키보드 스트림 + 버퍼(read) 연결완료-------------------");
			keyboard = new BufferedReader(new InputStreamReader(System.in));

			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// 새로운 스레드 역할 (글 읽기)
			ReadThread rt = new ReadThread();
			Thread t1 = new Thread(rt);
			t1.start();
			 
			// 메인 스레드의 역할 (글 쓰기)
			while(true) {
				System.out.println("4. 키보드 메세지 입력 대기중-------------------");
				String keyboardMsg = keyboard.readLine();
				bw.write(keyboardMsg + "\n");
				bw.flush();
			}
		} catch (Exception e) {
			System.out.println("클라이언트소켓 에러 발생함 : " + e.getMessage());
		}
		
	}
	class ReadThread implements Runnable {
		@Override
		public void run() {
			while(true) {
				try {
					String msg = br.readLine();
					System.out.println("서버로 부터받은 메시지 : " + msg);
				} catch (Exception e) {
					System.out.println("클라이언트 소켓쪽에서 서버소켓 메시지를 입력받는 중 오류가 발생했습니다 : " + e.getMessage()); 
				}
			}
		}
	}
	public static void main(String[] args) {
		new Two_wayClientFile();
	}
}
