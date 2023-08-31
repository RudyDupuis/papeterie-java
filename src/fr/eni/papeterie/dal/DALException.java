package fr.eni.papeterie.dal;

public class DALException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DALException(Exception e) {
        super(e);
    }

	public DALException(String message, Exception e) {
        super(message + e);
    }

}

