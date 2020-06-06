var Brewer = Brewer || {};

Brewer.MultiSelecao = (function() {

	function MultiSelecao() {

		this.statusBtn =  $('.js-status-btn');
		this.selecaoCheck =  $('.js-selecao');
		
		
	}

	MultiSelecao.prototype.iniciar = function() {
		
		this.statusBtn.on('click' , onAlterarStatus.bind(this));		
	}
	
	function onAlterarStatus(event)
	{
		var botao = $(event.currentTarget);	
		var status = botao.data('status');
		var filterSelecionado = this.selecaoCheck.filter(':checked');	
		var codigos = $.map(filterSelecionado, function(c) {			
			return $(c).data('codigo');			
		});
		
		
		if(codigo.length < 0)
			return;
		
		$.ajax({
			url: '',
			method: 'PUT',
			data: { codigos: codigos , status : status  },
			sucess: function()
			{
				window.location.reload();
			}
			
		
		});
		
		
		
		
	}
	

	return MultiSelecao;

})();

$(function() {
	var multiSelection = new Brewer.MultiSelecao();
	multiSelection.iniciar();
});