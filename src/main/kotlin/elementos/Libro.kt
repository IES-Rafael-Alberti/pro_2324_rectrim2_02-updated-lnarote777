package elementos

import otros.Estado
import interfaces.IPrestable

class Libro(
    override val id: String,
    override val titulo: String,
    private val autor: String,
    private val anioPublicacion: Int,
    private val tematica: String,
    override var estado: Estado = Estado.DISPONIBLE
) : ElementoBiblioteca(id, titulo, estado), IPrestable {

    init {
        require(titulo.isNotBlank()) { "El título no puede estar vacío" }
        require(autor.isNotBlank()) { "El autor no puede estar vacío" }
        require(anioPublicacion in 1..2024) { "El año de publicación debe ser un valor entre 1 y 2024" }
        require(tematica.isNotBlank()) { "La temática no puede estar vacía" }
        require(estado == Estado.DISPONIBLE || estado == Estado.PRESTADO) { "El estado debe ser válido" }
    }

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
        return "elementos.Libro(id: $id, titulo: $titulo, autor: $autor, año de publicación: $anioPublicacion, temática: $tematica, estado: ${estado.desc})"
    }
}