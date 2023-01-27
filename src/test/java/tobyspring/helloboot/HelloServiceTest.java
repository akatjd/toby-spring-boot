package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("Test");

        // 인텔리제이 라이브 템플릿 만들어두면 sout처럼 단축키 만들어서 쓸 수 있음
        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }
}
