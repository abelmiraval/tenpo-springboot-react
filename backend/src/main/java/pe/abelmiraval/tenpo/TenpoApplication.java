package pe.abelmiraval.tenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.DispatchableHandler;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.DispatchableProcessor;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.DispatchableTimer;

@SpringBootApplication
public class TenpoApplication {

    private final ApplicationContext applicationContext;

    public TenpoApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(TenpoApplication.class, args);
    }


    @Bean
    public DispatchableHandler dispatchableHandler() {
        return new DispatchableTimer(new DispatchableProcessor(this.applicationContext));
    }

}