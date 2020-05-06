package com.cantarino.brewer.validations.valitator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.cantarino.brewer.validations.Compare;

public class ComparacaoValidator implements ConstraintValidator<Compare, Object> {

	private String atributo;
	private String atributoConfirmacao;

	@Override
	public void initialize(Compare constraintAnnotation) {
		this.atributo = constraintAnnotation.atributo();
		this.atributoConfirmacao = constraintAnnotation.atributoComparacao();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		boolean _valid = false;
		try {

			Object valorAtributo = BeanUtils.getProperty(value, this.atributo);
			Object valorAtributoConfirmacao = BeanUtils.getProperty(value, this.atributoConfirmacao);

			_valid = areNull(valorAtributo, valorAtributoConfirmacao)
					|| areSame(valorAtributo, valorAtributoConfirmacao);

			if (!_valid)
				showViolation(context);

		} catch (Exception e) {
			throw new RuntimeException("Erro recuperando valores dos atributos", e);
		}

		return _valid;
	}

	private void showViolation(ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		String msg = context.getDefaultConstraintMessageTemplate();
		ConstraintViolationBuilder builder = context.buildConstraintViolationWithTemplate(msg);
		builder.addPropertyNode(atributoConfirmacao).addConstraintViolation();

	}

	private boolean areNull(Object valorAtributo, Object valorAtributoConfirmacao) {
		return (valorAtributo == null && valorAtributoConfirmacao == null);
	}

	private boolean areSame(Object valorAtributo, Object valorAtributoConfirmacao) {
		return valorAtributo != null && valorAtributo.equals(valorAtributoConfirmacao);
	}

}
