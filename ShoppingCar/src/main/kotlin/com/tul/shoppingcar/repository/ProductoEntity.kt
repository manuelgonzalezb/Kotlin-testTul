package com.tul.shoppingcar.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@Entity
@Data
@Table(name = "producto")
data class ProductoEntity(
	@Id
	@JsonProperty(value = "uuid") val uuid: String,
	@Column(name = "nombre") @JsonProperty(value = "nombre")  val nombre: String,
	@Column(name = "descripcion") @JsonProperty(value = "descripcion") val descripcion: String,
	@Column(name = "sku") @JsonProperty(value = "sku") val sku: String,
	@Column(name = "precio") @JsonProperty(value = "precio") val precio: Int,
	@Column(name = "tipo") @JsonProperty(value = "tipo") val tipo: String
) {


}
