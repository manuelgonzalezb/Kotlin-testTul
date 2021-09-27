package com.tul.shoppingcar.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface CarritoRepository : JpaRepository<CarritoEntity,Int> {
}