package gestores

import otros.Estado
import interfaces.IGestorBiblioteca
import interfaces.IGestorConsola

class GestorMenu(
    private val consola: IGestorConsola,
    private val biblioteca: IGestorBiblioteca
) {

    private fun mostrarMenuPpal(): Int {
        consola.mostrarInfo("MENÚ - Sistema de Gestión de Biblioteca")
        consola.mostrarInfo(" 1. Agregar libro") // Para ampliar correctamente pedir tipo de elemento
        consola.mostrarInfo(" 2. Eliminar elemento")
        consola.mostrarInfo(" 3. Registrar préstamo")
        consola.mostrarInfo(" 4. Devolver elemento")
        consola.mostrarInfo(" 5. Consultar disponibilidad")
        consola.mostrarInfo(" 6. Mostrar elementos del catálogo")
        consola.mostrarInfo(" 7. Mostrar elementos disponibles del catálogo")
        consola.mostrarInfo(" 8. Mostrar elementos prestados del catálogo")
        consola.mostrarInfo(" 9. Agregar otros.Usuario")
        consola.mostrarInfo("10. Elementos prestados a un usuario")
        consola.mostrarInfo("11. Mostrar historial préstamos de un elemento")
        consola.mostrarInfo("12. Mostrar historial préstamos de un usuario")
        consola.mostrarInfo("13. SALIR")
        return consola.pedirEntero("Elige una opción -> ", 1, 13)
    }

    private fun pausa() {
        consola.pedirCadena("\nPulse una tecla para continuar...")
    }

    fun menuPpal() {
        var opcion: Int

        do {
            opcion = mostrarMenuPpal()

            when (opcion) {
                1 -> crearLibro()
                2 -> biblioteca.eliminarElemento(pedirIdElemento())
                3 -> biblioteca.prestarElemento(pedirIdUsuario(), pedirIdElemento())
                4 -> biblioteca.devolverElemento(pedirIdUsuario(), pedirIdElemento())
                5 -> biblioteca.consultarDisponibilidadElemento(pedirIdElemento())
                6 -> mostrarInfo(biblioteca.elementosCatalogo())
                7 -> mostrarInfo(biblioteca.elementosCatalogo(Estado.DISPONIBLE))
                8 -> mostrarInfo(biblioteca.elementosCatalogo(Estado.PRESTADO))
                9 -> crearUsuario()
                10 -> mostrarInfo(biblioteca.elementosPrestadosUsuario(pedirIdUsuario()))
                11 -> mostrarUsuarios(biblioteca.historialPrestamosElemento(pedirIdElemento()))
                12 -> mostrarInfo(biblioteca.historialPrestamosUsuario(pedirIdUsuario()))
            }

            if (opcion != 13) {
                pausa()
                consola.limpiar()
            }

        } while (opcion != 13)

    }

    private fun crearLibro() {
        val titulo = consola.pedirCadena("\n* Introduzca el título: ")
        val autor = consola.pedirCadena("\n* Introduzca el autor: ")
        val anioPublicacion = consola.pedirEntero("\n* Introduzca el año de publicación: ")
        val tematica = consola.pedirCadena("\n* Introduzca la temática: ")
        biblioteca.agregarLibro(titulo, autor, anioPublicacion, tematica)
    }

    private fun pedirIdElemento() = consola.pedirCadena("\n* Introduzca el id del elemento: ")

    private fun pedirIdUsuario() = consola.pedirEntero("\n* Introduzca el id del usuario: ")

    private fun crearUsuario() {
        val nombre = consola.pedirCadena("\n* Introduzca el nombre: ")
        biblioteca.agregarUsuario(nombre)
    }

    private fun <T> mostrarInfo(info: List<T>, mensajeNoInfo: String = "No existen elementos.") {
        if (info.isEmpty()) {
            consola.mostrarInfo("\n$mensajeNoInfo")
        }
        else {
            consola.mostrarInfo() // Salto de línea
            info.forEach { consola.mostrarInfo(it) }
        }
    }

    private fun mostrarUsuarios(info: List<Int>) {
        if (info.isEmpty()) {
            consola.mostrarInfo("\nNo existen usuarios.")
        }
        else {
            consola.mostrarInfo("\nUsuarios a los que se ha prestado el libro:")
            info.forEach { usuarioId -> consola.mostrarInfo(biblioteca.buscarUsuario(usuarioId)) }
        }
    }

}