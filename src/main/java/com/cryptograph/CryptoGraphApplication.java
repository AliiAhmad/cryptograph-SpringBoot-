package com.cryptograph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@SpringBootApplication
@EnableScheduling
@ComponentScan( basePackages = "com.cryptograph" )
public class CryptoGraphApplication extends SpringBootServletInitializer
{

    public static void main(String[] args)
    {
        SpringApplication.run(CryptoGraphApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(CryptoGraphApplication.class);
    }
    /* tiles configuration */
    @Bean
    public UrlBasedViewResolver tilesViewResolver()
    {
        UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();      
        tilesViewResolver.setViewClass(TilesView.class);
        return tilesViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer()
    {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        String[] defs = { "/WEB-INF/tiles.xml" };
        tilesConfigurer.setDefinitions(defs);
        return tilesConfigurer;
    }
}
