package gestores

import otros.Estado
import otros.UtilidadesBiblioteca
import elementos.ElementoBiblioteca
import interfaces.IGestorCatalogo
import interfaces.IGestorElementos

class GestorCatalogo(private val gestorElementos: IGestorElementos<ElementoBiblioteca>) : IGestorCatalogo {

    override fun generarId() = UtilidadesBiblioteca.generarIdentificadorUnico()

    override fun agregar(elemento: ElementoBiblioteca): Boolean {
        return gestorElementos.agregar(elemento)
    }

    override fun eliminar(id: String): Boolean {
        return gestorElementos.eliminar(id)
    }

    override fun buscar(id: String): ElementoBiblioteca? {
        return gestorElementos.buscar(id)
    }

    override fun buscar(id: String, estado: Estado): ElementoBiblioteca? {
        return gestorElementos.filtrarPorCriterio { it.id == id && it.estado == estado }.firstOrNull()
    }

    override fun obtenerElementos() : List<ElementoBiblioteca> {
        return gestorElementos.obtenerElementos()
    }

    override fun obtenerElementos(estado: Estado) : List<ElementoBiblioteca> {
        return gestorElementos.filtrarPorCriterio { it.estado == estado }
    }

}