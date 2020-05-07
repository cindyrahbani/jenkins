package murex.dev.mxem.Environments.exception;

public class RoleNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public RoleNotFoundException()
    {
        super("Error : Role Not Found in Role repository");
    }
}
