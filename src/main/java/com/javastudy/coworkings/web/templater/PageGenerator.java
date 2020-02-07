package com.javastudy.coworkings.web.templater;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class PageGenerator {

    private static PageGenerator pageGenerator;
    private final TemplateEngine templateEngine = new TemplateEngine();


    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, WebContext webContext) {
        return templateEngine.process(filename, webContext);
    }

    private PageGenerator() {
        ClassLoaderTemplateResolver templateResolver=new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateEngine.addTemplateResolver(templateResolver);
    }
}
