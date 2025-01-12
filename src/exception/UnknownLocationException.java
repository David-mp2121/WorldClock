package exception;

public class UnknownLocationException extends Exception {

	String regiao;
	public UnknownLocationException(String regiao) {
	this.regiao = regiao;
	}
public String getRegiao() {
	return regiao;
}
}
