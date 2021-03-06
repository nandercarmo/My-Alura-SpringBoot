package br.com.alura.mvc.mudi.dto;

import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.model.User;

public class RequestNewOrderDTO {

	// Mesmos nomes que no input
	
	@NotBlank(message = "Esse campo é obrigatório!") //Também poderia ser feita a alteração pelo arquivo messages.properties alterando a propriedade NotBlank.requestNewOrderDTO.productName
	private String productName;
	
	@NotBlank(message = "Esse campo é obrigatório!") //Também poderia ser feita a alteração pelo arquivo messages.properties alterando a propriedade NotBlank.requestNewOrderDTO.productUrl
	private String productUrl;
	
	@NotBlank(message = "Esse campo é obrigatório!") //Também poderia ser feita a alteração pelo arquivo messages.properties alterando a propriedade NotBlank.requestNewOrderDTO.imageUrl
	private String imageUrl;
	
	private String description;

	public Order toOrder(User user) {

		return new Order(
			this.productName,
			this.productUrl,
			this.imageUrl,
			this.description,
			user,
			OrderStatus.AGUARDANDO
		);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
