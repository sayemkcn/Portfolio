package sayem.picoapps.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = { "sayem.picoapps.controllers",
		"sayem.picoapps.services" }, basePackageClasses = SecurityConfig.class)
@EntityScan(basePackages = { "sayem.picoapps.domains", "sayem.picoapps.domains.embedded" })
@EnableJpaRepositories(basePackages = "sayem.picoapps.repositories")
@EnableTransactionManagement
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

}
