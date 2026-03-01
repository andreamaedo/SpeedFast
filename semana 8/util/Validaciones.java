package util;


public class Validaciones {

    /** Verifica que el texto no sea nulo ni vacío */
    public static boolean noEstaVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /** Solo letras y espacios (para nombres de repartidores) */
    public static boolean soloLetras(String texto) {
        return texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
    }

    /** Dirección: letras, números, espacios y caracteres comunes de direcciones */
    public static boolean esDireccionValida(String texto) {
        return texto.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ #.,\\-]+");
    }
}