package interfaces
interface IGestorElementos<T> {

    val elementos: MutableList<T>

    fun agregar(elemento: T): Boolean

    fun eliminar(id: String): Boolean

    fun buscar(id: String): T?

    fun obtenerElementos(): List<T>

    fun filtrarPorCriterio(criterio: (T) -> Boolean): List<T>

}