package creadores

import elementos.ElementoBiblioteca
import elementos.Libro
import interfaces.ICreadorElemento
import interfaces.IGestorConsola
import interfaces.IGestorElementos

class CrearLibro(private val consola: IGestorConsola, private val gestorElementos: IGestorElementos<ElementoBiblioteca>): ICreadorElemento<ElementoBiblioteca> {
    override fun crear(): Libro? {
        val titulo = consola.pedirCadena("\n* Introduzca el título: ")
        if (titulo.isBlank()){
            consola.mostrarInfo("***ERROR - Este campo no puede estar vacío***")
            return null
        }

        val autor = consola.pedirCadena("\n* Introduzca el autor: ")
        if (autor.isBlank()){
            consola.mostrarInfo("***ERROR - Este campo no puede estar vacío***")
            return null
        }

        val anioPublicacion = consola.pedirEntero("\n* Introduzca el año de publicación: ")
        if (anioPublicacion < 0){
            consola.mostrarInfo("***ERROR - El año de publicación no puede ser negativo***")
            return null
        }

        val tematica = consola.pedirCadena("\n* Introduzca la temática: ")
        if (tematica.isBlank()){
            consola.mostrarInfo("***ERROR - Este campo no puede estar vacío***")
            return null
        }

        return Libro(gestorElementos.generarId(), titulo, autor, anioPublicacion, tematica)
    }
}