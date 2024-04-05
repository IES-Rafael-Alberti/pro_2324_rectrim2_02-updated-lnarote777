package creadores

import elementos.ElementoBiblioteca
import elementos.Libro
import interfaces.ICreadorElemento
import interfaces.IGestorConsola
import interfaces.IGestorElementos

class CrearLibro(val consola: IGestorConsola, val gestorElementos: IGestorElementos<ElementoBiblioteca>): ICreadorElemento<ElementoBiblioteca> {
    override fun crear(): Libro? {
        val titulo = consola.pedirCadena("\n* Introduzca el título: ")
        val autor = consola.pedirCadena("\n* Introduzca el autor: ")
        val anioPublicacion = consola.pedirEntero("\n* Introduzca el año de publicación: ")
        val tematica = consola.pedirCadena("\n* Introduzca la temática: ")
        return Libro(gestorElementos.generarId(), titulo, autor, anioPublicacion, tematica)
    }
}