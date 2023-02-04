package JsonJava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class OneSingleJson {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn=null;
        ArrayList<CumtomerDetails> cumtomerDetailsArrayList =new ArrayList();

        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","admin123");
//        Object of statement class will help us to execute queries
        Statement statement = conn.createStatement();
       // statement.execute("use Business");
        ResultSet resultSet = statement.executeQuery("select * from CustomerInfo where Location ='Asia' and PurchasedDate = curdate();"); //the date here will change every,reset the data base if needed.

//        int i=1;
        while(resultSet.next()){
            CumtomerDetails c = new CumtomerDetails();
            c.setCourseName(resultSet.getString(1));
            c.setPurchaseDate(resultSet.getString(2));
            c.setAmount(resultSet.getInt(3));
            c.setLocation(resultSet.getString(4));
            cumtomerDetailsArrayList.add(c);


        }
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i < cumtomerDetailsArrayList.size(); i++) {
            ObjectMapper o=new ObjectMapper();
            o.writeValue(new File("sdet-essentials"+i+".json"),cumtomerDetailsArrayList.get(i));
            Gson g = new Gson();
            String s = g.toJson(cumtomerDetailsArrayList.get(i));

            jsonArray.add(s);
        }
//      create json String from Java object

//      Json Simple
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        System.out.println(jsonObject.toJSONString());
        String unescapeString = StringEscapeUtils.unescapeJava(jsonObject.toJSONString());
        System.out.println(unescapeString);
        String finalString = unescapeString
                                            .replace("\"{", "{")
                                             .replace("}\"", "}");

        System.out.println(finalString);

        FileWriter fileWriter=new FileWriter("file1.txt");
        fileWriter.write(finalString);
        System.out.println(">> Successfully Copied JSON Object to File...");
        System.out.println("\nJSON Object: "+ jsonObject);
        fileWriter.close();


        conn.close();
    }
}





//    Use the following sql sentence to reset the data base
//
//    CREATE DATABASE Business;
//
//        use Business;
//
//        CREATE TABLE CustomerInfo
//
//        (CourseName varchar(50),
//
//        PurchasedDate date,
//
//        Amount int(50),
//
//        Location varchar(50));
//
//
//
//        INSERT INTO CustomerInfo values("selenium",CURRENT_DATE(),120,'Africa');
//
//        INSERT INTO CustomerInfo values("Protractor",CURRENT_DATE(),45,'Africa');
//
//        INSERT INTO CustomerInfo values("Appium",CURRENT_DATE(),99,'Asia');
//
//        INSERT INTO CustomerInfo values("WebServices",CURRENT_DATE(),21,'Asia');
//
//        INSERT INTO CustomerInfo values("Jmeter",CURRENT_DATE(),76,'Asia');
//
//        INSERT INTO CustomerInfo values("restapi",CURRENT_DATE(),76,'Asia');
//
//
//
//
//        select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';
