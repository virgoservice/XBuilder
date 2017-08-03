package com.ramostear.xbuilder.kit;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONMessageConvertor extends AbstractHttpMessageConverter<Object> {
	@Override
	protected Object readInternal(Class<? extends Object> clz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean supports(Class<?> clz) {
		return true;
	}

	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String jsonString = JSON.toJSONString(t,
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
		OutputStream os = outputMessage.getBody();
		os.write(jsonString.getBytes("UTF-8"));
		os.flush();
	}
}
