package com.javastudy.coworkings.web.templater;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import java.io.Writer;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class ThymeleafTemplater {
    private static TemplateEngine TEMPLATE_ENGINE = new TemplateEngine();
    private static boolean isConfigured;

    public synchronized static void configTemplate(ServletContext servletContext, Properties properties) {
        if (isConfigured) {
            return;
        }
        isConfigured = true;
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix(properties.getProperty("thymeleaf.prefix", "/WEB-INF/templates/"));
        templateResolver.setSuffix(properties.getProperty("thymeleaf.suffix", ".html"));
        templateResolver.setTemplateMode(properties.getProperty("thymeleaf.template.mode", "HTML"));
        String isCacheable = properties.getProperty("thymeleaf.cacheable", "true");
        templateResolver.setCacheable(Boolean.parseBoolean(isCacheable));
        TEMPLATE_ENGINE.setTemplateResolver(templateResolver);
    }


    public static void process(String template, Map<String, Object> productsMap, Writer writer) {
        IContext context = new Context(Locale.getDefault(), productsMap);
        TEMPLATE_ENGINE.process(template, context, writer);
    }

    public static void process(String template, Writer writer) {
        process(template, Collections.emptyMap(), writer);
    }
}