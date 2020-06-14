package cn.zeroce.design.chengwen.core.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author: zeroce
 * @date 20.3.22 21:22
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        final MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();

        postProcessor.setValidator(this.validatorFailFast());
        return postProcessor;

    }

    @Bean
    public Validator validatorFailFast() {
        final ValidatorFactory validatorFactory =
                Validation.byProvider(HibernateValidator.class)
                    .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
