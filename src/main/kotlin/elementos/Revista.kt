package elementos

import otros.Estado
import interfaces.IPrestable

class Revista(
    override val id: String,
    override val titulo: String,
    private val edicion: String,
    override var estado: Estado = Estado.DISPONIBLE
) : ElementoBiblioteca(id, titulo, estado), IPrestable {
    override fun prestar() {
        if (estado == Estado.DISPONIBLE) {
            estado = Estado.PRESTADO
        }
    }

    override fun devolver() {
        if (estado == Estado.PRESTADO) {
            estado = Estado.DISPONIBLE
        }
    }

    override fun toString(): String {
        return "elementos.Revista(id: $id, titulo: $titulo, edición: $edicion, estado: ${estado.desc})"
    }
}