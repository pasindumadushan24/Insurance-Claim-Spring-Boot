package lk.ijse.Been;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SpringBeen {

    @Value("$db.name")
    private String db;

    @Value("$db.user")
    private String dbhost;

    @Value("$db.password")
    private String dbport;

    @Value("$db.url")
    private String dbuser;


    public SpringBeen() {
    }
    @Override
    public void afterProperties() throws Exception{
        System.out.println(db);
        System.out.println(dbhost);
        System.out.println(dbport);
        System.out.println(dbuser);
    }

}


