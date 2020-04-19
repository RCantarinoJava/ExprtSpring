var Brewer = Brewer || {}

Brewer.MaskMoney = (function() {

	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}

	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney({
			decimal : ',',
			thousands : '.'
		});

		this.plain.maskMoney({
			precision : 0,
			thousands : '.'
		});

	}
	return MaskMoney;

}())

Brewer.FoneMask = (function() {

	function FoneMask() {
		this.inputTelefone = $('.js-telefone');
	}

	FoneMask.prototype.enable = function() {
		var maskBehavior = function(val) {
			return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000'
					: '(00) 0000-00009';
		};

		var options = {
			onKeyPress : function(val, e, field, options) {
				field.mask(maskBehavior.apply({}, arguments), options);
			}
		};

		this.inputTelefone.mask(maskBehavior, options);
	}
	return FoneMask;

}())

Brewer.DocumentsMask = (function() {

	function DocumentsMask() {
		this.radioPessoa = $('.js-radio-tipoPessoa');
		this.labelCpfCnpj = $('[for=cpfOuCnpj]');
		this.inputCpfCnpj = $('#cpfOuCnpj');
	}

	DocumentsMask.prototype.enable = function() {
		this.radioPessoa.on('change', onTipoPessoaAlterado.bind(this))
		var tipoPessoaSelecionada = this.radioPessoa.filter(':checked')[0]
		if (tipoPessoaSelecionada)
			aplicarMascara.call(this, $(tipoPessoaSelecionada));
	}

	function onTipoPessoaAlterado(event) {
		var tipoPessoaSelecionada = $(event.currentTarget);
		aplicarMascara.call(this, tipoPessoaSelecionada);
		this.inputCpfCnpj.val('');

	}

	function aplicarMascara(tipoPessoaSelecionada) {
		this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'))

		this.inputCpfCnpj.removeAttr('disabled');
	}

	return DocumentsMask;

}())

Brewer.CepMask = (function() {

	function CepMask() {
		this.inputCep = $('.js-cep');
	}

	CepMask.prototype.enable = function() {
		this.inputCep.mask('00.000-000');
	}

	return CepMask;

}())

$(function() {
	var maskMoney = new Brewer.MaskMoney();
	maskMoney.enable();

	var phoneMask = new Brewer.FoneMask();
	phoneMask.enable();

	var documentoMask = new Brewer.DocumentsMask();
	documentoMask.enable();

	var cepMask = new Brewer.CepMask();
	cepMask.enable();
});