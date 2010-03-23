package sneps;

/**
 * A special type of Exception class.
 * 
 * @author Amr Khaled Dawood 
 */
public class CustomException extends Exception
{
	
	private static final long serialVersionUID = 6366822584036241415L;

	/**
	 * @param message the message that the CustomException saves for reporting the exception
	 */
	public CustomException(String message)
	{
		super(message);
	}

}
