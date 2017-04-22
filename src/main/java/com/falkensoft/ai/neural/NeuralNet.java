package com.falkensoft.ai.neural;

import java.util.function.Function;

/**
 * Created by Falken224 on 4/22/2017.
 */
public class NeuralNet {
    Function<Double,Double> activationFunction;
    int inputCount;
    double[] outputs;
    double[] weightMatrix;

    NeuralNet(){}

    public static NeuralNetBuilder define()
    {
        return new NeuralNetBuilder();
    }

    public NeuralNet activate(double... inputs)
    {
        if(inputs==null || inputs.length!=inputCount)
        {
            throw new IllegalArgumentException("Number of inputs provided on activaton must equal the number defined for the net.");
        }
        //Normally we'd put intermediate layers here . . .
        for(int output=0; output<outputs.length; output++)
        {
            double sum = -weightMatrix[(output)*(inputCount+1)+inputCount];
            for(int input=0; input<inputCount; input++)
            {
                sum += inputs[input] * weightMatrix[output*(inputCount+1)+input];
            }
            outputs[output]=activationFunction.apply(sum);
        }
        return this;
    }

    public double[] outputs()
    {
        return outputs;
    }
}
