package com.kerry.estate;

import com.kerry.estate.filter.CrossDomainFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class EstateConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstateConsumerApplication.class, args);
	}


	/**
	 * 跨域处理器注册
	 * @return
	 */
	@Bean
	public FilterRegistrationBean crossDomainFilterRegistr(){
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CrossDomainFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("crossDomainFilter");
		registration.setOrder(1);
		return registration;
	}

}
