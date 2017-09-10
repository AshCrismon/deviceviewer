package com.huawei.deviceviewer.handler;

import com.huawei.deviceviewer.vo.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@Autowired
	private MessageVO errorMessage;

	private static final Logger logger = LoggerFactory.getLogger("RuntimeException");
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public MessageVO handleServiceException(RuntimeException ex) {
		ex.printStackTrace();
		return renderMessage(ex.getMessage());
	}
	
	public MessageVO renderMessage(String message){
		errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setMsg(message);
		errorMessage.setToken("");
		return errorMessage;
	}

}