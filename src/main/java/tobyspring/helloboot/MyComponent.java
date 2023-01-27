package tobyspring.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 언제까지 살아있을 것인지 지정. RUNTIME 동안.
@Retention(RetentionPolicy.RUNTIME)
// 어디에 붙을 것인가
@Target(ElementType.TYPE)
@Component
public @interface MyComponent {
}
