package Model;

/**
 * Created by D.Hamel on 28.03.17.
 */
public class ExceptionDataBase extends Exception {

    public ExceptionDataBase(String msg) {
        super(msg);
    }

    public String getMsg () {
        return super.getMessage();
    }

}
