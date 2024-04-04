package otros

import elementos.ElementoBiblioteca

class Usuario(
    private val id: Int,
    private val nombre: String
) {
    private val elementosPrestados = mutableListOf<ElementoBiblioteca>()

    init {
        require(nombre.isNotBlank()) { "El nombre del usuario no puede ser vacío" }
    }

    fun getId() = id

    fun agregarElementoPrestado(elemento: ElementoBiblioteca) {
        elementosPrestados.add(elemento)
    }

    fun quitarElementoPrestado(elemento: ElementoBiblioteca) {
        elementosPrestados.remove(elemento)
    }

    fun obtenerElementosPrestados(): List<ElementoBiblioteca> {
        return elementosPrestados.toList()
    }

    override fun toString(): String {
        return "otros.Usuario(id: $id, nombre: $nombre)"
    }

}