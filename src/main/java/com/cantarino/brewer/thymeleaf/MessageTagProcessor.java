package com.cantarino.brewer.thymeleaf;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class MessageTagProcessor extends AbstractElementTagProcessor {

	private static final String TAG_NAME = "message";
	private static final int PRECEDENCIA = 1000;

	public MessageTagProcessor(String dialectPrefix) {

		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCIA);

	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {

		IModelFactory modelfactory = context.getModelFactory();
		IModel model = modelfactory.createModel();

		model.add(modelfactory.createStandaloneElementTag("th:block", "th:replace", "layout/fragments/MensagemSucesso :: alert"));
		model.add(modelfactory.createStandaloneElementTag("th:block", "th:replace","layout/fragments/MensagensErroValidacao :: alert"));
		
		structureHandler.replaceWith(model, true);

	}

}
