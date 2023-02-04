package JsonJava;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ExtractJson {
    public static void main(String[] args) throws IOException {
        ObjectMapper o =new ObjectMapper();

        CustomDetailAppium c = o.readValue(new File("sdet-essentials0.json"), CustomDetailAppium.class);
        System.out.println(c.getStudentName());


//        vaidate records in web table are correct or not



    }
}
