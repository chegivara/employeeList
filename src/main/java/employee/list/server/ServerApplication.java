package employee.list.server;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by m.kuznecov on 02.10.2017.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan("employee.list.server")
public class ServerApplication {

    final static Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    @Autowired
    Environment env;

    public static void main(String[] args) {


        SpringApplication.run(ServerApplication.class, args);
    }

}
