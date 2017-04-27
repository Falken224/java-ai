package com.falkensoft.ai.neural

import org.testng.annotations.Test

/**
 * Created by Falken224 on 4/26/2017.
 */
class NeuralNetBuilderTest {

    @Test(expectedExceptions = IllegalStateException.class)
    void testTooFewWeights()
    {
        NeuralNet.define()
                .inputs(2)
                .addIntermediate(2)
                .outputs(1)
                .build(1,2,3)
    }

    @Test(expectedExceptions = IllegalStateException.class)
    void testTooManyWeights()
    {
        NeuralNet.define()
                .inputs(2)
                .addIntermediate(2)
                .outputs(1)
                .build(1,2,3,4,5,6,7,8,9,10,11)
    }

    @Test
    void testRandomWeightsWork()
    {
        NeuralNet.define()
                .inputs(2)
                .addIntermediate(2)
                .outputs(1)
                .build()
    }
}
