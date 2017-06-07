package com.falkensoft.ai.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleSupplier;

/**
 * Created by Falken224 on 5/4/2017.
 */
public class NVector {

    private List<Double> coords;

    private NVector(){}

    public NVector(Double... coords)
    {
        this.coords = new ArrayList<>(Arrays.asList(coords));
    }

    public static NVector duplicate(NVector vec)
    {
        if(vec==null)
        {
            throw new IllegalArgumentException("Cannot dupe a null vector");
        }
        return new NVector(vec.coords.toArray(new Double[vec.dimensions()]));
    }

    public static NVector createZero(int dimensions)
    {
        NVector ret = new NVector();
        for(int loop=0; loop<dimensions; loop++)
        {
            ret.coords.add(0d);
        }
        return ret;
    }

    public static NVector createRandom(int dimensions, double floor, double ceiling)
    {
        Random rnd = new Random();
        NVector ret = new NVector();
        ret.coords = new ArrayList<>();
        double range = ceiling-floor;
        for(int loop=0; loop<dimensions; loop++)
        {
            ret.coords.add((rnd.nextDouble()*range)+floor);
        }
        return ret;
    }

    public int dimensions()
    {
        return coords.size();
    }

    public static NVector add(NVector vec1, NVector vec2)
    {
        if(vec1==null || vec2==null)
        {
            throw new IllegalArgumentException("Cannot add null vectors.");
        }
        if(vec1.dimensions()!=vec2.dimensions())
        {
            throw new IllegalArgumentException("Both vectors must be the same dimsionality to add.");
        }
        NVector ret = NVector.createZero(vec1.dimensions());
        ret.increment(vec1);
        ret.increment(vec2);
        return ret;
    }

    public static NVector subtract(NVector vec1, NVector vec2)
    {
        if(vec1==null || vec2==null)
        {
            throw new IllegalArgumentException("Cannot subtract null vectors.");
        }
        if(vec1.dimensions()!=vec2.dimensions())
        {
            throw new IllegalArgumentException("Both vectors must be the same dimsionality to subtract.");
        }
        NVector ret = NVector.createZero(vec1.dimensions());
        ret.increment(vec1);
        ret.decrement(vec2);
        return ret;
    }

    public static NVector inverse(NVector vec1)
    {
        if(vec1==null)
        {
            throw new IllegalArgumentException("Cannot calculate the inverse of null vectors.");
        }
        NVector ret = NVector.duplicate(vec1);
        ret.negate(vec1);
        return ret;
    }

    public static NVector dupePerturbed(NVector vec1, DoubleSupplier perturbFactorFunction)
    {
        if(vec1==null)
        {
            throw new IllegalArgumentException("Cannot calculate the inverse of null vectors.");
        }
        NVector ret = NVector.duplicate(vec1);
        ret.perturb(perturbFactorFunction);
        return ret;
    }

    public static NVector multiply(NVector vec1, NVector vec2)
    {
        {
            if (vec1 == null || vec2 == null) {
                throw new IllegalArgumentException("Cannot add null vectors.");
            }
            if (vec1.dimensions() != vec2.dimensions()) {
                throw new IllegalArgumentException("Both vectors must be the same dimsionality to add.");
            }
            NVector ret = NVector.createZero(vec1.dimensions());
            ret.increment(vec1);
            ret.scale(vec2);
            return ret;
        }
    }

    public void set(NVector newVec)
    {
        if(newVec==null)
        {
            throw new IllegalArgumentException("Cannot set value of null vectors.");
        }
        if(this.dimensions()!=newVec.dimensions())
        {
            throw new IllegalArgumentException("Both vectors must be the same dimsionality to overwrite.");
        }
        for(int loop=0; loop<this.dimensions(); loop++)
        {
            this.coords.set(loop,(newVec.coords.get(loop)));
        }
    }

    public void scaleUniform(double scale)
    {
        for(int loop=0; loop<this.dimensions(); loop++)
        {
            this.coords.set(loop,(this.coords.get(loop)*scale));
        }
    }

    public void scale(NVector newVec)
    {
        if(newVec==null)
        {
            throw new IllegalArgumentException("Cannot add null vectors.");
        }
        if(this.dimensions()!=newVec.dimensions())
        {
            throw new IllegalArgumentException("Both vectors must be the same dimsionality to scale.");
        }
        for(int loop=0; loop<this.dimensions(); loop++)
        {
            this.coords.set(loop,(this.coords.get(loop)*newVec.coords.get(loop)));
        }
    }

    public void increment(NVector newVec)
    {
        if(newVec==null)
        {
            throw new IllegalArgumentException("Cannot add null vectors.");
        }
        if(this.dimensions()!=newVec.dimensions())
        {
            throw new IllegalArgumentException("Both vectors must be the same dimsionality to add.");
        }
        for(int loop=0; loop<this.dimensions(); loop++)
        {
            this.coords.set(loop,(this.coords.get(loop)+newVec.coords.get(loop)));
        }
    }

    public void decrement(NVector newVec)
    {
        if(newVec==null)
        {
            throw new IllegalArgumentException("Cannot subtract null vectors.");
        }
        if(this.dimensions()!=newVec.dimensions())
        {
            throw new IllegalArgumentException("Both vectors must be the same dimsionality to subtract.");
        }
        for(int loop=0; loop<this.dimensions(); loop++)
        {
            this.coords.set(loop,(this.coords.get(loop)-newVec.coords.get(loop)));
        }
    }

    public void negate(NVector newVec)
    {
        if(newVec==null)
        {
            throw new IllegalArgumentException("Cannot negate null vectors.");
        }
        for(int loop=0; loop<this.dimensions(); loop++)
        {
            this.coords.set(loop,(this.coords.get(loop)*-1d));
        }
    }

    public void perturb(DoubleSupplier perturbFactorFunction)
    {
        for(int loop=0; loop<this.dimensions(); loop++)
        {
            this.coords.set(loop,(this.coords.get(loop)*perturbFactorFunction.getAsDouble()));
        }
    }
}
