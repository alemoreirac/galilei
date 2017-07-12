package com.example.pefoce.peritolocal;

import org.junit.Assert;
import org.junit.Test;

import Enums.SetorDano;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getValorTeste(){
        String actual = SetorDano.ANTERIOR_DIREITO.getValor();
        String expected = "Setor Anterior Direito!";
        assertEquals(actual,expected);

    }
    public ExampleUnitTest()
    {

    }
}