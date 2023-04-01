package ro.tedyst.commands;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ro.tedyst.Catalog;

import java.io.*;

public class ReportCommand implements GenericCommand {
    Template template;

    public ReportCommand() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        try {
            cfg.setDirectoryForTemplateLoading(new File("/home/tedy/Git/PA2023_A5/Lab5/templates/"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        try {
            template = cfg.getTemplate("template.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCommand() {
        return "report";
    }

    public void runCommand(Catalog c, String path) throws CommandException {
        try {
            Writer out = new FileWriter(path);
            template.process(c, out);
        } catch (TemplateException e) {
            throw new CommandException(e.toString());
        } catch (IOException e) {
            throw new CommandException(e.toString());
        }
    }
}
