package gestores

import otros.Usuario
import interfaces.IGestorUsuarios

class GestorUsuarios : IGestorUsuarios {
    override val usuarios = mutableListOf<Usuario>()
    override var ultimaIdUsuario = 1

    override fun generarId() = ultimaIdUsuario++

    override fun agregar(usuario: Usuario): Boolean {
        return if (usuario !in usuarios) {
            usuarios.add(usuario)
        } else {
            false
        }
    }

    override fun crear(nombre: String): Usuario {
        return Usuario(
            id = generarId(),
            nombre = nombre
        )
    }

    override fun eliminar(id: Int): Boolean {
        val usuario = buscar(id)
        return if (usuario != null) {
            usuarios.remove(usuario)
        } else {
            false
        }
    }

    override fun buscar(id: Int): Usuario? {
        return usuarios.find { usuario -> usuario.getId() == id }
    }
}