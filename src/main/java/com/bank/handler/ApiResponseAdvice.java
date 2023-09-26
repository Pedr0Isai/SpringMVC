package com.bank.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.bank.dto.ApiResponseDto;

@RestControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

	/**
	 * Este método se llama antes de beforeBodyWrite y se utiliza para agregar
	 * condiciones personalizadas que controlan si el advice debe actuar sobre la
	 * respuesta en curso o no.
	 *
	 * @param returnType    Tipo de valor de retorno del controlador que está
	 *                      generando la respuesta
	 * @param converterType Tipo de convertidor de mensaje HTTP que se utilizará
	 *                      para escribir la respuesta
	 * @return true, si es verdadero se aplica el advice para la respuesta en curso
	 */
	@Override
	public boolean supports(MethodParameter returnType, @SuppressWarnings("rawtypes") Class converterType) {
		/**
		 * Se pueden agregar condiciones basadas en returnType y converterType. Si las
		 * condiciones se cumplen, se devuelve true para indicar que el advice debe
		 * aplicarse a la respuesta en curso. Si no se cumplen, se devuelve false para
		 * que el advice se omita y no se aplique a esta respuesta en particular.
		 * 
		 * ejemplo1. Verificar si el objeto de retorno es de tipo MyResponseDto: return
		 * returnType.getParameterType() == MyResponseDto.class;
		 * 
		 * ejemplo2. Verificar si el convertidor es de tipo MappingJackson2HttpMessageConverter: 
		 * return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
		 */
		boolean continua = false;
		/** Se obtiene el nombre del package desde el cual se lanzó el response*/
		String packageName = returnType.getExecutable().getDeclaringClass().getPackageName();
		/** Verifica si el packageName contiene 'com.bank' */
		if(packageName.contains("com.bank")) {
			continua = true;
		}
		return continua;
	}

	/** Este método se llama antes de escribir el cuerpo de la respuesta */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			@SuppressWarnings("rawtypes") Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		/** Se obtiene el response del servlet */
		ServletServerHttpResponse servletResponse = (ServletServerHttpResponse) response;
		/** Se modifica el cuerpo de ApiResponseDto antes de que se envíe */
		ApiResponseDto apiResponse = new ApiResponseDto(servletResponse.getServletResponse().getStatus(),
				request.getURI().getPath());
		apiResponse.setResponse(body);

		return apiResponse;
	}
}