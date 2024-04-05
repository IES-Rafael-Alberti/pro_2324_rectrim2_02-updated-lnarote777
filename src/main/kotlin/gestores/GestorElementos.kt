package gestores

import elementos.ElementoBiblioteca
import interfaces.IGestorElementos
import otros.Estado
import otros.UtilidadesBiblioteca

class GestorElementos<T: ElementoBiblioteca> : IGestorElementos<T> {
    override val elementos = mutableListOf<T>()

    override fun agregar(elemento: T): Boolean = elementos.add(elemento)

    override fun eliminar(id: String): Boolean = elementos.removeAll { it.id == id }

    override fun buscar(id: String): T? = elementos.find { it.id == id }

    override fun buscar(id: String, estado: Estado): T? {
        return filtrarPorCriterio { it.id == id && it.estado == estado }.firstOrNull()
    }

    override fun filtrarPorCriterio(criterio: (T) -> Boolean): List<T> = elementos.filter(criterio)

    override fun obtenerElementos(): List<T> = elementos.toList()

    override fun obtenerElementos(estado: Estado): List<T> {
        return filtrarPorCriterio { it.estado == estado }
    }

    override fun generarId(): String = UtilidadesBiblioteca.generarIdentificadorUnico()
}