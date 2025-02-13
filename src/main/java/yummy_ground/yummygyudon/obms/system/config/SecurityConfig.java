package yummy_ground.yummygyudon.obms.system.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import yummy_ground.yummygyudon.obms.support.constant.RequestConstant;
import yummy_ground.yummygyudon.obms.system.filter.JwtExceptionFilter;
import yummy_ground.yummygyudon.obms.system.filter.JwtAuthenticationFilter;
import yummy_ground.yummygyudon.obms.system.handler.AccessDeniedExceptionHandler;
import yummy_ground.yummygyudon.obms.system.handler.CustomAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String SLASH = "/";
    private static final String WILDCARD_ALL = "**";
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final AccessDeniedExceptionHandler accessDeniedExceptionHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Profile("test")
    public SecurityFilterChain filterChainForTest(HttpSecurity http) throws Exception {
        setDefaultHttp(http);
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    @Profile({"local", "dev"})
    public SecurityFilterChain filterChainForDevelopment(HttpSecurity http) throws Exception {
        setDefaultHttp(http);
        setApiSpecification(http);
        setSecuredHttp(http);
        addFilters(http);
        return http.build();
    }

    @Bean
    @Profile("prod")
    public SecurityFilterChain filterChainForProduction(HttpSecurity http) throws Exception {
        setDefaultHttp(http);
        setSecuredHttp(http);
        addFilters(http);
        return http.build();
    }

    private void setDefaultHttp(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }

    private void setApiSpecification(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers(new AntPathRequestMatcher(String.join(SLASH, RequestConstant.API_DOCS_URI_PREFIX, WILDCARD_ALL)))
                .permitAll());
    }

    private void setSecuredHttp(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers(new AntPathRequestMatcher(String.join(SLASH, RequestConstant.AUTH_URI_PREFIX, WILDCARD_ALL)))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher(String.join(SLASH, RequestConstant.ACTUATOR_URI_PREFIX, WILDCARD_ALL)))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher(String.join(SLASH, RequestConstant.ERROR_URI_PREFIX, WILDCARD_ALL)))
                .permitAll()
                .anyRequest().authenticated());
    }

    private void addFilters(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class)
                .exceptionHandling(handler -> handler
                        .accessDeniedHandler(accessDeniedExceptionHandler)
                        .authenticationEntryPoint(authenticationEntryPoint)
                );
    }

}
