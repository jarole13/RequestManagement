package com.bluemedia.app.impl.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = { ReasonValidator.class })
@Target({ ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ReasonValidity {
	String message() default "";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
