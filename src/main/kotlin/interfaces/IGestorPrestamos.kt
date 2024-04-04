package interfaces

import elementos.ElementoBiblioteca

interface IGestorPrestamos {

    fun registrarPrestamo(usuarioId: Int, elemento: ElementoBiblioteca)

    fun devolverElemento(usuarioId: Int, elemento: ElementoBiblioteca)

    fun historialPrestamosUsuario(usuarioId: Int): List<String>

    fun historialPrestamosElemento(elementoId: String): List<Int>

}
