package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void helloController() {
        // test stub
        // helloservice 인터페이스 하나니까 람다로 만들어도 됨.
//        HelloController helloController = new HelloController(new HelloService() {
//            @Override
//            public String sayHello(String name) {
//                return null;
//            }
//        });
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }

    // container 없는 테스트와 있는 테스트 시간 차이는 10배이상 남.
    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> name);

        // NullPointerException이 던져지면 성공
        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        // "" 과 null은 다르니 예외처리가 안됨
        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
