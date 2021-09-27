package com.tul.shoppingcar.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import com.tul.shoppingcar.service.ShoppingCarService
import org.springframework.web.bind.annotation.GetMapping
import com.tul.shoppingcar.repository.CarritoEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import java.net.URI
import com.tul.shoppingcar.exceptions.ConflictException
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.HttpStatus
import com.tul.shoppingcar.exceptions.NotFoundException
import org.springframework.web.bind.annotation.PatchMapping
import com.tul.shoppingcar.repository.EstadosEnum

@RestController
class ShoppingCar{
	@Autowired
	lateinit var shoppingCarService: ShoppingCarService;
	
	@GetMapping("/shoppingcar")
	fun getAll(): Iterable<CarritoEntity>
	{
		return shoppingCarService.getAll();		
	}
	
	@PostMapping("/shoppingcar")	
	fun insertar(@RequestBody carrito:CarritoEntity): ResponseEntity<Any>
	{
		try{
			var carritoRetorn = CarritoEntity(shoppingCarService.post(carrito),"",0,EstadosEnum.DISPONIBLE,0);
			return ResponseEntity
			.created( URI("/"+carrito.codigoCliente)).body(carritoRetorn);
		}
		catch(ex: ConflictException){
			throw ConflictException(carrito.codigoCliente.toString());
		}
		
	}
	
	@DeleteMapping ("/{codigoCliente}")
	fun delete(@PathVariable codigoCliente:Int):ResponseEntity<Any>
	{
		try{
			shoppingCarService.delete(codigoCliente);
			return ResponseEntity( HttpStatus.OK)
		}
		catch(ex: NotFoundException){
			throw NotFoundException(codigoCliente.toString());
		}
		
	}
	
	@PatchMapping ("/{codigoCliente}")
	fun patch(@PathVariable codigoCliente:Int,@RequestBody carrito:CarritoEntity):ResponseEntity<Any>
	{
		try{
			shoppingCarService.patch(carrito,codigoCliente);
			return ResponseEntity( HttpStatus.OK)
		}
		catch(ex: ConflictException){
			throw ConflictException(codigoCliente.toString());
		}
	}
}