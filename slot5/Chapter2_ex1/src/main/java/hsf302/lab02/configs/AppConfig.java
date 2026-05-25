package hsf302.lab02.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "hsf302.lab02")
@EnableAspectJAutoProxy
public class AppConfig {
}
