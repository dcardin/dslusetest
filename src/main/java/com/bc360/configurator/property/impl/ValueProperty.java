package com.bc360.configurator.property.impl;

import com.bc360.configurator.property.PersistentPropertyAttribute;
import com.bc360.configurator.property.PropertyAttribute;

public class ValueProperty<T> extends Property<T> 
{
	private static final PropertyAttribute<String> NAME = new PersistentPropertyAttribute<String>("name", String.class);

	public ValueProperty(String name)
	{
		super();
		setAttribute(NAME, name);
	}
}
