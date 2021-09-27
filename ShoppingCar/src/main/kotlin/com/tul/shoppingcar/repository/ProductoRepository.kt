package com.tul.shoppingcar.repository

import org.springframework.data.repository.CrudRepository
import com.tul.shoppingcar.entity.ProductoEntity
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface ProductoRepository : JpaRepository<ProductoEntity,String> {
}