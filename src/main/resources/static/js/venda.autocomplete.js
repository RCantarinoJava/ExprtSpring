Brewer = Brewer || {};

Brewer.Autocomplete = (function() {

	function Autocomplete() {
		this.inputSku = $('.js-sku-nome-cerveja-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);

	}
	Autocomplete.prototype.start = function() {

		var opts = {
			url: function(skuOuNome) {
				return '/brewer/Cerveja?skuOuNome=' + skuOuNome
			},

			getValue: 'nome',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: function(nome, cerveja) {
					cerveja.valorFormatado = Brewer.formatarMoeda(cerveja.valor);
					return this.template(cerveja);
				}.bind(this)
			}


		};

		this.inputSku.easyAutocomplete(opts)
	}


	return Autocomplete;


}());



$(function() {

	var autocomplete = new Brewer.Autocomplete();
	autocomplete.start();
})

