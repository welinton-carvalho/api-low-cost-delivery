package br.com.low.cost.delivery.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class ResponseServiceDTO<E> {

	private E result;

	private List<ErrorDTO> errors = new ArrayList<>();

	public ResponseServiceDTO() {

	}

	public E getResult() {
		return result;
	}

	public void setResult(E result) {
		this.result = result;
	}

	public List<ErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDTO> errors) {
		this.errors = errors;
	}

	public void addError(ErrorDTO error) {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		this.errors.add(error);
	}

	public Boolean hasError() {
		return errors != null && !errors.isEmpty();
	}

	@Override
	public String toString() {
		return "ResponseServiceDTO [result=" + result + ", errors=" + errors + "]";
	}

}
