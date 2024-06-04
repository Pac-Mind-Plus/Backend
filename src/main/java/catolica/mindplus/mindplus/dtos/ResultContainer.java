package catolica.mindplus.mindplus.dtos;

import java.util.List;

public class ResultContainer<T> {
    private T result;
    private List<String> errors;

    public ResultContainer(T result, List<String> errors) {
        this.result = result;
        this.errors = errors;
    }

	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
    
	public void addErrors(String error) {
		this.errors.add(error);
	}
}
