package reversePolishCalculator;

public class ComputationResult {
	private int value;
	private boolean isSuccess;
	private String errorMessage;

	public int getValue() {
		return value;
	}

	private void setSuccessResult(int value) {
		this.isSuccess = true;
		this.value = value;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private void setFailureResult(String errorMessage) {
		this.isSuccess = false;
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public ComputationResult(int value) {
		setSuccessResult(value);
	}

	public ComputationResult(String errorMessage) {
		setFailureResult(errorMessage);
	}

}
