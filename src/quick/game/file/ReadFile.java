package core;

import java.io.FileReader; 

public class ReadFile {  
    public static String ReadFile(String location)throws Exception{    
          FileReader fr=new FileReader(location);    
          int i;
          String FinalString = "";
          while((i=fr.read())!=-1) {
            FinalString += (char)i;
          }
          fr.close();
          return FinalString;
    }    
}    