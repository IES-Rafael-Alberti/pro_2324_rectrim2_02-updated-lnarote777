package elementos

import otros.Estado

class Ebook(
    override val id: String,
    override val titulo: String,
    private val tamanio: Double, // Tamaño en Pulgadas
    override var estado: Estado = Estado.DISPONIBLE
) : ElementoBiblioteca(id, titulo, estado) {

    override fun toString(): String {
        return "elementos.Ebook(id: $id, titulo: $titulo, tamaño: $tamanio, estado: ${estado.desc})"
    }
}