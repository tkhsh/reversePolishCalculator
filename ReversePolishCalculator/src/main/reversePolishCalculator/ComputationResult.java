package reversePolishCalculator;

public class ComputationResult {
	private int value;
	private boolean isSuccess;
	private String errorMessage;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.isSuccess = true;
		this.value = value;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.isSuccess = false;
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public ComputationResult() {
		this.isSuccess = true;
	}

}
