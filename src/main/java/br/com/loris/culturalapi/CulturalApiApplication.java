package br.com.loris.culturalapi;

import br.com.loris.culturalapi.service.CompanyService;
import br.com.loris.culturalapi.service.CulturalGenreService;
import br.com.loris.culturalapi.service.CulturalProductService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CulturalApiApplication {
	private final CulturalGenreService culturalGenreService;
	private final CompanyService companyService;
	private final CulturalProductService culturalProductService;

	public static void main(String[] args) {
		SpringApplication.run(CulturalApiApplication.class, args);
	}

	public CulturalApiApplication(CulturalGenreService culturalGenreService,
								  CompanyService companyService,
								  CulturalProductService culturalProductService){
		this.culturalGenreService = culturalGenreService;
		this.companyService = companyService;
		this.culturalProductService = culturalProductService;
	}

	@Bean
	public ApplicationRunner init() {
		return args -> {
			System.out.println("Populate database with some data");
			culturalGenreService.populateDB();
			companyService.populateDB();
			culturalProductService.populateDB();
		};
	}
}
