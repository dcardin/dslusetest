package com.bc360.configurator.property;



public class TransientPropertyAttribute<T> extends PropertyAttribute<T>
{
	public TransientPropertyAttribute(String name, Class<T> clazz)
	{
		super(name, clazz);
	}

	public TransientPropertyAttribute(String name, Class<T> clazz, T defaultValue)
	{
		super(name, clazz, defaultValue);
	}
	
	@Override
	public boolean isTransient() {
		return true;
	}
}
