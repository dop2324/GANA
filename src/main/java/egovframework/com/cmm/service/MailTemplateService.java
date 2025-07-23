package egovframework.com.cmm.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.Map;

@Component
public class MailTemplateService {

	private final Configuration freemarkerConfig;
	
	public MailTemplateService() throws Exception {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/egovframework/mail/templates");
        freemarkerConfig.setDefaultEncoding("UTF-8");
    }

    public String parseTemplate(String templateName, Map<String, Object> model) throws Exception {
        Template template = freemarkerConfig.getTemplate(templateName);
        try (StringWriter out = new StringWriter()) {
            template.process(model, out);
            return out.toString();
        }
    }
}
