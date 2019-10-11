package br.com.low.cost.delivery.api.dto;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class ErrorDTO {

	private String code;

	private String message;

	public ErrorDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ErrorDTO [code=" + code + ", message=" + message + "]";
	}

}
