![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)
# 🧠 Actividad Formativa 1: Explorando la sobrecarga y sobrescritura en clases derivadas

## 👤 Autor del proyecto
- **Nombre completo:** [Andrea Angélica Mena Aedo]
- **Sección:** [003A]
- **Carrera:** ANALISTA PROGRAMADOR COMPUTACIONAL(A)
- **Sede:** Online

---

## 📘 Descripción general del sistema
Este proyecto corresponde a la Actividad Formativa 1: Explorando la sobrecarga y sobrescritura en clases derivadas. 

---

## 🧱 Estructura general del proyecto

```plaintext
📁 src/
├── Main/         # Clase principal con el método main
├── Pedido/       # Es la clase base que define los atributos generales (ID, dirección, tipo) y establece el contrato para el método de asignación de repartidores.
├── PedidoComida/   # Subclase que especializa la asignación de repartidores exigiendo específicamente el uso de mochila térmica.
├──PedidoEncomienda/ # Subclase que sobrescribe la lógica de asignación para incluir la validación obligatoria de peso y embalaje del paquete.
├──PedidoExpress/ # Subclase diseñada para gestionar entregas rápidas priorizando la cercanía geográfica y disponibilidad inmediata del repartidor.
└── PedidoService/  # Clase encargada de la lógica de negocio que utiliza la sobrecarga de métodos para procesar y registrar los diferentes tipos de pedidos.
````

---



## ⚙️ Instrucciones para clonar y ejecutar el proyecto

1. Clona el repositorio desde GitHub:

```bash
](https://github.com/andreamaedo/SpeedFast.git)```

2. Abre el proyecto en IntelliJ IDEA.

3. Ejecuta el archivo `Main.java` desde el paquete `main`.

.


---

**Repositorio GitHub:** \ (https://github.com/andreamaedo/SpeedFast.git)
**Fecha de entrega:** \[12/01/2026]

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Evaluación Final Transversal EFT



