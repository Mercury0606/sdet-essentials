package JsonJava;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        ArrayList<CumtomerDetails> cumtomerDetailsArrayList =new ArrayList();


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn=null;
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","admin123");
//        Object of statement class will help us to execute queries
        Statement statement = conn.createStatement();
//        statement.execute("use Business");
        ResultSet resultSet = statement.executeQuery("select * from CustomerInfo where Location ='Asia' and PurchasedDate = curdate() ;");

//        int i=1;
        while(resultSet.next()){
            CumtomerDetails c = new CumtomerDetails();
            c.setCourseName(resultSet.getString(1));
            c.setPurchaseDate(resultSet.getString(2));
            c.setAmount(resultSet.getInt(3));
            c.setLocation(resultSet.getString(4));
            cumtomerDetailsArrayList.add(c);
        }

        ObjectMapper o=new ObjectMapper();
        o.writeValue(new File("sdet-essentials.json"),cumtomerDetailsArrayList);


        conn.close();

    }
}
