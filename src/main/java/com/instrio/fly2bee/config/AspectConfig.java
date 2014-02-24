package com.instrio.fly2bee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true) // <aop:aspectj-autoproxy>
public class AspectConfig {

}
