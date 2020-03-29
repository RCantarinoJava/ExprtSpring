$(function() {

	var modal = $('#modalCadastroRapidoEstilo');
	var btnSalvar = modal.find('.js-salvar-btn');
	var form = modal.find('form');
	var _url = form.attr('action');
	var inputNome = $('#nomeEstilo');
	var containerErro = $('.js-mensagem')

	form.on('submit', function(event) {
		event.preventDefault()
	});

	modal.on('hide.bs.modal', onModalClose);
	modal.on('shown.bs.modal', onModalShow);
	btnSalvar.on('click', onBotaoSalvarClick);

	function onModalShow() {
		inputNome.focus();
	}

	function onModalClose() {
		inputNome.val('');
		containerErro.addClass('hidden')
	}

	function onBotaoSalvarClick() {
		var estiloNome = inputNome.val().trim();

		$.ajax({
			url : _url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				'nome' : estiloNome
			}),
			error : onErroSalvarEstilo,
			success : onEstiloSalvo

		})
	}

	function onEstiloSalvo(estilo) {
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo);
		modal.modal('hide');

	}
	function onErroSalvarEstilo(obj) {
		var msgErro = obj.responseText;
		containerErro.removeClass('hidden');
		containerErro.html('<span>' + msgErro + '</span>')

	}

})