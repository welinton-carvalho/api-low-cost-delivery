package br.com.low.cost.delivery.api.dto;

import javax.validation.constraints.NotBlank;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class NodeDTO {

	private Long id;

	@NotBlank
	private String name;

	public NodeDTO() {

	}

	public NodeDTO(@NotBlank String name) {
		super();
		this.name = name;
	}

	public NodeDTO(Long id, @NotBlank String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NodeDTO [id=" + id + ", name=" + name + "]";
	}

}
