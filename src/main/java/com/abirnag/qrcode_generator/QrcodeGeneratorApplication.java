package com.abirnag.qrcode_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class QrcodeGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrcodeGeneratorApplication.class, args);
	}

}
