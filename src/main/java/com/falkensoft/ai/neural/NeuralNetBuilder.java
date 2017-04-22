package com.falkensoft.ai.neural;

import java.util.function.Function;

/**
 * Created by Falken224 on 4/22/2017.
 */
public class NeuralNetBuilder {

    private Function<Double,Double> activationFunction=ActivationFunctions::sigmoid;
    private Integer inputCount=1;
    private Integer outputCount=1;
    private double[] weightMatrix;

    NeuralNetBuilder(){}

    public NeuralNet build()
    {
        NeuralNet ret = new NeuralNet();
        ret.inputCount = inputCount;
        ret.outputs = new double[outputCount];
        if(weightMatrix==null)
        {
            weightMatrix = new double[(inputCount+1)*outputCount];
        }
        ret.weightMatrix = weightMatrix;
        ret.activationFunction = activationFunction;
        return ret;
    }

    public NeuralNetBuilder inputs(int inputCount)
    {
        this.inputCount = inputCount;
        return this;
    }

    public NeuralNetBuilder outputs(int outputCount)
    {
        this.outputCount = outputCount;
        return this;
    }

    public NeuralNetBuilder activation(Function<Double,Double> func)
    {
        this.activationFunction = func;
        return this;
    }

    public NeuralNetBuilder weightMatrix(double... weightMatrix)
    {
        this.weightMatrix = weightMatrix;
        return this;
    }
}
