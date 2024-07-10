package Queue.management.system.example.management.system.Exceptions.AdminExceptions;

public class AdminSystemExceptions extends Exception{
    public AdminSystemExceptions(AdminErrMsg adminErrMsg){
        super(adminErrMsg.getMessage());
    }
}
