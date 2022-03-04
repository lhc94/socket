package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientFile {
	
	Socket socket;
	BufferedWriter bw;
	BufferedReader br;
	
	public ClientFile() {
		try {
			System.out.println("1.클라이언트 소켓 시작------------------");
			socket  = new Socket("localhost", 10000);// 현재 라인 실행시 -> 서버소켓의 accept() 메서드가 호출
			//소켓과 클라이언트 소켓이 연결이된 상태
			
			System.out.println("2. 버퍼(write) 연결완료-------------------");
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//키보드에 입력받아 쓰고싶다
			System.out.println("3. 키보드 스트림 + 버퍼(read) 연결완료-------------------");
			br = new BufferedReader(new InputStreamReader(System.in));

//			System.out.println("4. 키보드 메세지 입력 대기중-------------------");
//			String keyboardMsg = br.readLine();
			//메세지 끝을 알려줘야한다. 통신의 규칙임
//			bw.write(keyboardMsg + "\n");
//			bw.flush();
			// 버퍼가 생각보다 크기때문에 flush를 써 줘야함
			
			//단방향으로 계속 통신하려면
			while(true) {
				System.out.println("4. 키보드 메세지 입력 대기중-------------------");
				String keyboardMsg = br.readLine();
				bw.write(keyboardMsg + "\n");
				bw.flush();
			}
		} catch (Exception e) {
			System.out.println("클라이언트소켓 에러 발생함 : " + e.getMessage());
		}
	}
	public static void main(String[] args) {
		new ClientFile();
	}
}
