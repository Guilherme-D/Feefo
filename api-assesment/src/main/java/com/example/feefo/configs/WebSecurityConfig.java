package com.example.feefo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.cors().and().csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/*","/v2/api-docs", "/swagger-resources/**",
				"/swagger-ui.html", "/webjars/springfox-swagger-ui/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				//codigo anterior continua...
				.antMatchers("/h2-console/**").permitAll()
				.and().csrf().disable().headers().frameOptions().disable();

	}
//	@Bean
//	public 	WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedOrigins("http://localhost:4200", "http://localhost:8080")
//						.allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");
//				//.addMapping("/*").allowedOrigins("*");
//			}
//		};
//	}
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//		return source;
//	}
}
