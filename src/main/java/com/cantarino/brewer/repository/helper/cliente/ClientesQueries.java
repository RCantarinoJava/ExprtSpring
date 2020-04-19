package com.cantarino.brewer.repository.helper.cliente;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cantarino.brewer.model.Cliente;
import com.cantarino.brewer.repository.filter.ClienteFilter;

public interface ClientesQueries {
	
	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
}
