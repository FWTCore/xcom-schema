package org.xcom.shcema.infra.starter;

import com.github.dreamroute.sqlprinter.starter.anno.EnableSQLPrinter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * XcomInfraStarterApplication
 *
 * @author xcom
 * @date 2024/8/9
 */

@Slf4j
@EnableSQLPrinter
@SpringBootApplication
public class XcomInfraStarterApplication {

    public static void main(String[] args) throws UnknownHostException {
        ApplicationContext applicationContext = SpringApplication.run(XcomInfraStarterApplication.class, args);
        Environment environment = applicationContext.getEnvironment();

        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String servletStr = environment.getProperty("server.servlet.context-path");
        String path = "";
        if (StringUtils.isNotBlank(servletStr)) {
            path = servletStr.trim();
        }

        log.info("web application has started : {}, CPU core : {}"
                , Arrays.toString(environment.getActiveProfiles()), Runtime.getRuntime().availableProcessors());

        log.info("server.port:" + environment.getProperty("server.port"));

        log.info("\n----------------------------------------------------------\n\t" +
                "Application " + applicationContext.getApplicationName() + " is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
