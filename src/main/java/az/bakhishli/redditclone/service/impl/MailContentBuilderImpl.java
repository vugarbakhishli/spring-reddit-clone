package az.bakhishli.redditclone.service.impl;

import az.bakhishli.redditclone.service.MailContentBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailContentBuilderImpl implements MailContentBuilder {

    private final TemplateEngine templateEngine;

    @Override
    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }
}
