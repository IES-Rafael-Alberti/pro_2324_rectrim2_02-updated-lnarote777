package interfaces

import otros.Usuario

interface IGestorUsuarios {

    val usuarios: MutableList<Usuario>
    var ultimaIdUsuario: Int

    fun generarId(): Int

    fun agregar(usuario: Usuario): Boolean

    fun crear(nombre: String): Usuario

    fun eliminar(id: Int): Boolean

    fun buscar(id: Int): Usuario?

}