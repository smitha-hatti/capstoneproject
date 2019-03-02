package com.capstone.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Lob;

import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Items" )				//, uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private int id;

	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_desc", length= 255, nullable = false)
	private String itemDescription;

	
	@Column(name = "item_category", length= 20, nullable = false)
	private String itemCategory;
	
	@Lob
	@Column(name = "item_image", length = Integer.MAX_VALUE,nullable = true)
	private String image;

	@Column(name = "item_price", nullable = false)
	private BigDecimal itemPrice;
	
	@Column(name = "item_stock", nullable = false)
    @Min(value = 0, message = "*Quantity has to be non negative number")
    private Integer itemStock ;
	
	@Column(name="item_type")
	private String itemType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemStock() {
		return itemStock;
	}

	public void setItemStock(Integer itemStock) {
		this.itemStock = itemStock;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void ifPresent(Object object) {
		
	}




}
