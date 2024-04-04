package interfaces
interface IGestorConsola {

    fun mostrarInfo(mensaje: String = "", saltoLinea: Boolean = true)

    fun <T> mostrarInfo(objeto: T)

    fun pedirEntero(mensaje: String = "", min: Int, max: Int) : Int

    fun pedirEntero(mensaje: String = "") : Int

    fun pedirCadena(mensaje: String = "") : String

    fun limpiar(numLineas: Int = 20)

}