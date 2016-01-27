package com.bc360.configurator.property;

import java.io.Serializable;

public class PersistentPropertyAttribute<T extends Serializable> extends PropertyAttribute<T> implements Serializable
{
	private static final long serialVersionUID = 8225612623852635054L;

	public PersistentPropertyAttribute(String name, Class<T> clazz)
	{
		super(name, clazz);
	}

	public PersistentPropertyAttribute(String name, Class<T> clazz, T defaultValue)
	{
		super(name, clazz, defaultValue);
	}
	

}