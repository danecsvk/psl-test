package sk.stuba.fiit.psl.test;

import java.io.FileReader;
import java.io.IOException;
import javax.sql.DataSource;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbiConfiguration {

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driver;

	@Bean
	public DataSource getDataSource() throws IOException {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driver);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		dataSourceBuilder.url(url);
		return dataSourceBuilder.build();
	}

	@Bean
	public Jdbi jdbi(DataSource ds) throws Exception {
		String version = new MavenXpp3Reader().read(new FileReader("pom.xml")).getVersion();
		//return Jdbi.create(ds, pslDirectory, model);
		return Jdbi.create(ds);
	}
}
