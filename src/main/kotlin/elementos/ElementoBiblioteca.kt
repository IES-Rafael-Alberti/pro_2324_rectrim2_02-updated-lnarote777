package elementos

import otros.Estado

abstract class ElementoBiblioteca(
    open val id: String,
    open val titulo: String,
    open val estado: Estado
)