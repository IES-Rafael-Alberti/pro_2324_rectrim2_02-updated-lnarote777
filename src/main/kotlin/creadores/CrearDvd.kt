package creadores

import elementos.Dvd
import elementos.ElementoBiblioteca
import interfaces.ICreadorElemento
import interfaces.IGestorConsola
import interfaces.IGestorElementos

class CrearDvd(private val consola: IGestorConsola, private val gestorElementos: IGestorElementos<ElementoBiblioteca>): ICreadorElemento<ElementoBiblioteca> {
    override fun crear(): Dvd? {

        val titulo = consola.pedirCadena("\n* Introduzca el título: ")
        if (titulo.isBlank()){
            consola.mostrarInfo("***ERROR - Este campo no puede estar vacío***")
            return null
        }

        val duracion = consola.pedirEntero("\n* Introduzca la duración (en minutos): ")
        if (duracion <= 0){
            consola.mostrarInfo("***ERROR - La duración no puede ser negativa ni 0***")
            return null
        }

        return Dvd(gestorElementos.generarId(), titulo, duracion)
    }
}