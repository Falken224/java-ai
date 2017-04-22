package com.falkensoft.ai.neural

import org.testng.annotations.DataProvider
import org.testng.annotations.Test

import static com.falkensoft.ai.neural.ActivationFunctions.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.closeTo

/**
 * Created by Falken224 on 4/21/2017.
 */
class ActivationFunctionsTest {
    @Test(dataProvider = "sigmoidActivationTestSet")
    void testSigmoidActivation(double input, double expectedOut)
    {
        assertThat(sigmoid(input),closeTo(expectedOut,0.001d))
    }

    @DataProvider(name="sigmoidActivationTestSet")
    Object[][] buildSigmoidTestSet()
    {
        [
                [1.0d, 0.731d],
                [-1.0d,0.268d],
                [0.0d,0.5d]
        ]
    }
    @Test(dataProvider = "binaryActivationTestSet")
    void testBinaryActivation(double input, double expectedOut)
    {
        assertThat(binary(input),closeTo(expectedOut,0.001d))
    }

    @DataProvider(name="binaryActivationTestSet")
    Object[][] buildBinaryTestSet()
    {
        [
                [1.0d, 1.0d],
                [-1.0d,0.0d],
                [0.0d,0.0d]
        ]
    }
}
