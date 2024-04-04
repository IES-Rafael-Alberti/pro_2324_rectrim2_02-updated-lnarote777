package interfaces

import otros.Estado
import otros.Usuario
import elementos.ElementoBiblioteca

interface IGestorBiblioteca {

    fun agregarElemento(elemento: ElementoBiblioteca)

    fun agregarLibro(titulo: String, autor: String, anioPublicacion: Int, tematica: String)

    fun agregarUsuario(usuario: Usuario)

    fun agregarUsuario(nombre: String)

    fun buscarUsuario(id: Int): Usuario?

    fun eliminarElemento(id: String)

    fun prestarElemento(usuarioId: Int, elementoId: String)

    fun devolverElemento(usuarioId: Int, elementoId: String)

    fun consultarDisponibilidadElemento(id: String)

    fun elementosCatalogo(estado: Estado): List<ElementoBiblioteca>

    fun elementosCatalogo(): List<ElementoBiblioteca>

    fun elementosPrestadosUsuario(usuarioId: Int) : List<ElementoBiblioteca>

    fun historialPrestamosElemento(libroId: String): List<Int>

    fun historialPrestamosUsuario(usuarioId: Int): List<String>

}