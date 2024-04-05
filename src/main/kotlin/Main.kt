import elementos.Dvd
import elementos.Ebook
import elementos.ElementoBiblioteca
import elementos.Libro
import gestores.*

fun main() {
    val consola = GestorConsola()

    val gestorElementos = GestorElementos<ElementoBiblioteca>()
    val usuarios = GestorUsuarios()

    val gestorPrestamos = GestorPrestamos()

    val biblioteca = GestorBiblioteca(consola, gestorElementos, usuarios, gestorPrestamos)
    val menu = GestorMenu(consola, biblioteca)

    // Añadir dos usuarios para pruebas...
    biblioteca.agregarUsuario("Julia")
    biblioteca.agregarUsuario("Marcos")

    // Añadir algunos elementos al catálogo para pruebas...
    biblioteca.agregarElemento(Libro(gestorElementos.generarId(), "El Hobbit", "J.R.R. Tolkien", 1937, "Fantasía"))
    biblioteca.agregarElemento(Libro(gestorElementos.generarId(), "1984", "George Orwell", 1949, "Ciencia Ficción"))
    biblioteca.agregarElemento(Libro(gestorElementos.generarId(), "Cien años de soledad", "Gabriel García Márquez", 1967, "Realismo Mágico"))

    // El elementos.Ebook no es prestable... debería dar error al intentar registrar el préstamo...
    biblioteca.agregarElemento(Ebook(gestorElementos.generarId(), "Kotlin no se presta", 7.0))

    biblioteca.agregarElemento(Dvd(gestorElementos.generarId(), "Seis días y siete noches", 127))

    consola.limpiar()

    // Implementación de un simple menú de usuario...
    menu.menuPpal()
}