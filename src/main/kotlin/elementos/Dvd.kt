package elementos

import otros.Estado
import interfaces.IPrestable

class Dvd(
    override val id: String,
    override val titulo: String,
    private val duracion: Int, // Duración en minutos
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
        return "DVD(id: $id, titulo: $titulo, duración: $duracion, estado: ${estado.desc})"
    }
}