package creadores

import elementos.Dvd
import elementos.ElementoBiblioteca
import interfaces.ICreadorElemento
import interfaces.IGestorConsola
import interfaces.IGestorElementos

class CrearDvd(val consola: IGestorConsola, val gestorElementos: IGestorElementos<ElementoBiblioteca>): ICreadorElemento<ElementoBiblioteca> {
    override fun crear(): Dvd? {
        val titulo = consola.pedirCadena("\n* Introduzca el título: ")
        val duracion = consola.pedirEntero("\n* Introduzca la duración (en minutos): ")

        return Dvd(gestorElementos.generarId(), titulo, duracion)
    }
}