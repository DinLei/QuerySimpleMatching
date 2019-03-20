package queryMatchingTask.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class ServerResponse implements Serializable {

    private int httpCode;
	private int code;
	private String message;
	private Object data;

	public ServerResponse(int code, Object data) {
	    this.httpCode = StatusCode.toHttpStatus(code);
	    this.code = code;
	    this.message = StatusCode.getMessage(code);
	    this.data = data;
    }

    public ServerResponse(int code) {
        this.httpCode = StatusCode.toHttpStatus(code);
        this.code = code;
        this.message = StatusCode.getMessage(code);
    }

}
