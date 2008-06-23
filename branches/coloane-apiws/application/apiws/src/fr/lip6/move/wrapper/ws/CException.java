package fr.lip6.move.wrapper.ws;



public class CException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 6129790794662997229L;

    /**
     * Minor Error : Specify Client can also do some actions on Wrapper (no deconnection)
     */
    public static final int LEVEL_LOW = 0;

    /**
     * Major Error : Specify that Wrapper have deconnected the Client, because FrameKit have deconnected the user
     */
    public static final int LEVEL_HIGHT = 1;

    /**
     * Critical Error : Specify that FrameKit break down
     */
    public static final int LEVEL_CRASH = 2;
    
    public static final int COMM_ERROR  = 0;


    /**
     * Error number
     */
    private int error;

    /**
     * Error level
     */
    private int level;

    /**
     * Message Cause of the Error
     */
    private String mess;

    /**
     * Kind of the Error
     */
    private String type;
    
    /**
     * Default constructor
     */
    public CException(){
        super();
    }
    
    public CException(String mess,int error){
        this.mess=mess;
        this.error=error;
        type = "CException";
    }
    
    public void initialize(String data){
        if(data.indexOf("FKException") !=-1 ||data.indexOf("WException")!=-1){
            int nb1 = 0;
            int nb2 = data.indexOf(":", nb1);
            type = data.substring(nb1, nb2);
            nb1=nb2+1;
            nb2 = data.indexOf(":", nb1);
            error = new Integer(data.substring(nb1, nb2)).intValue();
            nb1=nb2+1;
            nb2 = data.indexOf(":", nb1);
            level = new Integer(data.substring(nb1, nb2)).intValue();
            nb1=nb2+1;
            nb2=data.length();
            mess =data.substring(nb1, nb2);
        }
        else{
            mess = "UNKNOW ERROR";
            error = 99;
        }
    }

    /**
     * Constructor of an error
     * @param error Error number
     * @param type Kind of the Error
     * @param mess Cause of the Error
     * @param level Level of the Error
     */
    public CException(int error, String type, String mess, int level) {
        super(type+":"+error+":"+level+":"+mess);
        this.error = error;
        this.type = type;
        this.mess = mess;
        this.level = level;
    }

    /**
     * Getter : Obtain the level Error
     * @return Return the level number
     */
    public int getLevel() {
        return level;
    }

    /**
     * Getter : Obtain the Error number
     * @return Return the Error number
     */
    public int getError() {
        return error;
    }

    /**
     * Getter : Obtain the type of Error
     * @return Return the type of Error
     */
    public String getType() {
        return type;
    }
    
    /**
     * 
     * @return Return the Error Message
     */
    public String getMess(){
        String res ="";
        res+="TYPE : "+ type;
        res+="ERROR :";
        return type+":"+error+":"+level+":"+mess;
    }

    /**
     * @param error the error to set
     */
    public void setError(int error) {
        this.error = error;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @param mess the mess to set
     */
    public void setMess(String mess) {
        this.mess = mess;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}