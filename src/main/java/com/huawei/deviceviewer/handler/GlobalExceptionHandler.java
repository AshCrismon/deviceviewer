package com.huawei.deviceviewer.handler;

import com.huawei.deviceviewer.vo.MessageVO;
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
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public MessageVO handleServiceException(RuntimeException ex) {
		return renderMessage(ex.getMessage());
	}
	
	public MessageVO renderMessage(String message){
		errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setMsg(message);
		errorMessage.setToken("");
		return errorMessage;
	}

}