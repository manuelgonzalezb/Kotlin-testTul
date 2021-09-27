package com.tul.shoppingcar.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import com.tul.shoppingcar.repository.CarritoRepository
import com.tul.shoppingcar.repository.CarritoEntity
import com.tul.shoppingcar.exceptions.ConflictException
import com.tul.shoppingcar.exceptions.NotFoundException
import com.tul.shoppingcar.repository.EstadosEnum

@Service
@Transactional
class ShoppingCarService{
	
	@Autowired
	lateinit var carritoRepository: CarritoRepository
	
	fun getAll(): Iterable<CarritoEntity>{
		return carritoRepository.findAll();
	}
	
	fun post(carrito: CarritoEntity): Int{
		if(carritoRepository.existsById(carrito.codigoCliente)){
			throw ConflictException(carrito.codigoCliente.toString());
		}
		val carritoEntity = carritoRepository.save(carrito);
		return carritoEntity.codigoCliente;
	}
	
	fun patch(carrito: CarritoEntity, codigoCliente: Int){
		if(!carritoRepository.existsById(codigoCliente)){
			throw NotFoundException(codigoCliente.toString());
		}
		var carritoEntity = carritoRepository.findById(codigoCliente).get();
		if(carrito.cantidad!=0){
			carritoEntity = carritoEntity.copy(cantidad=carrito.cantidad);
		}
		if(!carrito.estado.toString().isEmpty()){
			carritoEntity = carritoEntity.copy(estado=carrito.estado);
		}
		
		carritoRepository.save(carritoEntity);
	}
	
	fun delete(codigoCliente: Int){
		if(!carritoRepository.existsById(codigoCliente)){
			throw NotFoundException(codigoCliente.toString());
		}
		var carrito = CarritoEntity(codigoCliente,"",0,EstadosEnum.AGOTADO,0)
		carritoRepository.delete(carrito);
	}
	
}