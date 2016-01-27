package com.bc360.configurator.property.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.bc360.configurator.property.PropertyAttribute;

/**
 * This is the base Property container. It is very flexible, allowing the storage of any type of attribute. There is a distinction between transient and
 * persistent attributes. The Maps are handled separately, based on the type of PropertyAttribute being used.
 * 
 * @author Daniel Cardin
 * @author NetAppsID inc. (c) 2016-
 * 
 * @param <T>
 */
public abstract class Property<T> 
{
	private Map<String, Object> atts = new HashMap<String, Object>();
	private transient Map<String, Object> transientAttributes = new HashMap<String, Object>();

	public Property()
	{}

	@SuppressWarnings("unchecked")
	private <U> Optional<U> getValueFromMap(PropertyAttribute<U> attribute, Map<String, Object> map)
	{
		if (map.containsKey(attribute.getName()))
			return Optional.of((U) map.get(attribute.getName()));
		else
			return Optional.empty();
	}

	private <U> boolean isPropertyValueValid(PropertyAttribute<U> attribute, U valueToTest)
	{
		return attribute != null && attribute.isValueValid(valueToTest);
	}

	public <U> void setAttribute(PropertyAttribute<U> attribute, U value)
	{
		if (attribute == null) 
		{
			throw new IllegalArgumentException("Unknown attribute");
		}
		else if (isPropertyValueValid(attribute, value))
		{
			if (attribute.isPersistent())
				atts.put(attribute.getName(), value);
			else
				transientAttributes.put(attribute.getName(), value);
		}
		else
		{
			throw new IllegalArgumentException(String.format("Invalid value %s for property %s, class %s expected", value, attribute.toString(), attribute.getType().getSimpleName()));
		}
	}

	public <U> void removeAttribute(PropertyAttribute<U> attribute)
	{
		if (attribute.isPersistent())
			atts.remove(attribute.getName());
		else
			transientAttributes.remove(attribute.getName());
	}

	public <U> Optional<U> getAttribute(PropertyAttribute<U> attribute)
	{
		if (attribute.isPersistent())
		{
			return getValueFromMap(attribute, atts);
		}
		else
		{
			return getValueFromMap(attribute, transientAttributes);
		}
	}


}
