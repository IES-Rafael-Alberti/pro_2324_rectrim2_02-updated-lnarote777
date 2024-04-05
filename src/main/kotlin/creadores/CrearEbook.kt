package creadores

import elementos.Ebook
import elementos.ElementoBiblioteca
import interfaces.ICreadorElemento
import interfaces.IGestorConsola
import interfaces.IGestorElementos

class CrearEbook(private val consola: IGestorConsola, private val gestorElementos: IGestorElementos<ElementoBiblioteca>): ICreadorElemento<ElementoBiblioteca> {
    override fun crear(): Ebook? {
        val titulo = consola.pedirCadena("\n* Introduzca el título: ")
        if (titulo.isBlank()){
            consola.mostrarInfo("***ERROR - Este campo no puede estar vacío***")
            return null
        }

        val tamanio = consola.pedirCadena("\n* Introduzca el tamaño en pulgadas: ").toDouble()
        if (tamanio <= 0){
            consola.mostrarInfo("***ERROR - El tamaño no puede ser negativo ni 0***")
            return null
        }

        return Ebook(gestorElementos.generarId(), titulo, tamanio)
    }
}