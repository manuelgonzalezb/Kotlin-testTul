package com.tul.shoppingcar.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.tul.shoppingcar.repository.ProductoRepository
import org.springframework.beans.factory.annotation.Autowired
import com.tul.shoppingcar.entity.ProductoEntity
import com.tul.shoppingcar.exceptions.ConflictException
import com.tul.shoppingcar.exceptions.NotFoundException

@Service
@Transactional
class ProductoService{
	
	@Autowired
	lateinit var productoRepository: ProductoRepository
	
	fun getAll(): Iterable<ProductoEntity>{
		return productoRepository.findAll();
	}
	
	fun post(producto: ProductoEntity): String{
		if(productoRepository.existsById(producto.uuid)){
			throw ConflictException(producto.uuid);
		}
		val productoEntity = productoRepository.save(producto);
		return productoEntity.uuid;
	}
	
	fun patch(producto: ProductoEntity, uuid: String){
		if(!productoRepository.existsById(producto.uuid)){
			throw NotFoundException(producto.uuid);
		}
		var productoEntity = productoRepository.findById(uuid).get();
		if(!producto.nombre.isEmpty()){
			productoEntity = productoEntity.copy(nombre=producto.nombre);
		}
		if(!producto.descripcion.isEmpty()){
			productoEntity = productoEntity.copy(descripcion=producto.descripcion);
		}
		if(!producto.sku.isEmpty()){
			productoEntity = productoEntity.copy(sku=producto.sku);
		}
		
		productoRepository.save(productoEntity);
	}
	
	fun delete(uuid: String){
		if(!productoRepository.existsById(uuid)){
			throw NotFoundException(uuid);
		}
		var producto = ProductoEntity(uuid,"","","",0,"")
		productoRepository.delete(producto);
	}
	
}