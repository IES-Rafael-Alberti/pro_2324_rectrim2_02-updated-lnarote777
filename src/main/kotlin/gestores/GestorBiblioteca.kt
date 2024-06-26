package gestores

import otros.Estado
import otros.Usuario
import elementos.ElementoBiblioteca
import elementos.Libro
import interfaces.*

class GestorBiblioteca(
    private val consola: IGestorConsola,
    private val gestorElementos: IGestorElementos<ElementoBiblioteca>,
    private val usuarios: IGestorUsuarios,
    private val prestamos: IGestorPrestamos
) : IGestorBiblioteca {

    override fun agregarElemento(elemento: ElementoBiblioteca) {
        if (gestorElementos.agregar(elemento)) {
            consola.mostrarInfo("\nElemento ${elemento.id} ha sido agregado.")
        }
    }

    override fun agregarElemento(creador: ICreadorElemento<ElementoBiblioteca>){
        val elemento = creador.crear()
        if (elemento != null){
            agregarElemento(elemento)
        }else{
            consola.mostrarInfo("***No se pudo agregar el elemento**")
        }
    }

    override fun agregarUsuario(usuario: Usuario) {
        usuarios.agregar(usuario)
    }

    override fun agregarUsuario(nombre: String) {
        try {
            val usuario = usuarios.crear(nombre)
            usuarios.agregar(usuario)
        }
        catch (e: IllegalArgumentException) {
            consola.mostrarInfo("\n**Error** ${e.message}.")
        }
    }

    override fun buscarUsuario(id: Int): Usuario? {
        return usuarios.buscar(id)
    }

    override fun eliminarElemento(id: String) {
        if (gestorElementos.eliminar(id)) {
            consola.mostrarInfo("\nElemento $id ha sido eliminado.")
        }
        else {
            consola.mostrarInfo("\n**Error** Elemento $id no encontrado en el catálogo.")
        }
    }

    override fun prestarElemento(usuarioId: Int, elementoId: String) {
        val usuario = usuarios.buscar(usuarioId)
        val elemento = gestorElementos.buscar(elementoId, Estado.DISPONIBLE)
        if (usuario != null && elemento != null && elemento is IPrestable) {
            usuario.agregarElementoPrestado(elemento)
            elemento.prestar()
            prestamos.registrarPrestamo(usuarioId, elemento)
            consola.mostrarInfo("\nElemento $elementoId ha sido prestado al usuario $usuarioId.")
        }
        else {
            if (usuario == null) {
                consola.mostrarInfo("\n**Error** otros.Usuario $usuarioId no encontrado en el sistema.")
            }
            else if (elemento == null) {
                consola.mostrarInfo("\n**Error** Elemento $elementoId no encontrado en el catálogo o prestado.")
            }
            else {
                consola.mostrarInfo("\n**Error** Elemento $elementoId no prestable.")
            }
        }
    }

    override fun devolverElemento(usuarioId: Int, elementoId: String) {
        val usuario = usuarios.buscar(usuarioId)
        val elemento = gestorElementos.buscar(elementoId, Estado.PRESTADO)
        if (usuario != null && elemento != null && elemento is IPrestable) {
            usuario.quitarElementoPrestado(elemento)
            elemento.devolver()
            prestamos.devolverElemento(usuarioId, elemento)
            consola.mostrarInfo("\nElemento $elementoId ha sido devuelto por el usuario $usuarioId.")
        }
        else {
            if (usuario == null) {
                consola.mostrarInfo("\n**Error** otros.Usuario $usuarioId no encontrado en el sistema.")
            }
            else if (elemento == null) {
                consola.mostrarInfo("\n**Error** Elemento $elementoId no encontrado en el catálogo o ya disponible.")
            }
            else {
                consola.mostrarInfo("\n**Error** Elemento $elementoId no prestable.")
            }
        }
    }

    override fun consultarDisponibilidadElemento(id: String) {
        val elemento = gestorElementos.buscar(id)
        if (elemento == null) {
            consola.mostrarInfo("\n**Error** Elemento $id no encontrado en el catálogo.")
        }
        else {
            consola.mostrarInfo("\nElemento $id está ${elemento.estado.desc}.")
        }
    }

    override fun elementosCatalogo(estado: Estado): List<ElementoBiblioteca> {
        return gestorElementos.obtenerElementos(estado)
    }

    override fun elementosCatalogo(): List<ElementoBiblioteca> {
        return gestorElementos.obtenerElementos()
    }

    override fun elementosPrestadosUsuario(usuarioId: Int) : List<ElementoBiblioteca> {
        val usuario = usuarios.buscar(usuarioId)
        return if (usuario != null) {
            usuario.obtenerElementosPrestados()
        } else {
            consola.mostrarInfo("\notros.Usuario $usuarioId no encontrado en el sistema.")
            emptyList()
        }
    }

    override fun historialPrestamosElemento(elementoId: String): List<Int> {
        return prestamos.historialPrestamosElemento(elementoId)
    }

    override fun historialPrestamosUsuario(usuarioId: Int): List<String> {
        return prestamos.historialPrestamosUsuario(usuarioId)
    }
}