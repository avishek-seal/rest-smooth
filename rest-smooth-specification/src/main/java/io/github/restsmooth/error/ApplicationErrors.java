package io.github.restsmooth.error;

import io.github.restsmooth.error.model.RestSmoothError;
import io.github.restsmooth.format.FormatSupprt;

@FunctionalInterface
public interface ApplicationErrors extends FormatSupprt{
	
	RestSmoothError parseError();
}
