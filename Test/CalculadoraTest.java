import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas para calculadora")
class CalculadoraTest {

    @Test
    @DisplayName("Sumando 1 y 3")
    public void pruebaSuma1(){
        assertEquals(Calculadora.sumar(1,3),4, "Sumando 1 y 3");
    }

    @Test
    public void pruebaSuma2(){
        assertEquals(Calculadora.sumar(0,0),0, "Sumando 0 y 0");
    }

    @Test
    public void pruebaSuma3(){
        assertEquals(0, Calculadora.sumar(1,-1), "sumando 1 y -1");
    }


}