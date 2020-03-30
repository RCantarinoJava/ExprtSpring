var Brewer = Brewer || {}

Brewer.EstiloCadastroRapidoAjax = (function() {

	function EstiloCadastroRapidoAjax() {
		this.modal = $('#modalCadastroRapidoEstilo');
		this.btnSalvar = this.modal.find('.js-salvar-btn');
		this.form = this.modal.find('form');
		this._url = this.form.attr('action');
		this.inputNome = $('#nomeEstilo');
		this.containerErro = $('.js-mensagem');
	}

	EstiloCadastroRapidoAjax.prototype.start = function() {

		this.form.on('submit', function(event) {
			event.preventDefault()
		});

		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.btnSalvar.on('click', onBotaoSalvarClick.bind(this));

	}
	
	function onModalShow() {
		this.inputNome.focus();
	}

 	function onModalClose() {
 		this.inputNome.val('');
 		this.containerErro.addClass('hidden');
	}

	function onBotaoSalvarClick() {
		var estiloNome = this.inputNome.val().trim();

		$.ajax({
			url : this._url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				'nome' : estiloNome
			}),
			error : onErroSalvarEstilo.bind(this),
			success : onEstiloSalvo.bind(this)

		});
	} 
	
	function onEstiloSalvo(estilo) {
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome
				+ '</option>');
		comboEstilo.val(estilo.codigo);
		this.modal.modal('hide');

	}
	
	function onErroSalvarEstilo(obj) {
		var msgErro = obj.responseText;
		this.containerErro.removeClass('hidden');
		this.containerErro.html('<span>' + msgErro + '</span>')
	}  

	return EstiloCadastroRapidoAjax;
}());

$(function() {
	
	var cadastroAjax = new Brewer.EstiloCadastroRapidoAjax();
	cadastroAjax.start();
})