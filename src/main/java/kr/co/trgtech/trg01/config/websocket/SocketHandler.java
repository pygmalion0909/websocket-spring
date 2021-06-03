package kr.co.trgtech.trg01.config.websocket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.trgtech.trg01.dto.TalkDto;
import kr.co.trgtech.trg01.service.TalkService;

@Component
public class SocketHandler extends TextWebSocketHandler{

	private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);
	
	@Autowired
	private TalkService talkService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	//웹소켓 세션을 담아둘 맵
	private Map<String, List<WebSocketSession>> channelMap = new HashMap<>();
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonMappingException, JsonProcessingException {
		logger.debug("socketTextMessage==={}", message.getPayload());
		/**
		 * DB저장을 위한
		 */
		//json parser
		//front -> server로 받아 json문자열을 parser. for save db
		TalkDto talkData = objectMapper.readValue(message.getPayload(), TalkDto.class);
		
		//insert data		
		//parser된 데이터에 현재 날짜 주입 -> db저장
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		talkData.setCreateDate(format.format(date));
		talkService.insertTalk(talkData);
		
		/**
		 * websocket으로 메세지 전달
		 */
		//front로 전달할 메세지(talkData)Object를 json문자열로 변환
		String msg = objectMapper.writeValueAsString(talkData);
		
		//session정보로 현재 채널에 접속한 session list 가져오기
		List<WebSocketSession> sameChannelSessionList = new ArrayList<>();
		
		for(String key : channelMap.keySet()) {
			int size = channelMap.get(key).size();
			for(int i = 0; i < size; i++) {
				if(channelMap.get(key).get(i).getId().equals(session.getId())) {
					sameChannelSessionList.addAll(channelMap.get(key));
				}
			}
		}
		
		for(WebSocketSession wss : sameChannelSessionList) {
			try {
				wss.sendMessage(new TextMessage(msg));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
		ByteBuffer byteBuffer = message.getPayload();
		logger.debug("binaryMessage=={}", byteBuffer);
//		//바이너리 메시지 발송
//		ByteBuffer byteBuffer = message.getPayload();
//		String fileName = "temp.jpg";
//		File dir = new File(FILE_UPLOAD_PATH);
//		if(!dir.exists()) {
//			dir.mkdirs();
//		}
//		
//		File file = new File(FILE_UPLOAD_PATH, fileName);
//		FileOutputStream out = null;
//		FileChannel outChannel = null;
//		try {
//			byteBuffer.flip(); //byteBuffer를 읽기 위해 세팅
//			out = new FileOutputStream(file, true); //생성을 위해 OutputStream을 연다.
//			outChannel = out.getChannel(); //채널을 열고
//			byteBuffer.compact(); //파일을 복사한다.
//			outChannel.write(byteBuffer); //파일을 쓴다.
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(out != null) {
//					out.close();
//				}
//				if(outChannel != null) {
//					outChannel.close();
//				}
//			}catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		byteBuffer.position(0); //파일을 저장하면서 position값이 변경되었으므로 0으로 초기화한다.
//		//파일쓰기가 끝나면 이미지를 발송한다.
//		HashMap<String, Object> temp = rls.get(fileUploadIdx);
//		for(String k : temp.keySet()) {
//			if(k.equals("roomNumber")) {
//				continue;
//			}
//			WebSocketSession wss = (WebSocketSession) temp.get(k);
//			try {
//				wss.sendMessage(new BinaryMessage(byteBuffer)); //초기화된 버퍼를 발송한다.
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	/**
	 * @title 소켓연결
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);

		//채널id 가져오기
		String channelId = session.getUri().getPath().split("/", 3)[2];
		
		//채널이 있다면 session만 넣기
		if(channelMap.containsKey(channelId)) {
			channelMap.get(channelId).add(session);
		}else {
			//채널이 없다면 채널Id, session 넣기
			List<WebSocketSession> socketSession = new ArrayList<>();
			socketSession.add(session);
			channelMap.put(channelId, socketSession);			
		}
		logger.debug("===sp/save session/channelMap==={}", channelMap);
	}
	
	/**
	 * @title 소켓종료
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		for(String key : channelMap.keySet()) {
			int size = channelMap.get(key).size();
			//session삭제
			for(int i = 0; i < size; i++) {
				if(channelMap.get(key).get(i).getId().equals(session.getId())) {
					channelMap.get(key).remove(i);
					//break없을 경우 size와 배열 index랑 다르기 때문에 channelMap에는 삭제 후 값이 없는데 가져올려고 해서 IndexOutOfBoundsException뜸
					break;
				}
			}
			//채널에 session이 없다면 채널 삭제
			if(channelMap.get(key).size() <= 0) {
				//ConcurentModificationException가 발생할때가 있음, https://m.blog.naver.com/tmondev/220393974518참고
				channelMap.remove(key); 
			}
		}
		logger.debug("===sp/remove session/channelMap==={}", channelMap);
		super.afterConnectionClosed(session, status);
	}
	
}
