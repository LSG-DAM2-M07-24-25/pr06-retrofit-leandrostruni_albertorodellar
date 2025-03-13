<div align="center">

# 🍸 Cocktail Finder 🍹

¡Bienvenido a **Cocktail Finder**!  
Una aplicación interactiva que te permite explorar y descubrir cócteles de todo el mundo.  
Busca tus bebidas favoritas, explora categorías, encuentra cócteles aleatorios y guarda los que más te gusten en tu lista de favoritos.  

</br>

---

## 📌 **Descripción del Proyecto**

**Cocktail Finder** es una aplicación móvil desarrollada en **Kotlin** con **Jetpack Compose**, que permite a los usuarios buscar cócteles por nombre, explorar categorías de bebidas, obtener un cóctel aleatorio y administrar una lista de favoritos.  
Utiliza **TheCocktailDB API** para obtener información detallada sobre los cócteles.

</br>

---

## 🏛 **Estructura del Proyecto**

La aplicación sigue el patrón de arquitectura **MVVM (Model-View-ViewModel)**, lo que facilita la separación de lógica, datos y UI.

 ### **🧩 Módulo Model (Datos)**
 **`Drink.kt`** → Modelo de datos que representa un cóctel obtenido de la API.</br>
 **`DrinkEntity.kt`** → Modelo de datos para almacenar cócteles en la base de datos local (Room).</br>
 **`DataAPI.kt`** → Modelo que representa la estructura de la respuesta de la API.</br>
 **`Routes.kt`** → Define las rutas de navegación dentro de la aplicación</br>
 **`BottomNavItem.kt`** → Define los elementos de la barra de navegación inferior.</br></br>

 ### **📡 Módulo ViewModel (Lógica)**
 **`APIViewModel.kt`** → Maneja las solicitudes a la API y la gestión del estado de la interfaz de usuario.</br>
 **`CocktailViewModel.kt`** → Administra la lógica del juego, el almacenamiento local y las interacciones del usuario.</br></br>

 ### **🎨 Módulo View (Interfaz de Usuario)**
 **`AppCocktailNavigation.kt`** → Gestiona la navegación principal de la aplicación.</br>
 **`LaunchScreen.kt`** → Pantalla de bienvenida con animación del logo.</br>
 **`MainViewScreen.kt`** → Pantalla principal con opciones de búsqueda y exploración.</br>
 **`SearchByNameScreen.kt`** → Pantalla que permite buscar cócteles por nombre.</br>
 **`SearchByCategoryScreen.kt`** → Pantalla que muestra cócteles organizados por categoría.</br>
 **`CocktailRandomScreen.kt`** → Pantalla donde se muestra un cóctel aleatorio.</br>
 **`FavoritesScreen.kt`** → Pantalla para administrar cócteles favoritos.</br>
 **`DetailsScreen.kt`** → Pantalla de detalles con información completa de un cóctel.</br></br>
 
 ### **💾 Módulo Room (Base de Datos Local)**
 **`DrinkDao.kt`** → Interfaz DAO para la gestión de datos de cócteles en la base de datos Room.</br>
 **`DrinkDatabase.kt`** → Base de datos Room para almacenar información de los cócteles.</br>
 **`DrinkRepository.kt`** →  Capa de abstracción entre la base de datos y la UI.</br>
 **`DrinkApplication.kt`** → Configura la instancia de Room Database al iniciar la aplicación.</br></br>

 ### **🌐 Módulo API (Conexión con TheCocktailDB)**
 **`APIInterface.kt`** → Define los métodos de la API con Retrofit.</br>
 **`APIViewModel.kt`** → Maneja las solicitudes HTTP y actualiza la UI con los datos recibidos.</br></br>

</br>

---


### 🎮 Muestra del la App 🎮

<table align="center">
  <tr>
    <td align="center">
      <img src="app/src/main/res/drawable/screenshot_1.png" alt="Móvil" width="200"/>
      <p>Móvil</p>
    </td>
    <td width="100"></td> <!-- Celda vacía para espacio -->
    <td align="center">
      <img src="app/src/main/res/drawable/screenshot_2.png" alt="Móvil" width="200"/>
      <p>Móvil</p>
    </td>
  </tr>
</table>

<table align="center">
  <tr>
    <td align="center">
      <img src="app/src/main/res/drawable/screenshot_3.png" alt="Tablet" width="200"/>
      <p>Tablet</p>
    </td>
    <td width="100"></td> <!-- Celda vacía para espacio -->
    <td align="center">
      <img src="app/src/main/res/drawable/screenshot_4.png" alt="Tablet" width="200"/>
      <p>Tablet</p>
    </td>
  </tr>
</table>

### 🛠️ Tecnologías y Herramientas 🛠️

</br>

<img alt="github" src="https://user-images.githubusercontent.com/25181517/192108374-8da61ba1-99ec-41d7-80b8-fb2f7c0a4948.png" width="80"/>  
<img alt="androidstudio" src="https://user-images.githubusercontent.com/25181517/192108895-20dc3343-43e3-4a54-a90e-13a4abbc57b9.png" width="80"/>
<img alt="android" src="https://user-images.githubusercontent.com/25181517/117269608-b7dcfb80-ae58-11eb-8e66-6cc8753553f0.png" width="80"/>
<img alt="kotlin" src="https://user-images.githubusercontent.com/25181517/185062810-7ee0c3d2-17f2-4a98-9d8a-a9576947692b.png" width="80"/>

<br>

---

</br>

### Integrantes del equipo: 
<p>
  Alberto Rodellar,
  Leandro Struni</br>
</p>

<table align="center">
  <tr>
    <td>
      <table align="center">
        <tr>
          <td align="center">
            <a href="https://github.com/LeanEmanuel">
              <img src="https://github.com/LeanEmanuel/Images/blob/main/Leandro.png" alt="Mini Leandro" width="80">
            </a>
          </td>
        </tr>
        <tr>
          <td>
            <a href="https://github.com/LeanEmanuel">
              <img src="https://img.shields.io/badge/LeanEmanuel-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub" alt="Badge">
            </a>
          </td>
        </tr>
      </table>
    </td>
    <td>
      <table align="center">
        <tr>
          <td align="center">
            <a href="https://github.com/AlbertoRodellar">
              <img src="https://media.tenor.com/33I1sOQI3V4AAAAi/heimerdinger.gif" alt="Gif" width="80">
            </a>
          </td>
        </tr>
        <tr>
          <td>
            <a href="https://github.com/AlbertoRodellar">
              <img src="https://img.shields.io/badge/AlbertoRodellar-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub" alt="Badge">
            </a>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>







