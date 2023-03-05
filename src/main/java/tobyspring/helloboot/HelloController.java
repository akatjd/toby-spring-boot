package tobyspring.helloboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

// RestController는 DispatcherServlet하고 직접 관련은 없음 나중에 Spring container를 적용하는 또 다른 시점에 필요함
//@RestController
//@RequestMapping("/hello")
// 내가 만든 MyComponent안에 @Component가 있어 인식함
//@MyComponent
// @RestController가 @ResponseBody 포함하고 있음. 메서드 쪽에 @ResponseBody 안붙여도 됨.
@RestController
//public class HelloController implements ApplicationContextAware {
public class HelloController {
    private final HelloService helloService;
    // ApplicationContext final 붙으면 안됨. 늦어도 생성자가 완료되는 시점까지 초기화가 되어야 하는데 이것은 이미 생성자를 통해 인스턴스가 다 만들어진 이후에 호출되는 메소드여서 불가능함.
    private ApplicationContext applicationContext;

//    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
//        this.helloService = helloService;
//        this.applicationContext = applicationContext;
//        System.out.println(applicationContext);
//    }

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // RequestMapping 안붙이고 GetMapping만 하면 검색에 너무 오래걸림
    // String 으로 return 하면 해당 String 값의 View가 있는지 체크해서 404 에러뜸. ResponseBody에 담아 리턴하면 됨. @RestController가 class 앞에 붙으면 메서드에 @ResponseBody가 붙어있는걸로 인식함.
    @GetMapping("/hello")
//    @ResponseBody
    public String hello(String name) {
//        SimpleHelloService simpleHelloService = new SimpleHelloService();

        if(name == null || name.trim().length() == 0) throw new IllegalArgumentException();

        // Objects.requireNonNull object를 던져서 null이면 throw
        return helloService.sayHello(Objects.requireNonNull(name));
    }


    int cnt = 0;
    @GetMapping("/1")
//    @ResponseBody
    public void hello1() throws InterruptedException {
        for(int i=0; cnt<100; i++) {
            System.out.println("hello 1 :: " + cnt);
            Thread.sleep(500);
            cnt++;
        }
    }

    @GetMapping("/2")
    public void hello2() throws InterruptedException{
        for(int i=0; cnt<100; i++) {
            System.out.println("mmmmmello 2 :: " + cnt);
            Thread.sleep(500);
            cnt++;
        }
    }



//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println(applicationContext);
//        this.applicationContext = applicationContext;
//    }
}
