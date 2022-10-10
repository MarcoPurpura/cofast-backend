package constants.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
public class RuoliUtente {
    private RuoliUtente()
    {
        //Static class
    }
    public final static String APP_NAME="cofast:";
    public final static String  SUPER_ADMIN=APP_NAME+"super-admin";
    public final static String  CLIENT =APP_NAME+"client";
    public final static String  ADMIN_CLIENT=APP_NAME+"admin-client";
}
