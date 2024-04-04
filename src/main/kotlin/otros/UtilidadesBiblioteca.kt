package otros

import java.util.UUID

class UtilidadesBiblioteca {
    companion object {

        private val uuidsGenerados = mutableSetOf<String>()

        fun generarIdentificadorUnico(): String {
            var uuid: String
            do {
                uuid = UUID.randomUUID().toString()
            } while (!uuidsGenerados.add(uuid))
            return uuid
        }
    }
}