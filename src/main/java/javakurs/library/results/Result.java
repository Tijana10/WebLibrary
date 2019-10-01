package javakurs.library.results;

import javax.xml.bind.annotation.XmlRootElement;

import javakurs.libarary.errorhandling.Pair;

@XmlRootElement
public class Result {
	
	private int errorCode;
	
	private String errorMessage;

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
	
	public Result(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public Result(Pair<Integer, String> pair) {
		this(pair.getFirst(), pair.getSecond());

	}
	
	public Result() {
		
	}
	
	

}
