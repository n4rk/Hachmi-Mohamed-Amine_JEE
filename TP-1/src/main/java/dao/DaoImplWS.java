package dao;

import org.springframework.stereotype.Component;

@Component("daoWS")
public class DaoImplWS implements IDao{
    @Override
    public double getData() {
        System.out.println("Version Web Service : ");
        return 100;
    }
}
