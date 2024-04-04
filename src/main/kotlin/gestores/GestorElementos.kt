package gestores

import elementos.ElementoBiblioteca
import interfaces.IGestorElementos

class GestorElementos<T: ElementoBiblioteca> : IGestorElementos<T> {
    override val elementos = mutableListOf<T>()

    override fun agregar(elemento: T): Boolean = elementos.add(elemento)

    override fun eliminar(id: String): Boolean = elementos.removeAll { it.id == id }

    override fun buscar(id: String): T? = elementos.find { it.id == id }

    override fun filtrarPorCriterio(criterio: (T) -> Boolean): List<T> = elementos.filter(criterio)

    override fun obtenerElementos(): List<T> = elementos.toList()
}