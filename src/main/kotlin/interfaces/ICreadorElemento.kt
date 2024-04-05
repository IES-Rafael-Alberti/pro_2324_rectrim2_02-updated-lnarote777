package interfaces

import elementos.ElementoBiblioteca

interface ICreadorElemento<T: ElementoBiblioteca> {
    fun crear(): T?
}