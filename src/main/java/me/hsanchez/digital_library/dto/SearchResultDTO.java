package me.hsanchez.digital_library.dto;

import java.util.List;

public class SearchResultDTO<T> {
	private Long total;
	private List<T> results;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

}
