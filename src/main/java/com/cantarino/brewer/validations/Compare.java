package com.cantarino.brewer.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.cantarino.brewer.validations.valitator.ComparacaoValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {  ComparacaoValidator.class   })
public @interface Compare {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Atributos não são correspondentes";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	
	String atributo();
	String atributoComparacao();
	

}
