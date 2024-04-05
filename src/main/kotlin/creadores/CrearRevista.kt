package creadores

import elementos.ElementoBiblioteca
import elementos.Revista
import interfaces.ICreadorElemento
import interfaces.IGestorConsola
import interfaces.IGestorElementos

class CrearRevista(val consola: IGestorConsola, val gestorElementos: IGestorElementos<ElementoBiblioteca>): ICreadorElemento<ElementoBiblioteca> {
    override fun crear(): Revista? {
        val titulo = consola.pedirCadena("\n* Introduzca el título: ")
        val edicion = consola.pedirCadena("\n* Introduzca la edición: ")

        return Revista(gestorElementos.generarId(), titulo, edicion)
    }
}