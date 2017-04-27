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
                .outputs(1)
                .build(1,1,0.5);
        assertThat("OR gate net failed",net.setInputs(1,1).getOutput(0),greaterThan(0.5d))
        assertThat("OR gate net failed",net.setInputs(0,1).getOutput(0),greaterThan(0.5d))
        assertThat("OR gate net failed",net.setInputs(1,0).getOutput(0),greaterThan(0.5d))
        assertThat("OR gate net failed",net.setInputs(0.24,0.24).getOutput(0),lessThan(0.5d))
        assertThat("OR gate net failed",net.setInputs(0.26,0.26).getOutput(0),greaterThan(0.5d))
    }

    @Test
    void testAndNet()
    {
        NeuralNet net = NeuralNet.define()
                .activation(ActivationFunctions.&binary)
                .inputs(2)
                .outputs(1)
                .build(1,1,1.5);
        assertThat("AND gate net failed",net.setInputs(1,1).getOutput(0),greaterThan(0.5d))
        assertThat("AND gate net failed",net.setInputs(0,1).getOutput(0),lessThan(0.5d))
        assertThat("AND gate net failed",net.setInputs(1,0).getOutput(0),lessThan(0.5d))
        assertThat("AND gate net failed",net.setInputs(0,0).getOutput(0),lessThan(0.5d))
        assertThat("AND gate net failed",net.setInputs(0.75,0.74).getOutput(0),lessThan(0.5d))
        assertThat("AND gate net failed",net.setInputs(0.75,0.76).getOutput(0),greaterThan(0.5d))
    }

    @Test
    void testOrNetWithIntermediates()
    {
        NeuralNet net = NeuralNet.define()
                .activation(ActivationFunctions.&binary)
                .inputs(2)
                .addIntermediate(2)
                .outputs(1)
                .build(1,0,0,0,1,0,1,1,0.5);
        assertThat("OR gate net failed",net.setInputs(1,1).getOutput(0),greaterThan(0.5d))
        assertThat("OR gate net failed",net.setInputs(0,1).getOutput(0),greaterThan(0.5d))
        assertThat("OR gate net failed",net.setInputs(1,0).getOutput(0),greaterThan(0.5d))
        //These don't work in multi-layer binary networks.
//        assertThat("OR gate net failed",net.setInputs(0.24,0.24).getOutput(0),lessThan(0.5d))
//        assertThat("OR gate net failed",net.setInputs(0.26,0.26).getOutput(0),greaterThan(0.5d))
    }

    @Test
    void testAndNetWithIntermediates()
    {
        NeuralNet net = NeuralNet.define()
                .activation(ActivationFunctions.&binary)
                .inputs(2)
                .addIntermediate(2)
                .outputs(1)
                .build(1,0,0,0,1,0,1,1,1.5);
        assertThat("AND gate net failed",net.setInputs(1,1).getOutput(0),greaterThan(0.5d))
        assertThat("AND gate net failed",net.setInputs(0,1).getOutput(0),lessThan(0.5d))
        assertThat("AND gate net failed",net.setInputs(1,0).getOutput(0),lessThan(0.5d))
        assertThat("AND gate net failed",net.setInputs(0,0).getOutput(0),lessThan(0.5d))
        //These don't work in multi-layer binary netowrks.
//        assertThat("AND gate net failed",net.setInputs(0.75,0.74).getOutput(0),lessThan(0.5d))
//        assertThat("AND gate net failed",net.setInputs(0.75,0.76).getOutput(0),greaterThan(0.5d))
    }
}
