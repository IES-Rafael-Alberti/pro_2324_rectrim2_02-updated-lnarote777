package interfaces

import otros.Estado
import elementos.ElementoBiblioteca

interface IGestorCatalogo {

    fun generarId(): String

    fun agregar(elemento: ElementoBiblioteca): Boolean

    fun eliminar(id: String): Boolean

    fun buscar(id: String): ElementoBiblioteca?

    fun buscar(id: String, estado: Estado): ElementoBiblioteca?

    fun obtenerElementos() : List<ElementoBiblioteca>

    fun obtenerElementos(estado: Estado) : List<ElementoBiblioteca>

}