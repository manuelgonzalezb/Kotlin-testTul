package com.tul.shoppingcar.ShoppingCar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.EnableAutoConfiguration

@SpringBootApplication
class ShoppingCarApplication

fun main(args: Array<String>) {
	runApplication<ShoppingCarApplication>(*args)
}
