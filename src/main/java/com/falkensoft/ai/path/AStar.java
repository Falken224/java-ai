package com.falkensoft.ai.path;

import org.apache.commons.lang3.tuple.Triple;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

/**
 * Created by Falken224 on 5/2/2017.
 */
public class AStar {

    public static <T> List<T> findPath(T start, T end, Function<Step<T>,Set<Step<T>>> stepMaker, ToDoubleFunction<T> remainingScore)
    {
        Map<T,Step<T>> allSteps = new HashMap<>();
        TreeSet<Step<T>> stepScores = new TreeSet<>(Comparator.comparing(it->it.getTotalScore()));

        Step<T> startStep = new Step<>(start,null,0d).calcScore(remainingScore);
        allSteps.put(start,startStep);
        stepScores.add(startStep);

        while(stepScores.size()>0) {
            Step<T> currentStep = stepScores.first();
            for (Step<T> step : stepMaker.apply(currentStep)) {
                step.calcScore(remainingScore);
                if(step.getLoc().equals(end))
                {
                    //We're done . . . generate the return and go!
                    List<T> ret = new ArrayList<>();
                    while(step!=null)
                    {
                        ret.add(step.getLoc());
                        step=step.getPrevious();
                    }
                    Collections.reverse(ret);
                    return ret;
                }
                if(allSteps.containsKey(step.getLoc()))
                {
                    if(allSteps.get(step.getLoc()).getTotalScore() > step.getTotalScore())
                    {
                        stepScores.remove(allSteps.get(step.getLoc()));
                        stepScores.add(step);
                        allSteps.put(step.getLoc(),step);
                    }
                    continue;
                }
                allSteps.put(step.getLoc(), step);
                stepScores.add(step);
            }
            stepScores.remove(currentStep);
        }
        throw new IllegalStateException("There is no valid path from start to end!");
    }

}
