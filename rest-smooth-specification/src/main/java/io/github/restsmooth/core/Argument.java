package io.github.restsmooth.core;

import java.io.Serializable;
import java.lang.annotation.Annotation;

public class Argument implements Serializable{

	private static final long serialVersionUID = -7369936305231757657L;
	
	private Annotation annotation;
	
	private final Class<?> type;

	public Argument(Class<?> type) {
		super();
		this.type = type;
	}

	public Annotation getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

	public Class<?> getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Argument other = (Argument) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
