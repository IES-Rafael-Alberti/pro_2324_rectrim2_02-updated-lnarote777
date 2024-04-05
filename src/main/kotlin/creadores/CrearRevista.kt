package creadores

import elementos.ElementoBiblioteca
import elementos.Revista
import interfaces.ICreadorElemento
import interfaces.IGestorConsola
import interfaces.IGestorElementos

class CrearRevista(private val consola: IGestorConsola, private val gestorElementos: IGestorElementos<ElementoBiblioteca>): ICreadorElemento<ElementoBiblioteca> {
    override fun crear(): Revista? {
        val titulo = consola.pedirCadena("\n* Introduzca el título: ")
        if (titulo.isBlank()){
            consola.mostrarInfo("***ERROR - Este campo no puede estar vacío***")
            return null
        }

        val edicion = consola.pedirCadena("\n* Introduzca la edición: ")
        if (edicion.isBlank()){
            consola.mostrarInfo("***ERROR - Este campo no puede estar vacío***")
            return null
        }

        return Revista(gestorElementos.generarId(), titulo, edicion)
    }
}