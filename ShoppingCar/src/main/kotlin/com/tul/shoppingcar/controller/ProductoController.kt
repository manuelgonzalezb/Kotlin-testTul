package com.tul.shoppingcar.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import com.tul.shoppingcar.service.ProductoService
import org.springframework.web.bind.annotation.GetMapping
import com.tul.shoppingcar.entity.ProductoEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import com.tul.shoppingcar.exceptions.ConflictException
import java.net.URI
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import com.tul.shoppingcar.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.CrossOrigin

@RestController
@CrossOrigin
@RequestMapping("/producto")
class ProductoController {
	
	@Autowired
	lateinit var productoService: ProductoService;
	
	@GetMapping("/producto")
	fun getAll(): Iterable<ProductoEntity>
	{
		return productoService.getAll();		
	}
	
	@PostMapping("/")	
	fun insertar(@RequestBody producto:ProductoEntity): ResponseEntity<Any>
	{
		try{
			var productoRetorn = ProductoEntity(productoService.post(producto),"","","",0,"");
			return ResponseEntity
			.created( URI("/"+producto.uuid)).body(productoRetorn);
		}
		catch(ex: ConflictException){
			throw ConflictException(producto.uuid);
		}
		
	}
	
	@DeleteMapping ("/{uuid}")
	fun delete(@PathVariable uuid:String):ResponseEntity<Any>
	{
		try{
			productoService.delete(uuid);
			return ResponseEntity( HttpStatus.OK)
		}
		catch(ex: NotFoundException){
			throw NotFoundException(uuid);
		}
		
	}
	
	@PatchMapping ("/{uuid}")
	fun patch(@PathVariable uuid:String,@RequestBody producto:ProductoEntity):ResponseEntity<Any>
	{
		try{
			productoService.patch(producto,uuid);
			return ResponseEntity( HttpStatus.OK)
		}
		catch(ex: ConflictException){
			throw ConflictException(producto.uuid);
		}
	}
}