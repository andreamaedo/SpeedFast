![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)
# 🧠 Actividad Sumativa 2: Sincronizando procesos en sistemas concurrentes
## 👤 Autor del proyecto
- **Nombre completo:** [Andrea Angélica Mena Aedo]
- **Sección:** [003A]
- **Carrera:** ANALISTA PROGRAMADOR COMPUTACIONAL(A)
- **Sede:** Online

---

## 📘 Descripción general del sistema
Este proyecto corresponde a la Actividad Sumativa 2: Sincronizando procesos en sistemas concurrentes
---
Estructura del proyecto 

📁 src/main/
├── Main.java              # Punto de entrada. Orquesta la creación de hilos (Threads) y la sincronización final con join().
├── ZonaDeCarga.java       # Recurso compartido. Implementa métodos 'synchronized' para evitar condiciones de carrera en el retiro de pedidos.
├── Repartidor.java        # Implementa 'Runnable'. Clase que define el comportamiento del hilo para procesar pedidos de la zona de carga en paralelo.
├── Pedido.java            # Clase abstracta base. Define los atributos esenciales (id int, dirección, estado) y encapsula el comportamiento común.
├── EstadoPedido.java      # Enum que estandariza los estados (PENDIENTE, EN_REPARTO, ENTREGADO) garantizando integridad de datos.
├── PedidoComida.java      # Subclase especializada en entregas de alimentos que hereda de la estructura base sincronizada.
├── PedidoEncomienda.java  # Subclase especializada en logística pesada integrada en el flujo concurrente.
├── PedidoExpress.java     # Subclase optimizada para entregas de alta prioridad dentro del sistema de carga.
├── PedidoService.java     # Clase de utilidad que procesa y registra pedidos mediante sobrecarga de métodos.
├── Cancelable.java        # Interfaz que define el contrato para la anulación de procesos.
├── Despachable.java       # Interfaz para estandarizar el envío y ejecución de pedidos pendientes.
└── Rastreable.java        # Interfaz encargada de la gestión y visualización del historial global de rastreo.
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
**Fecha de entrega:** \[09/02/2026]

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Evaluación Final Transversal EFT



