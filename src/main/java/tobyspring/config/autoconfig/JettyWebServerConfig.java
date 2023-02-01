package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import tobyspring.config.MyAutoConfiguration;

//@Configuration
@MyAutoConfiguration // 안에 @Configuration 있음
@Conditional(JettyWebServerConfig.JettyCondition.class)
public class JettyWebServerConfig {
    // bean 이름은 기본적으로 메소드이름을 따라감
    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }

    static class JettyCondition implements Condition {
        // Jetty or Tomcat 중 사용할 서버 return true로 바꿔줌. 두개 동시 true면 오류 뜸.
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }
}
