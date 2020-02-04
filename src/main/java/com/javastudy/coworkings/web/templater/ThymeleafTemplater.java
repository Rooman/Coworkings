package com.javastudy.coworkings.web.templater;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import java.io.Writer;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

public class ThymeleafTemplater {
    private static final TemplateEngine TEMPLATE_ENGINE = new TemplateEngine();
    private static boolean isCreated;

    public ThymeleafTemplater() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

    }

    public static void configureNewInstance(ServletContext servletContext) {
        if (isCreated) {
            return;
        }

        isCreated = true;
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        TEMPLATE_ENGINE.setTemplateResolver(templateResolver);
    }

    public static void process(String templateName, Writer writer) {
        process(templateName, Collections.emptyMap(), writer);
    }

    public static void process(String templateName, Map<String, Object> variablesMap, Writer writer) {
        IContext context = new Context(Locale.getDefault(), variablesMap);

        TEMPLATE_ENGINE.process(templateName, context, writer);
    }
}