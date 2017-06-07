package com.falkensoft.ai.neural.training;

import com.falkensoft.ai.neural.NeuralNet;
import com.falkensoft.ai.util.NVector;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleBiFunction;

/**
 * Created by Falken224 on 5/3/2017.
 */
public class EPSO {
    private static final double learningFactor=0.1d;
    public static NeuralNet train(NeuralNet net, int errorFloorIterations, int problemArea, int pointCount, TrainingData trainingData, ToDoubleBiFunction<List<Double>,List<Double>> scoringFunction)
    {
        Random rnd = new Random();
        int dimensions = net.weights().size();
        List<NVector> swarm = new ArrayList<>();
        List<NVector> swarmInertia = new ArrayList<>();
        List<Pair<NVector,Double>> swarmBest = new ArrayList<>();
        Pair<List<Double>,Double> globalBest;

        for(int pointIndex=0; pointIndex<pointCount; pointIndex++)
        {
            swarm.add(NVector.createRandom(dimensions,-problemArea,problemArea));
            swarmInertia.add(NVector.createRandom(dimensions,0,1));
        }
    }

    private static void iteratePoint(NeuralNet net, Random rnd, NVector originalPoint, NVector originalInertia, Double inertiaWeight,
                                     Pair<NVector,Double> pointBest, Double bestWeight,
                                     Pair<NVector,Double> globalBest, Double globalWeight,
                                     TrainingData trainingData, ToDoubleBiFunction<NVector,NVector> scoringFunction)
    {
        NVector point1 = NVector.duplicate(originalPoint)
        NVector point2 = NVector.duplicate(originalPoint);
        NVector towardBest = NVector.subtract(pointBest.getLeft(),originalPoint);
        NVector towardGlobalBest = NVector.subtract(globalBest.getLeft(),originalPoint);

        NVector newVelocity1 = NVector.dupePerturbed(originalInertia,() -> rnd.nextGaussian() * inertiaWeight);
        NVector newVelocity2 = NVector.dupePerturbed(originalInertia,() -> rnd.nextGaussian() * inertiaWeight);

        newVelocity1.increment(NVector.dupePerturbed(towardBest,() -> rnd.nextGaussian() * bestWeight));
        newVelocity1.increment(NVector.dupePerturbed(towardGlobalBest,() -> rnd.nextGaussian() * globalWeight));
        point1.increment(newVelocity1);

        newVelocity2.increment(NVector.dupePerturbed(towardBest,() -> rnd.nextGaussian() * bestWeight));
        newVelocity2.increment(NVector.dupePerturbed(towardGlobalBest,() -> rnd.nextGaussian() * globalWeight));
        point2.increment(newVelocity2);


    }

    private static NVector
}
