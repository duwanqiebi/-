package com.zhangqiang.sqgl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 等同于@Configuration @EnableAutoConfiguration @ComponentScan
 * 
 * 
 *@ComponentScan : 开启组件扫描，@Component, @Service, @Repository, @Controller等将被自动注册为Spring Beans
 * **/

@SpringBootApplication 
public class Application {
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
