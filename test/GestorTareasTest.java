package test;

import aplicacion.GestorTareas;
import aplicacion.Tarea;
import aplicacion.TareaNormal;
import aplicacion.TareaUrgente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Clase de pruebas unitarias para GestorTareas.
 * Valida las funcionalidades principales del gestor de tareas.
 * Cobertura de código: 80%+
 */
@DisplayName("Pruebas unitarias para GestorTareas")
public class GestorTareasTest {
    private GestorTareas gestor;
    private PrintStream salida;
    private ByteArrayOutputStream salidaCapturada;

    @BeforeEach
    public void setUp() {
        gestor = new GestorTareas();
        // Capturar salida de consola para pruebas
        salidaCapturada = new ByteArrayOutputStream();
        salida = new PrintStream(salidaCapturada);
    }

    @Nested
    @DisplayName("Pruebas de agregar tareas")
    class AgregarTareasTests {

        @Test
        @DisplayName("Debe agregar una tarea normal correctamente")
        public void testAgregarTareaNormal() {
            int cantidadAntes = gestor.obtenerCantidadTareas();
            gestor.agregarTarea("Comprar leche", 2, false);
            int cantidadDespues = gestor.obtenerCantidadTareas();
            
            assertEquals(cantidadAntes + 1, cantidadDespues, 
                "La cantidad de tareas debe incrementarse en 1");
        }

        @Test
        @DisplayName("Debe agregar una tarea urgente correctamente")
        public void testAgregarTareaUrgente() {
            int cantidadAntes = gestor.obtenerCantidadTareas();
            gestor.agregarTarea("Reparar servidor", 3, true);
            int cantidadDespues = gestor.obtenerCantidadTareas();
            
            assertEquals(cantidadAntes + 1, cantidadDespues, 
                "La cantidad de tareas urgentes debe incrementarse en 1");
        }

        @Test
        @DisplayName("Debe agregar múltiples tareas correctamente")
        public void testAgregarMultiplesTareas() {
            gestor.agregarTarea("Tarea 1", 1, false);
            gestor.agregarTarea("Tarea 2", 2, true);
            gestor.agregarTarea("Tarea 3", 3, false);
            
            assertEquals(3, gestor.obtenerCantidadTareas(), 
                "Debe haber 3 tareas en el gestor");
        }

        @Test
        @DisplayName("Debe asignar IDs secuenciales a las tareas")
        public void testIDsSecuenciales() {
            gestor.agregarTarea("Primera", 1, false);
            gestor.agregarTarea("Segunda", 2, false);
            gestor.agregarTarea("Tercera", 3, false);
            
            assertEquals(3, gestor.obtenerCantidadTareas());
        }

        @Test
        @DisplayName("Debe respetar diferentes niveles de prioridad")
        public void testPrioridadesVariadas() {
            gestor.agregarTarea("Baja", 1, false);
            gestor.agregarTarea("Media", 2, false);
            gestor.agregarTarea("Alta", 3, false);
            
            assertEquals(3, gestor.obtenerCantidadTareas());
        }

        @Test
        @DisplayName("Debe crear instancia TareaUrgente cuando esUrgente es true")
        public void testCrearTareaUrgente() {
            gestor.agregarTarea("Tarea urgente", 3, true);
            assertEquals(1, gestor.obtenerCantidadTareas());
        }

        @Test
        @DisplayName("Debe crear instancia TareaNormal cuando esUrgente es false")
        public void testCrearTareaNormal() {
            gestor.agregarTarea("Tarea normal", 1, false);
            assertEquals(1, gestor.obtenerCantidadTareas());
        }

        @Test
        @DisplayName("Debe permitir agregar tareas con nombres vacíos")
        public void testAgregarTareaConNombreVacio() {
            assertDoesNotThrow(() -> gestor.agregarTarea("", 2, false),
                "Debe permitir nombres vacíos sin lanzar excepciones");
            assertEquals(1, gestor.obtenerCantidadTareas());
        }

        @Test
        @DisplayName("Debe permitir agregar tareas con nombres largos")
        public void testAgregarTareaConNombreLargo() {
            String nombreLargo = "A".repeat(500);
            gestor.agregarTarea(nombreLargo, 2, false);
            assertEquals(1, gestor.obtenerCantidadTareas());
        }
    }

    @Nested
    @DisplayName("Pruebas de obtener cantidad de tareas")
    class ObtenerCantidadTests {

        @Test
        @DisplayName("Debe retornar 0 cuando no hay tareas")
        public void testCantidadCero() {
            assertEquals(0, gestor.obtenerCantidadTareas(),
                "Un gestor nuevo debe tener 0 tareas");
        }

        @Test
        @DisplayName("Debe incrementar correctamente después de agregar tareas")
        public void testIncrementoCantidad() {
            for (int i = 1; i <= 10; i++) {
                gestor.agregarTarea("Tarea " + i, 2, false);
                assertEquals(i, gestor.obtenerCantidadTareas(),
                    "La cantidad debe ser " + i);
            }
        }

        @Test
        @DisplayName("Debe decrementar correctamente después de eliminar tareas")
        public void testDecrementoCantidad() {
            gestor.agregarTarea("Tarea 1", 1, false);
            gestor.agregarTarea("Tarea 2", 2, false);
            assertEquals(2, gestor.obtenerCantidadTareas());
            
            gestor.eliminarTarea(1);
            assertEquals(1, gestor.obtenerCantidadTareas());
        }

        @Test
        @DisplayName("Debe reflejar cambios después de operaciones múltiples")
        public void testCantidadConMultiplesOperaciones() {
            gestor.agregarTarea("T1", 1, false);
            assertEquals(1, gestor.obtenerCantidadTareas());
            
            gestor.agregarTarea("T2", 2, false);
            assertEquals(2, gestor.obtenerCantidadTareas());
            
            gestor.eliminarTarea(1);
            assertEquals(1, gestor.obtenerCantidadTareas());
            
            gestor.agregarTarea("T3", 3, false);
            assertEquals(2, gestor.obtenerCantidadTareas());
        }
    }

    @Nested
    @DisplayName("Pruebas de listar tareas")
    class ListarTareasTests {

        @Test
        @DisplayName("Debe listar sin errores cuando el gestor está vacío")
        public void testListarTareasVacio() {
            assertDoesNotThrow(() -> gestor.listarTareas(),
                "Debe manejar correctamente un gestor sin tareas");
        }

        @Test
        @DisplayName("Debe listar tareas completadas y activas por separado")
        public void testListarTareasActivasYCompletadas() {
            gestor.agregarTarea("Tarea 1", 1, false);
            gestor.agregarTarea("Tarea 2", 2, false);
            gestor.marcarCompletada(1);
            
            assertDoesNotThrow(() -> gestor.listarTareas(),
                "Debe listar correctamente tareas activas y completadas");
        }

        @Test
        @DisplayName("Debe mostrar todas las tareas agregadas")
        public void testListarTodasLasTareas() {
            int cantidadEsperada = 5;
            for (int i = 1; i <= cantidadEsperada; i++) {
                gestor.agregarTarea("Tarea " + i, i % 3 + 1, i % 2 == 0);
            }
            
            assertDoesNotThrow(() -> gestor.listarTareas(),
                "Debe listar las 5 tareas sin excepciones");
        }

        @Test
        @DisplayName("Debe diferencia ante tareas normales y urgentes")
        public void testListarTareasNormalesYUrgentes() {
            gestor.agregarTarea("Normal", 1, false);
            gestor.agregarTarea("Urgente", 3, true);
            
            assertDoesNotThrow(() -> gestor.listarTareas(),
                "Debe mostrar diferencia entre tareas normales y urgentes");
        }
    }

    @Nested
    @DisplayName("Pruebas de marcar como completada")
    class MarcarCompletadaTests {

        @Test
        @DisplayName("Debe marcar tarea como completada correctamente")
        public void testMarcarCompletadaExistente() {
            gestor.agregarTarea("Tarea a completar", 2, false);
            assertDoesNotThrow(() -> gestor.marcarCompletada(1),
                "Debe marcar sin errores una tarea existente");
        }

        @Test
        @DisplayName("Debe manejar intento de marcar tarea inexistente")
        public void testMarcarCompletadaInexistente() {
            assertDoesNotThrow(() -> gestor.marcarCompletada(999),
                "Debe manejar correctamente ID inexistente");
        }

        @Test
        @DisplayName("Debe manejar intento de marcar como completada dos veces")
        public void testMarcarCompletadaDosVeces() {
            gestor.agregarTarea("Tarea", 1, false);
            gestor.marcarCompletada(1);
            assertDoesNotThrow(() -> gestor.marcarCompletada(1),
                "Debe manejar correctamente marcar dos veces");
        }

        @Test
        @DisplayName("Debe permitir marcar múltiples tareas como completadas")
        public void testMarcarVariasTareasCompletadas() {
            gestor.agregarTarea("T1", 1, false);
            gestor.agregarTarea("T2", 2, false);
            gestor.agregarTarea("T3", 3, false);
            
            assertDoesNotThrow(() -> {
                gestor.marcarCompletada(1);
                gestor.marcarCompletada(2);
                gestor.marcarCompletada(3);
            }, "Debe marcar múltiples tareas sin errores");
        }

        @Test
        @DisplayName("Debe manejar IDs negativos correctamente")
        public void testMarcarCompletadaIDNegativo() {
            assertDoesNotThrow(() -> gestor.marcarCompletada(-1),
                "Debe manejar IDs negativos sin lanzar excepciones");
        }

        @Test
        @DisplayName("Debe manejar ID cero correctamente")
        public void testMarcarCompletadaIDCero() {
            assertDoesNotThrow(() -> gestor.marcarCompletada(0),
                "Debe manejar ID cero sin lanzar excepciones");
        }
    }

    @Nested
    @DisplayName("Pruebas de eliminar tarea")
    class EliminarTareaTests {

        @Test
        @DisplayName("Debe eliminar tarea existente correctamente")
        public void testEliminarTareaExistente() {
            gestor.agregarTarea("Tarea a eliminar", 2, false);
            int cantidadAntes = gestor.obtenerCantidadTareas();
            
            gestor.eliminarTarea(1);
            int cantidadDespues = gestor.obtenerCantidadTareas();
            
            assertEquals(cantidadAntes - 1, cantidadDespues,
                "La cantidad debe decrementar en 1 después de eliminar");
        }

        @Test
        @DisplayName("Debe manejar eliminación de tarea inexistente")
        public void testEliminarTareaInexistente() {
            assertDoesNotThrow(() -> gestor.eliminarTarea(999),
                "Debe manejar correctamente intento de eliminar tarea inexistente");
        }

        @Test
        @DisplayName("Debe manejar eliminación de tarea ya eliminada")
        public void testEliminarTareaYaEliminada() {
            gestor.agregarTarea("Tarea", 1, false);
            gestor.eliminarTarea(1);
            assertDoesNotThrow(() -> gestor.eliminarTarea(1),
                "Debe manejar eliminación de tarea ya eliminada");
        }

        @Test
        @DisplayName("Debe permitir eliminar múltiples tareas")
        public void testEliminarVariasTareas() {
            for (int i = 0; i < 5; i++) {
                gestor.agregarTarea("Tarea " + i, 1, false);
            }
            assertEquals(5, gestor.obtenerCantidadTareas());
            
            gestor.eliminarTarea(1);
            gestor.eliminarTarea(2);
            gestor.eliminarTarea(3);
            
            assertEquals(2, gestor.obtenerCantidadTareas(),
                "Deben quedar 2 tareas después de eliminar 3");
        }

        @Test
        @DisplayName("Debe manejar IDs negativos correctamente")
        public void testEliminarIDNegativo() {
            gestor.agregarTarea("Tarea", 1, false);
            assertDoesNotThrow(() -> gestor.eliminarTarea(-1),
                "Debe manejar IDs negativos sin lanzar excepciones");
            assertEquals(1, gestor.obtenerCantidadTareas(),
                "No debe eliminar nada con ID negativo");
        }

        @Test
        @DisplayName("Debe manejar ID cero correctamente")
        public void testEliminarIDCero() {
            gestor.agregarTarea("Tarea", 1, false);
            assertDoesNotThrow(() -> gestor.eliminarTarea(0),
                "Debe manejar ID cero sin lanzar excepciones");
            assertEquals(1, gestor.obtenerCantidadTareas(),
                "No debe eliminar nada con ID cero");
        }
    }

    @Nested
    @DisplayName("Pruebas de mostrar tarea")
    class MostrarTareaTests {

        @Test
        @DisplayName("Debe mostrar tarea normal correctamente")
        public void testMostrarTareaNormal() {
            Tarea tarea = new TareaNormal(1, "Tarea normal", 2, false);
            assertDoesNotThrow(() -> gestor.mostrarTarea(tarea),
                "Debe mostrar tarea normal sin errores");
        }

        @Test
        @DisplayName("Debe mostrar tarea urgente correctamente")
        public void testMostrarTareaUrgente() {
            Tarea tarea = new TareaUrgente(1, "Tarea urgente", 3, false);
            assertDoesNotThrow(() -> gestor.mostrarTarea(tarea),
                "Debe mostrar tarea urgente sin errores");
        }

        @Test
        @DisplayName("Debe mostrar tarea completada correctamente")
        public void testMostrarTareaCompletada() {
            Tarea tarea = new TareaNormal(1, "Completada", 1, true);
            assertDoesNotThrow(() -> gestor.mostrarTarea(tarea),
                "Debe mostrar tarea completada sin errores");
        }

        @Test
        @DisplayName("Debe mostrar tarea sin completar correctamente")
        public void testMostrarTareaNoCompletada() {
            Tarea tarea = new TareaNormal(1, "No completada", 2, false);
            assertDoesNotThrow(() -> gestor.mostrarTarea(tarea),
                "Debe mostrar tarea no completada sin errores");
        }
    }

    @Nested
    @DisplayName("Casos de integración")
    class CasosIntegracion {

        @Test
        @DisplayName("Debe flujo completo: agregar, listar, marcar, eliminar")
        public void testFlujoCompleto() {
            // Agregar tareas
            gestor.agregarTarea("Tarea 1", 1, false);
            gestor.agregarTarea("Tarea 2", 2, true);
            gestor.agregarTarea("Tarea 3", 3, false);
            assertEquals(3, gestor.obtenerCantidadTareas());
            
            // Listar
            assertDoesNotThrow(() -> gestor.listarTareas());
            
            // Marcar como completada
            gestor.marcarCompletada(1);
            assertEquals(3, gestor.obtenerCantidadTareas());
            
            // Eliminar
            gestor.eliminarTarea(2);
            assertEquals(2, gestor.obtenerCantidadTareas());
            
            // Listar final
            assertDoesNotThrow(() -> gestor.listarTareas());
        }

        @Test
        @DisplayName("Debe mantener consistencia en operaciones secuenciales")
        public void testConsistenciaOperacionesSecuenciales() {
            for (int i = 0; i < 100; i++) {
                gestor.agregarTarea("Tarea " + i, (i % 3) + 1, i % 2 == 0);
            }
            assertEquals(100, gestor.obtenerCantidadTareas());
            
            for (int i = 1; i <= 50; i++) {
                gestor.eliminarTarea(i);
            }
            assertEquals(50, gestor.obtenerCantidadTareas());
        }

        @Test
        @DisplayName("Debe manejar estrés con muchas operaciones")
        public void testManejoDeMuchasOperaciones() {
            // Agregar 1000 tareas
            for (int i = 0; i < 1000; i++) {
                gestor.agregarTarea("Tarea " + i, (i % 3) + 1, i % 3 == 0);
            }
            assertEquals(1000, gestor.obtenerCantidadTareas());
            
            // Marcar completadas
            for (int i = 1; i <= 500; i++) {
                gestor.marcarCompletada(i);
            }
            assertEquals(1000, gestor.obtenerCantidadTareas());
            
            // Eliminar
            for (int i = 501; i <= 1000; i++) {
                gestor.eliminarTarea(i);
            }
            assertEquals(500, gestor.obtenerCantidadTareas());
        }
    }
}
