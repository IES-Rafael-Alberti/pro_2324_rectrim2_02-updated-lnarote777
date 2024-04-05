package creadores

import elementos.Ebook
import elementos.ElementoBiblioteca
import interfaces.ICreadorElemento
import interfaces.IGestorConsola
import interfaces.IGestorElementos

class CrearEbook(private val consola: IGestorConsola, private val gestorElementos: IGestorElementos<ElementoBiblioteca>): ICreadorElemento<ElementoBiblioteca> {
    override fun crear(): Ebook? {
        val titulo = consola.pedirCadena("\n* Introduzca el título: ")
        val tamanio = consola.pedirCadena("\n* Introduzca el tamaño en pulgadas: ").toDouble()

        return Ebook(gestorElementos.generarId(), titulo, tamanio)
    }
}