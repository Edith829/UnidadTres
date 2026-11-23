package com.example.nav3.data

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val category: String
)

val sampleProducts = listOf(
    Product(
        id = 1,
        name = "Laptop Pro 16\"",
        price = "$1,299.99",
        description = "Procesador de alto rendimiento, 16GB RAM, SSD 512GB.",
        category = "Electrónica"
    ),
    Product(
        id = 2,
        name = "Auriculares Inalámbricos",
        price = "$149.50",
        description = "Cancelación activa de ruido y autonomía de hasta 30 horas.",
        category = "Audio"
    ),
    Product(
        id = 3,
        name = "Smartwatch Sport",
        price = "$199.00",
        description = "Monitoreo continuo de ritmo cardíaco, GPS y resistencia al agua.",
        category = "Gadgets"
    ),
    Product(
        id = 4,
        name = "Teclado Mecánico RGB",
        price = "$89.99",
        description = "Switches táctiles silenciosos con iluminación RGB personalizable.",
        category = "Accesorios"
    ),
    Product(
        id = 5,
        name = "Monitor 4K 27\"",
        price = "$349.99",
        description = "Panel IPS con precisión de color profesional y tasa de 144Hz.",
        category = "Electrónica"
    )
)
