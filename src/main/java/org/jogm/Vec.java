package org.jogm;

import java.util.Arrays;

public class Vec implements Cloneable
{
	private float[] values;

	public Vec(float... values)
	{
		if(values.length > 4)
			throw new JOGMTooManyDimensionsException("JOGM currently does not support more than 4 dimensions");

		for(float f : values)
			if(Float.isNaN(f))
				throw new JOGMEmptyVectorException("JOGM Vectors must not be empty");

		if(values.length == 0)
			throw new JOGMEmptyVectorException("JOGM Vectors must not be empty");

		this.values = Arrays.copyOf(values, values.length);
	}

	public Vec(Vec vec, int size)
	{
		if(size > 4)
			throw new JOGMTooManyDimensionsException("JOGM currently does not support more than 4 dimensions");

		if(size == 0)
			throw new JOGMEmptyVectorException("JOGM Vectors must not be empty");

		if(vec == null)
			throw new NullPointerException("JOGM Vectors must not be null");

		this.values = Arrays.copyOf(values, values.length);
	}

	public Vec(int size)
	{
		if(size > 4)
			throw new JOGMTooManyDimensionsException("JOGM currently does not support more than 4 dimensions");

		if(size == 0)
			throw new JOGMEmptyVectorException("JOGM Vectors must not be empty");

		values = new float[size];
		for(int i = 0; i < size; i++)
			values[i] = 0;
	}

	// TODO: Design better system for Format String
	public Vec get(String format)
	{
		if(format.length() > 4)
			throw new JOGMTooManyDimensionsException("JOGM currently does not support more than 4 dimensions");

		if(format.length() == 0)
			throw new JOGMEmptyVectorException("JOGM Vectors must not be empty");

		if(!format.matches("(x|y|z|w)*"))
			throw new JOGMIncompatibleFormatException("JOGM Vectors support only 'x', 'y', 'z' and 'w' as dimensions");

		float[] ret = new float[format.length()];

		for(int i = 0; i < format.length(); i++)
		{
			switch(format.charAt(i))
			{
				case 'x':ret[i] = values[0];break;
				case 'y':ret[i] = values[1];break;
				case 'z':ret[i] = values[2];break;
				case 'w':ret[i] = values[3];break;
			}
		}

		return new Vec(ret);
	}

	// TODO: Design better system for Format String
	public float get(char dimension)
	{
		switch(dimension)
		{
			case 'x':return values[0];
			case 'y':return values[1];
			case 'z':return values[2];
			case 'w':return values[3];
			default: throw new JOGMIncompatibleFormatException("JOGM Vectors support only 'x', 'y', 'z' and 'w' as dimensions");
		}
	}

	public int getDimensionCount()
	{
		return values.length;
	}
	public float getLength()
	{
		double sum = 0;
		for(float f : values)
			sum += (Math.pow((double)(f),2));
		return (float)(Math.sqrt(sum));
	}
}
