package org.initial.heart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class AuthServerApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void restfulGet(){
		RestTemplate restTemplate=new RestTemplate();

		String url = "http://127.0.0.1:8090/test/users?username={username}";
		String url2 = "http://127.0.0.1:8090/test/users/laowan";

		//1、使用getForObject请求接口,  顺序传入参数
		String result1 = restTemplate.getForObject(url, String.class, "你好");
		System.out.println("result1====================" + result1);

		//2、使用getForObject请求接口   使用HashMap传入参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", "laowan");
		String result2 = restTemplate.getForObject(url, String.class, paramMap);
		System.out.println("result2====================" + result2);

		//3、使用url路径变量PathVariable
		String result3 = restTemplate.getForObject(url2, String.class);
		System.out.println("result3====================" + result3);

		//4、使用exchange请求接口
		ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.GET, null, String.class,paramMap);
		System.out.println("result4====================" + response2.getBody());

		//5、使用exchange请求接口，可以封装HttpEntity对象传递header参数
		HttpHeaders headers = new HttpHeaders();
		headers.set("username", "laowan");
		HttpEntity httpEntity = new HttpEntity(null,headers);
		ResponseEntity<String> response5 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class,paramMap);
		System.out.println("result5====================" + response5.getHeaders());
		/**
		 * result1====================传入的参数你好
		 * result2====================传入的参数你好
		 * result3====================传入的参数laowan
		 * result4====================传入的参数laowan
		 * result5====================
		 * [Content-Type:"text/plain;charset=UTF-8", Content-Length:"21", Date:"Sun, 12 Dec 2021 14:48:38 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]
		 */
	}

}
