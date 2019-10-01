package javakurs.libarary.errorhandling;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class LibraryException extends RuntimeException{
	
	private int errorCode;
	
	private String errorMessage;
	
	public LibraryException(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public LibraryException(Pair<Integer, String> pair) {
		this(pair.getFirst(), pair.getSecond());
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Pair<Integer,String> getError(){
		
		return Pair.of(this.errorCode, this.errorMessage);
	}
	
	
	public static Pair<Integer, String> OK = new Pair<Integer, String>(0, "Success.");
	
	
	
	public static Pair<Integer, String> TITLE_INVALID_LENGTH = new Pair<>(101, "Title should be between 0..150 chars");
	
	public static Pair<Integer, String> AUTHOR_INVALID_LENGTH = new Pair<Integer, String>(102, "Invalid length");
	
	public static Pair<Integer, String> INVALID_ISBN = new Pair<Integer, String>(103, "invalid isbn");
	
	public static Pair<Integer, String> DESCRIPTION_INVALID_LENGTH =  new Pair<Integer, String>(104, "invalid length");
	
	public static Pair<Integer, String> CATEGORY_NOT_PRESENT =  new Pair<Integer, String>(105, "No category");

	public static Pair<Integer, String> BOOK_ALREADY_EXISTS = new Pair<Integer, String>(106, "Already exists");

	public static Pair<Integer, String> DATABASE_ERROR = new Pair<Integer, String>(107, "Database error");

	public static Pair<Integer, String> BOOK_ID_DONT_EXIST = new Pair<Integer, String>(108, "Given book id do not exist.");

	public static Pair<Integer, String> TITLE_ALREADY_EXIST = new Pair<Integer, String>(109, "Book with title already exist.");
}
