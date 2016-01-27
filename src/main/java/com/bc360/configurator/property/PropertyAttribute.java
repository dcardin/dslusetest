package com.bc360.configurator.property;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Fake Enumeration class. The reason why it's not an actual enum is that is is meant to be extended to create new states.
 * 
 * @author Daniel Cardin
 * @author NetAppsID inc.
 */
public abstract class PropertyAttribute<T>
{
	private static Map<String, PropertyAttribute<?>> definitions = new HashMap<String, PropertyAttribute<?>>();

	public static final PropertyAttribute<Boolean> VISIBLE = new PersistentPropertyAttribute<Boolean>("visible", Boolean.class, Boolean.TRUE);
	public static final PropertyAttribute<String> LABEL = new PersistentPropertyAttribute<String>("label", String.class);

	final private String name;
	final private Class<T> type;
	final private Optional<T> defaultValue;

	protected PropertyAttribute(String name, Class<T> type)
	{
		this(name, type, Optional.empty());
	}

	protected PropertyAttribute(String name, Class<T> type, T defaultValue)
	{
		this(name, type, Optional.of(defaultValue));
	}

	protected PropertyAttribute(String name, Class<T> type, Optional<T> defaultValue)
	{
		if (definitions.containsKey(name))
			throw new IllegalArgumentException("PropertyAttribute " + name + " is already defined!");
		
		this.name = name;
		this.type = type;
		this.defaultValue = defaultValue;
		definitions.put(name, this);
	}

	public String getName()
	{
		return name;
	}
	
	public Optional<T> getDefaultValue()
	{
		return defaultValue;
	}

	public String toString()
	{
		return this.name;
	}

	public Class<T> getType()
	{
		return this.type;
	}

	public boolean isPersistent()
	{
		return !isTransient();
	}

	public boolean isTransient()
	{
		return (this instanceof TransientPropertyAttribute);
	}

	public boolean isValueValid(T value)
	{
		return (value == null || value.getClass().isAssignableFrom(getType())); 
	}
	
	protected PropertyAttribute()
	{
		name = null;
		type  = null;
		defaultValue = null;
	}
}
