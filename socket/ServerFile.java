package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


// 서버 소켓만들기
public class ServerFile {
	
	ServerSocket serverSocket; // 서버소켓
	Socket socket; // 서버소켓의 새 소켓
//		소켓 클래스가 왜 두 개 ??
//		서버소켓의 역할 : 연결만 받아 새 소켓을 만들어 클라이언트 소켓이랑 연결된다
//		그 새로운 소켓은 포트번호를 랜덤으로 선정한다
//		그리고 클라이언트 소켓과 실제 ByteStream으로 연결된다
	BufferedReader br;

	public ServerFile() {
		System.out.println("1. 서버소켓 시작-----------------");
		try {
			serverSocket = new ServerSocket(10000); 
			// 10000포트의 소켓을 생성
			// 일단 모든 포트를 차단시켜 놓음 
			// 외부에서 악성코드나 해커등 방해 요소들이 여러포트에서 비집고 들어올 수 있으니까 
			// 개발자가 열기를 원하는 포트번호만 연다 
			// 그래서 열면서 방화벽관련한 문구가 뜬다
			// 물어보는거임
			System.out.println("2. 소버소켓 생성완료 : 클라이언트 접속 대기중-------------------");
			socket = serverSocket.accept(); // 클라이언트 접속 대기중...
			//어디서부터 어디까지?
			// 서버소켓 실행
			// 클라이언트 소켓 실행
			// 서버소켓으로 ip주소:포트번호 를 서버소켓으로 연결시도
			// ClientFile 클래스에서 socket  = new Socket("localhost", 10000);가 실행 될 때 까지
			
			System.out.println("3. 클라이언트 연결완료");
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// getInputStream() : Byte Stream 연결시키기
			// new BufferedReader(new InputStreamReader) : 소켓에다가 버퍼를 달기 위해서
			
//			String msg = br.readLine();
//			System.out.println("4. 클라이언트로 부터 받은 메시지 : " + msg);
			
			//단방향으로 계속 통신하려면
			while(true) {
				String msg = br.readLine();
				System.out.println("4. 클라이언트로 부터 받은 메시지 : " + msg);	
			}
		} catch (Exception e) {
			System.out.println("서버소켓 에러 발생함 : " + e.getMessage());
		}
	
	}
	public static void main(String[] args) {
		new ServerFile();
	}
}
