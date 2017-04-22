package com.falkensoft.ai.neural

import org.testng.annotations.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.greaterThan
import static org.hamcrest.Matchers.lessThan

/**
 * Created by Falken224 on 4/21/2017.
 */
public class NeuralNetTest {

    @Test
    void testOrNet()
    {
        NeuralNet net = NeuralNet.define()
                .activation(ActivationFunctions.&binary)
                .inputs(2)
                .weightMatrix(1,1,0.5)
                .build();
        assertThat("OR gate net failed",net.activate(1,1).outputs()[0],greaterThan(0.5d))
        assertThat("OR gate net failed",net.activate(0,1).outputs()[0],greaterThan(0.5d))
        assertThat("OR gate net failed",net.activate(1,0).outputs()[0],greaterThan(0.5d))
        assertThat("OR gate net failed",net.activate(0.24,0.24).outputs()[0],lessThan(0.5d))
        assertThat("OR gate net failed",net.activate(0.26,0.26).outputs()[0],greaterThan(0.5d))
    }

    @Test
    void testAndNet()
    {
        NeuralNet net = NeuralNet.define()
                .activation(ActivationFunctions.&binary)
                .inputs(2)
                .weightMatrix(1,1,1.5)
                .build();
        assertThat("AND gate net failed",net.activate(1,1).outputs()[0],greaterThan(0.5d))
        assertThat("AND gate net failed",net.activate(0,1).outputs()[0],lessThan(0.5d))
        assertThat("AND gate net failed",net.activate(1,0).outputs()[0],lessThan(0.5d))
        assertThat("AND gate net failed",net.activate(0,0).outputs()[0],lessThan(0.5d))
        assertThat("AND gate net failed",net.activate(0.75,0.74).outputs()[0],lessThan(0.5d))
        assertThat("AND gate net failed",net.activate(0.75,0.76).outputs()[0],greaterThan(0.5d))
    }
}