package gestores

import creadores.CrearDvd
import creadores.CrearEbook
import creadores.CrearLibro
import creadores.CrearRevista
import elementos.ElementoBiblioteca
import otros.Estado
import interfaces.IGestorBiblioteca
import interfaces.IGestorConsola
import interfaces.IGestorElementos

class GestorMenu(
    private val consola: IGestorConsola,
    private val biblioteca: IGestorBiblioteca,
    private val gestorElementos: IGestorElementos<ElementoBiblioteca>
) {

    private fun mostrarMenuPpal(): Int {
        consola.mostrarInfo("MENÚ - Sistema de Gestión de Biblioteca")
        consola.mostrarInfo(" 1. Agregar elemento") // Para ampliar correctamente pedir tipo de elemento
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
                1 -> subMenu()
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

    private fun mostrarSubMenu(): Int {
        consola.mostrarInfo("MENÚ - Agregar Elemento")
        consola.mostrarInfo("1. Libro")
        consola.mostrarInfo("2. Revista")
        consola.mostrarInfo("3. DVD")
        consola.mostrarInfo("4. Ebook")
        consola.mostrarInfo("5. VOLVER")
        return consola.pedirEntero("Elige una opción -> ", 1, 5)
    }

    private fun subMenu(){
        var opcion: Int
        do {
            opcion = mostrarSubMenu()

            when (opcion){
                1 -> biblioteca.agregarElemento(CrearLibro(consola, gestorElementos))
                2 -> biblioteca.agregarElemento(CrearRevista(consola, gestorElementos))
                3 -> biblioteca.agregarElemento(CrearDvd(consola, gestorElementos))
                4 -> biblioteca.agregarElemento(CrearEbook(consola, gestorElementos) )
            }

            if (opcion != 5) {
                pausa()
                consola.limpiar()
            }
        }while (opcion != 5)
    }

}