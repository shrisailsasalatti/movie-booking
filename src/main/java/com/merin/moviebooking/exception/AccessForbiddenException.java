package com.merin.moviebooking.exception;

@SuppressWarnings("serial")
public class AccessForbiddenException extends Exception
{
	
	public String message;
	
	public AccessForbiddenException(String message) 
	{
		super(message);
	}

	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}
}
