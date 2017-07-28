package com.kerry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 系统管理服务提供者
 * @description controller服务禁止外部调用
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@ServletComponentScan
public class SysProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysProviderApplication.class, args);
	}
}
