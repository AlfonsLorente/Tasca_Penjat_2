package test;

import com.jaume.penjat.Puntuacio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PuntuacioTest {

    private Puntuacio puntuacio;
    @BeforeEach
    void init(){
        puntuacio = new Puntuacio();
    }

    @Test
    void getIntentsFuncionament() {
        puntuacio.getParaulaSecretaDificultat(2);
        assertEquals(4, puntuacio.getIntents(), "Se esperaba 4");
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getParaulaSecretaDificultat_1(int num) {
        String[] paraules1 = {"cargol","porc","aranya","patates","farina","nabiu","elefant"};
        String[] paraules2 = {"periquito", "peix espasa","nectarina", "peix daurat","xinxilla","armadillo","llenties"};
        String[] paraules3 = {"escombraries","escopinyes","engronxador","desnonament","malhauradament","malbaratament",};
        boolean bool = false;
        switch(num){
            case 1:
                if(Arrays.asList(paraules1).contains(puntuacio.getParaulaSecretaDificultat(num)));
                    bool = true;
                break;
            case 2:
                if(Arrays.asList(paraules2).contains(puntuacio.getParaulaSecretaDificultat(num)));
                bool = true;
                break;
            case 3:
                if(Arrays.asList(paraules3).contains(puntuacio.getParaulaSecretaDificultat(num)));
                bool = true;

                break;
            default:
                System.out.println("Hi ha hagut un error");
        }
        assertEquals(true, bool, "La paraula no s'ha trobat");

    }

    @Test
    void getParaulaSecretaDificultat_2() {
        assertEquals("err", puntuacio.getParaulaSecretaDificultat(-1), "Ha hagut un error, s'esperava: err");
    }

    @Test
    void calcularPuntuacio_1() {
        puntuacio.getParaulaSecretaDificultat(2);
        puntuacio.setParaula("armadillo");
        String[] paraula = {"a", "r", "m", "a", "d", "i", "l", "l", "o"};
        float resultat = puntuacio.calcularPuntuacio(paraula, 4);
        assertEquals(400, resultat, "Ha calculat malament la puntuacio");

    }

    @Test
    void calcularPuntuacio_2() {
        puntuacio.getParaulaSecretaDificultat(2);
        puntuacio.setParaula("escopinyes");
        String[] paraula = {"e", "s", "c", "o", "p", "i", "n", "y", "e", "s"};
        float resultat = puntuacio.calcularPuntuacio(paraula, 4);
        assertEquals(410, resultat, "Ha calculat malament, no ha afegit els 10 punts extres");

    }


    @Test
    void calcularPuntuacio_3() throws InterruptedException {
        puntuacio.getParaulaSecretaDificultat(2);
        puntuacio.setParaula("armadillo");
        TimeUnit.SECONDS.sleep(20);//Aturar 20 segons
        String[] paraula = {"a", "r", "m", "a", "d", "i", "l", "l", "o"};
        float resultat = puntuacio.calcularPuntuacio(paraula, 4);
        assertEquals(380, resultat, "Ha calculat malament la puntuacio, no ha contat el temps");
    }
}