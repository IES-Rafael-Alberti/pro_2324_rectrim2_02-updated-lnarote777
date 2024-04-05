package interfaces

import otros.Estado

interface IGestorElementos<T> {

    val elementos: MutableList<T>

    fun agregar(elemento: T): Boolean

    fun eliminar(id: String): Boolean

    fun buscar(id: String): T?

    fun buscar(id: String, estado: Estado): T?

    fun obtenerElementos(): List<T>

    fun obtenerElementos(estado: Estado) : List<T>

    fun filtrarPorCriterio(criterio: (T) -> Boolean): List<T>

    fun generarId(): String

}