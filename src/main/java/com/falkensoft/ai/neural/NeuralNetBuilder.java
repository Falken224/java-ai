package com.falkensoft.ai.neural;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by Falken224 on 4/22/2017.
 */
public class NeuralNetBuilder {

    NeuralNetBuilder(){}

    public StepOneBuilder start()
    {
        return new StepOneBuilder();
    }

    public abstract class BaseBuilder<T>
    {
        protected List<Integer> layerSizes = new ArrayList<>();
        protected Function<Double,Double> activationFunction=ActivationFunctions::sigmoid;
        public T activation(Function<Double,Double> func)
        {
            this.activationFunction = func;
            return (T)this;
        }
    }

    public class StepOneBuilder extends BaseBuilder<StepOneBuilder>
    {
        private StepOneBuilder(){}
        public StepTwoBuilder inputs(int inputCount)
        {
            this.layerSizes.add(inputCount);
            return new StepTwoBuilder(this);
        }
    }

    public class StepTwoBuilder extends BaseBuilder<StepTwoBuilder>
    {
        private StepTwoBuilder(StepOneBuilder one)
        {
            this.layerSizes = one.layerSizes;
            this.activationFunction = one.activationFunction;
        }

        public StepTwoBuilder addIntermediate(Integer... neuronCount)
        {
            this.layerSizes.addAll(Arrays.asList(neuronCount));
            return this;
        }

        public StepThreeBuilder outputs(int outputCount)
        {
            this.layerSizes.add(outputCount);
            return new StepThreeBuilder(this);
        }
    }

    public class StepThreeBuilder extends BaseBuilder<StepThreeBuilder> {
        private StepThreeBuilder(StepTwoBuilder two) {
            this.layerSizes = two.layerSizes;
            this.activationFunction = two.activationFunction;
        }

        public NeuralNet build(double... weights)
        {
            List<NeuralNet.Connection> connections = new ArrayList<>();
            NeuralNet ret = new NeuralNet();
            int[] previousLayer=null;
            Iterator<Double> weightIterator;
            if(weights!=null && weights.length>0) {
                weightIterator = DoubleStream.of(weights).iterator();
            } else {
                weightIterator = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble()).iterator();
            }
            int nextNodeId = 1;
            for(int loop=0; loop<layerSizes.size(); loop++)
            {
                int[] newLayer = IntStream.range(nextNodeId,nextNodeId+(Integer)layerSizes.get(loop)).toArray();
                appendLayer(connections,previousLayer,newLayer,weightIterator);
                nextNodeId+=(Integer)layerSizes.get(loop);
                previousLayer=newLayer;
            }
            if(weightIterator.hasNext() && weights!=null && weights.length>0)
            {
                throw new IllegalStateException("Too many weights provided for the given network topology.");
            }
            ret.activationFunction = activationFunction;
            ret.totalNodes=nextNodeId-1;
            ret.connectionList = connections;
            ret.outputNodeIndex = ret.totalNodes-(Integer)layerSizes.get(layerSizes.size()-1);
            ret.inputCount = layerSizes.get(0);
            return ret;
        }

        private void appendLayer(List<NeuralNet.Connection> connections, int[] firstLayer, int[] secondLayer, Iterator<Double> it)
        {
            try {
                if (firstLayer == null) {
                    for (int loop = 0; loop < secondLayer.length; loop++) {
                        connections.add(new NeuralNet.Connection(0, secondLayer[loop], 0));
                    }
                } else {
                    for (int secondNode = 0; secondNode < secondLayer.length; secondNode++) {
                        for (int firstNode = 0; firstNode < firstLayer.length; firstNode++) {
                            connections.add(new NeuralNet.Connection(firstLayer[firstNode], secondLayer[secondNode], it.next()));
                        }
                        connections.add(new NeuralNet.Connection(0, secondLayer[secondNode], -it.next()));
                    }
                }
            }
            catch(NoSuchElementException ex)
            {
                throw new IllegalStateException("Too few weights were provided for the given network topology!");
            }
        }
    }
}
