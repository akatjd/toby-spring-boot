package tobyspring.helloboot;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tobyspring.config.EnableMyAutoConfiguration;
import tobyspring.config.autoconfig.DispatcherServletConfig;
import tobyspring.config.autoconfig.TomcatWebServerConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // TYPE -> class, interface, enum
@Configuration
@ComponentScan
// 직접 추가해줄 수 있음. 패키지가 달라도 config class 주입할 수 있음.
//@Import({DispatcherServletConfig.class, TomcatWebServerConfig.class})
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {

}
