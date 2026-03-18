package multithreading;

/***
 * A java bean class on which threads will work and call wait and notify
 * methods.
 * 
 ***/
public class Message {

	private String msg;

	public Message(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
