package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
        // 추상화 시켜놔서 톰캣말고 제티 등의 다른 서블릿 컨테이너도 지원 가능 (동일하게 동작)
        // 빈 컨테이너 서블릿 실행 (톰캣 띄우고 HelloController get request 날려도 404에러 뜸)
        // 독립 실행이 가능한 Servlet application
//        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

//        WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
//            @Override
//            public void onStartup(ServletContext servletContext) throws ServletException {
//
//            }
//        });

//        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                // super 생략 하면 안됨.
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this);

//                WebServer webServer = serverFactory.getWebServer(servletContext -> {
//                    servletContext.addServlet("dispatcherServlet",
//                            // 자기 자신을 참조하니 this
//                            new DispatcherServlet(this)
//                    ).addMapping("/*");
//                });

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };
//        applicationContext.registerBean(HelloController.class);
        // bean 등록시에는 interface가 아닌 어떤 service class를 사용할건지 넣어줘야 함
//        applicationContext.registerBean(SimpleHelloService.class);
        // TobySpringBootApplication에서 시작해서 bean 등록
//        applicationContext.register(TobySpringBootApplication.class);
        // run method로 리팩토링 후 main 메서드에서 해당 클래스를 파라미터로 넘겨서 실행
        applicationContext.register(applicationClass);
        applicationContext.refresh();


        // lamda 변환
//        WebServer webServer = serverFactory.getWebServer(servletContext -> {
//            servletContext.addServlet("hello", new HttpServlet() {
//                @Override
//                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                    String name = req.getParameter("name");
//                    // spring enum 사용
////                    resp.setStatus(200);
//                    resp.setStatus(HttpStatus.OK.value());
////                    resp.setHeader("Content-Type", "text/plain");
//                    // spring enum 사용
//                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//                    resp.getWriter().println("Hello Servlet ~ Hello :: " + name);
//                }
//            }).addMapping("/hello");

        // front controller 만들기
//            servletContext.addServlet("frontcontroller", new HttpServlet() {
//                HelloController helloController = new HelloController();
//                @Override
//                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                    // 인증, 보안, 다국어, 공통 기능
//                    if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
//                        String name = req.getParameter("name");
//                        String ret = helloController.hello(name);
//                        resp.setStatus(HttpStatus.OK.value());
//                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//                        resp.getWriter().println(ret);
//                    }else if(req.getRequestURI().equals("/user")) {
//                        // user 로직 실행
//                    }else {
//                        resp.setStatus(HttpStatus.NOT_FOUND.value());
//                    }
//
//                }
//            }).addMapping("/*");


//            servletContext.addServlet("frontcontroller", new HttpServlet() {
//                @Override
//                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                    // 인증, 보안, 다국어, 공통 기능
//                    if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
//                        String name = req.getParameter("name");
//                        HelloController helloController = applicationContext.getBean(HelloController.class);
//                        String ret = helloController.hello(name);
//                        // 안넣으면 기본적으로 200으로 날아가니 생략 가능
////                        resp.setStatus(HttpStatus.OK.value());
////                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//                        // content type도 아래와 같이 줄일 수 있음
//                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
//                        resp.getWriter().println(ret);
//                    }else if(req.getRequestURI().equals("/user")) {
//                        // user 로직 실행
//                    }else {
//                        resp.setStatus(HttpStatus.NOT_FOUND.value());
//                    }
//
//                }
//            }).addMapping("/*");

//            servletContext.addServlet("dispatcherServlet",
//                    new DispatcherServlet(applicationContext)
//            ).addMapping("/*");
//        });
//        webServer.start();
    }
}
