# Recuperación - Ejercicio 2.

Se pide modificar el código existente del Sistema de Biblioteca para que sea capaz de ```agregar``` distintos elementos de tipo ```ElementoBiblioteca``` solo modificando las llamadas del submenú 
"Agregar Elemento" que se va a mostrar en el menú de la aplicación después de los cambios que debéis realizar en el proyecto.

El resto de clases deben ser totalmente independientes y no deben verse afectadas por posibles incorporaciones de nuevos tipos de elemento. De esta forma haremos cumplir los principios SOLID que 
hemos estado viendo hasta ahora. Con esta modificación de nuestro código, si se incorpora un nuevo elemento a la biblioteca solo tendremos que modificar la clase ```GestorMenu```, agregando la opción 
al submenú y la llamada al método correspondiente.

Para ello, se desarrollarán clases específicas para cada tipo de elemento (Libro, Revista, ...), cuyas instancias se pasarán cómo argumento a un nuevo método ```agregarElemento()``` que debéis implementar
en ```GestorBiblioteca``` *(sin eliminar el ya existente, sino haciendo una sobrecarga)*... es decir, deberéis pasar a tener dos métodos agregarElemento:

    - agregarElemento(elemento: ElementoBiblioteca)
    - agregarElemento(creadorElemento: ICreadorElemento<ElementoBiblioteca>)

## Detalles de los cambios y nuevos desarrollos que debéis realizar:

### 1. Sustituir GestorCatalogo por GestorElementos.

Esta modificación va a requerir cambios en ```GestorElementos```, ```GestorBiblioteca``` y sus ```interfaces```. Se trata de eliminar GestorCatalogo y la interfaz IGestorCatalogo, para que GestorBiblioteca 
delegue la responsabilidad de la gestión de los elementos del catálogo de la biblioteca a GestorElementos, o mejor dicho, a una abstracción que implemente el contrato IGestorElementos.

### 2. Eliminar del proyecto GestorCatalogo.

Una vez realizada la sustitución por completo, debéis eliminar ```GestorCatalogo``` y el interface ```IGestorcatalogo``` y comprobar que todo sigue funcionando correctamente.

### 3. Submenú AGREGAR ELEMENTO.

Modificar en el menú ppal la opción ```1. Agregar libro``` por ```1. Agregar Elemento```. Ahora esta opción no realizará una llamada a crearLibro(), sino que debe mostrar un submenú como el siguiente:

```kotlin
MENÚ - Agregar Elemento
 1. Libro
 2. Revista
 3. DVD
 4. Ebook
 5. VOLVER
Elige una opción ->
```

### 4. Crear una abstracción para crear elemnentos.

```kotlin
interface ICreadorElemento<T: ElementoBiblioteca> {
    fun crear() : T?
}
```

### 5. Eliminar métodos innecesarios.

El método ```crearLibro()``` de la clase ```GestorMenu``` debe eliminarse.

### 6. Desarrollo de las opciones del submenú.

* Las opciones del submenú deben llamar a un nuevo método ```agregarElemento``` sobrecargado en la clase ```GestorBiblioteca```.
* Este método tendrá como parámetro una abstracción capaz de crear un elemento de tipo ElementoBiblioteca y por tanto cumplir el contrato de ICreadorElemento.
* Cuando ```agregarElemento``` sea llamado con cada una de las opciones del submenú desde la clase ```GestorMenu``` se le pasará por argumento una instancia de cada clase concreta necesaria para crear un elemento de tipo Libro, Revista, Dvd o Ebook.
* Por tanto debéis crear las clases concretas para CrearLibro, CrearRevista, CrearDvd y CrearEbook que implementen el interface ICreadorElemento.
