package fr.eni.papeterie.bll;

public class BLLException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BLLException(Exception e) {
        super(e);
    } 

	public BLLException(String message) {
        super(message);
    } 
	
	public BLLException(String message, Exception e) {
        super(message + e);
    }



}
