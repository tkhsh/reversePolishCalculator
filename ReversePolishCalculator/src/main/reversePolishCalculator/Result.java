package reversePolishCalculator;

public class Result {
	private int value;
	private boolean isSuccess;
	private String errorMessage;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public Result(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
