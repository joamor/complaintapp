import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.complaintapp.api.**",
        "com.complaintapp.domain.**",
        "com.complaintapp.infrastructure.**"
})
public class ComplaintApp {

    public static void main(String[] args) {
        SpringApplication.run(ComplaintApp.class, args);
    }

}
