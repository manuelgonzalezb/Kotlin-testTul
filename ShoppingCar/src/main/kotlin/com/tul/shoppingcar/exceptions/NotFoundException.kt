package com.tul.shoppingcar.exceptions

import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException: RuntimeException {

	constructor(codigo: String?): super("No se encontro registro "+codigo);
}