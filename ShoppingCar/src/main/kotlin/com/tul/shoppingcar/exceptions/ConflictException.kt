package com.tul.shoppingcar.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class ConflictException: RuntimeException
{
	constructor(codigo: String?): super("Codigo de producto ya existe "+codigo); 
} 