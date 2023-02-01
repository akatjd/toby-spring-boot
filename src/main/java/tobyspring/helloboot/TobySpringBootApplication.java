package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;

//@Configuration
//@ComponentScan
@MySpringBootApplication
public class TobySpringBootApplication {

//    @Bean
//    public HelloController helloController(HelloService helloService) {
//        return new HelloController(helloService);
//    }
//
//    @Bean
//    public HelloService helloService() {
//        return new SimpleHelloService();
//    }

    // factory method
    // Config class로 이동
//    @Bean
//    public ServletWebServerFactory servletWebServerFactory() {
//        return new TomcatServletWebServerFactory();
//    }
//
//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet();
//    }

    public static void main(String[] args) {
//        MySpringApplication.run(TobySpringBootApplication.class, args);
        SpringApplication.run(TobySpringBootApplication.class, args);
    }
}
