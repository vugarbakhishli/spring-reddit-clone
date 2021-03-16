package az.bakhishli.redditclone;

import az.bakhishli.redditclone.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class RedditcloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditcloneApplication.class, args);
	}

}
