# 💧 RetoAguaApp 💧 – Versión Stateless y Modularizada 

Esta es una versión **modularizada y sin estado** de la aplicación RetoAguaApp, desarrollada con **Jetpack Compose**. La app permite calcular una **meta diaria de hidratación** con base en el peso del usuario y si realizó ejercicio, y permite registrar el consumo de vasos de agua. Todos los componentes UI son **reutilizables, sin manejo de estado interno**, siguiendo buenas prácticas de diseño limpio.

---

## 🚀 Características principales

- 📦 Componentes `@Composable` completamente **modulares y sin estado**.
- ✅ Cálculo de meta diaria (peso × 35 ml + 500 ml si hizo ejercicio).
- 🥤 Registro de consumo en vasos (250 ml por vaso).
- 🧠 Separación clara entre lógica de estado y presentación visual.
- 🎓 Ideal para enseñanza de Compose, estados, y arquitectura limpia.

---

## 🧠 Tecnologías utilizadas

- Kotlin
- Jetpack Compose
- Material 3
- Android Studio (Giraffe o superior)

---

## 📷 Capturas sugeridas

> *(Agrega aquí tus propias capturas si deseas)*

```bash
💧 Pantalla principal:
- Entrada de peso (OutlinedTextField)
- Switch: ¿Hizo ejercicio hoy?
- Botón: Calcular meta
- Sección de progreso y botón de vasos
- Mensaje final de meta alcanzada o cuánto falta
```

---

## 🛠️ Cómo ejecutar el proyecto

```bash
git clone git remote add origin https://github.com/alejing/reto-agua-app-sin-estado.git
cd reto-agua-app-sin-estado
```

1. Abre el proyecto en **Android Studio**.
2. Sincroniza las dependencias (`Sync Now`).
3. Ejecuta la app en un dispositivo físico o emulador.

---

## 📚 Propósito educativo

Este proyecto fue creado para enseñar a estudiantes cómo construir aplicaciones más limpias, reutilizables y mantenibles usando **Jetpack Compose sin estado (Stateless UI)**. Es un excelente punto de partida para aprender:

- Manejo explícito de estados desde el padre (`remember`, `mutableStateOf`)
- Cómo desacoplar UI de lógica de negocio

---

## 👨‍🏫 Autor y licencia

Proyecto desarrollado por **José Alejandro Franco Calderon** para fines educativos.  
Licencia: MIT

---
