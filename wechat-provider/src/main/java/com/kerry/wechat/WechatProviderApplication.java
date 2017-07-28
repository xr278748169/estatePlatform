package com.kerry.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 微信业务模块服务提供方包含微信功能以及微信接口
 * @description controller服务禁止外部调用
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@ServletComponentScan
public class WechatProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatProviderApplication.class, args);
	}
}
