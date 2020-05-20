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

Brewer.MaskDate = (function() {

	function MaskDate() {
		this.inputDate = $('.js-date');
	}

	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation : 'bottom',
			language : 'pt-BR',
			autoclose : true
		});
	}

	return MaskDate;

}());

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

}());

Brewer.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
	
}());

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

	var cepMask = new Brewer.CepMask();
	cepMask.enable();

	var maskDate = new Brewer.MaskDate();
	maskDate.enable();
	
	var security = new Brewer.Security();
	security.enable();

});