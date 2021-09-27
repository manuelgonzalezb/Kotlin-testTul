package com.tul.shoppingcar.repository

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Enumerated
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@Entity
@Data
@Table(name = "carrito")
data class CarritoEntity(
	@Column(name = "codigo_cliente") @JsonProperty(value = "codigoCliente") val codigoCliente: Int,
	@Column(name = "codigo_producto") @JsonProperty(value = "codigoProducto") val codigoProducto: String,
	@Column(name = "cantidad") @JsonProperty(value = "cantidad") val cantidad: Int,
	@Column(name = "estado") @JsonProperty(value = "estado") val estado: EstadosEnum,
	@Column(name = "precio_neto") @JsonProperty(value = "precioNeto") val precioNeto: Int,
	) {


}
