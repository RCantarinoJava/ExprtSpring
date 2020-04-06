package com.cantarino.brewer.controllers.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {

	private Page<T> page;
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest request) {
		this.page = page;

		this.uriBuilder = ServletUriComponentsBuilder.fromHttpUrl(montarUrlBugSpace(request));
	}

	private String montarUrlBugSpace(HttpServletRequest request) {

		return request.getRequestURL().append(request.getQueryString() != null ? "?" + request.getQueryString() : "?")
				.toString().replaceAll("\\+", "%20");
	}

	public List<T> getContent() {
		return page.getContent();

	}

	public Boolean isEmpty() {
		return page.getContent().isEmpty();

	}

	public int getNumber() {
		return page.getNumber();

	}

	public int getTotalPages() {
		return page.getTotalPages();

	}

	public Boolean isFirst() {
		return page.isFirst();

	}

	public String buildUrlPage(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}

	public String urlOrdenada(String props) {

		UriComponentsBuilder urlSort = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString());

		String valorSort = String.format("%s,%s", props, inverterDirecao(props));

		return urlSort.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}

	public String inverterDirecao(String props) {
		String direcaoPadrao = "asc";

		Order order = page.getSort() != null ? page.getSort().getOrderFor(props) : null;

		if (order != null)
			direcaoPadrao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";

		return direcaoPadrao;
	}

	public Boolean isLast() {
		return page.isLast();

	}

	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("asc");
	}

	public boolean ordenada(String propriedade) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;

		if (order == null)
			return false;

		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}

}
