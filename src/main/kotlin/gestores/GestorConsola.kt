package gestores

import interfaces.IGestorConsola

class GestorConsola : IGestorConsola {

    override fun mostrarInfo(mensaje: String, saltoLinea: Boolean) {
        if (saltoLinea) println(mensaje) else print(mensaje)
    }

    override fun <T> mostrarInfo(objeto: T) {
        println(objeto)
    }

    override fun pedirEntero(mensaje: String, min: Int, max: Int) : Int {
        var numero: Int? = null

        do {
            if (mensaje.isNotBlank()) mostrarInfo(mensaje, false)
            try {
                numero = readln().toInt()
                require(numero in min..max) { "El número debe estar entre $min y $max." }
            }
            catch (e: NumberFormatException) {
                mostrarInfo("**Error** El formato del número es incorrecto.")
            }
            catch (e: IllegalArgumentException) {
                mostrarInfo("**Error** ${e.message}")
            }
        } while (numero == null)

        return numero
    }

    override fun pedirEntero(mensaje: String) : Int {
        var numero: Int? = null

        do {
            if (mensaje.isNotBlank()) mostrarInfo(mensaje, false)
            try {
                numero = readln().toInt()
            }
            catch (e: NumberFormatException) {
                mostrarInfo("**Error** El formato del número es incorrecto.")
            }
        } while (numero == null)

        return numero
    }

    override fun pedirCadena(mensaje: String) : String {
        if (mensaje.isNotBlank()) mostrarInfo(mensaje, false)
        return readln()
    }

    override fun limpiar(numLineas: Int) {
        (1..numLineas).forEach { _ -> mostrarInfo("") }
    }

}