package gestores

import elementos.ElementoBiblioteca
import interfaces.IGestorPrestamos

class GestorPrestamos : IGestorPrestamos {
    private val prestamosActuales = mutableMapOf<Int, ElementoBiblioteca>() // Map<UsuarioID, elementos.Libro>
    private val historialPrestamos = mutableListOf<Pair<Int, String>>() // Pair<UsuarioID, LibroID>

    override fun registrarPrestamo(usuarioId: Int, elemento: ElementoBiblioteca) {
        prestamosActuales[usuarioId] = elemento
        historialPrestamos.add(Pair(usuarioId, elemento.id))
    }

    override fun devolverElemento(usuarioId: Int, elemento: ElementoBiblioteca) {
        prestamosActuales.remove(usuarioId, elemento)
    }

    override fun historialPrestamosUsuario(usuarioId: Int): List<String> {
        return historialPrestamos.filter { it.first == usuarioId }.map { it.second }
    }

    override fun historialPrestamosElemento(elementoId: String): List<Int> {
        return historialPrestamos.filter { it.second == elementoId }.map { it.first }
    }
}