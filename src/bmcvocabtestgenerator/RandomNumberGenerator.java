/*
 * Author: jianqing
 * Date: Jun 13, 2020
 * Description: This document is created for
 */
package bmcvocabtestgenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 *
 * @author jianqing
 */
public class RandomNumberGenerator
{

    public static int[] generateTrueRandomInteger(int amount, int max, int min) throws IOException
    {
        String address = "https://www.random.org/integers/?num="+amount+"&min=" + min + "&max=" + max + "&col=1&base=10&format=plain&rnd=new";
        //System.out.println("REQUEST SENT TO RANDOM.ORG: " + address);
        return readInput1Line(address,amount);
    }
    
//    public static int[] generateTrueRandomIntegerSetNoRepeat(int amount, int max, int min) throws IOException
//    {
//        String address="https://www.random.org/sequences/?min="+min+"&max="+max+"&col=1&format=plain&rnd=new";
//        int[] preset = readInput1Line(address, max);
//        
//    }
    public static int randomInt(int lowerBound, int upperBound)
    {
        return lowerBound + (int) (Math.random() * (upperBound - lowerBound + 1));
    }

    private static int[] readInput1Line(String address, int amount) throws IOException
    {
        URL url = new URL(address);
       // pl.progressUpdated(20);
        URLConnection conn = url.openConnection();
        //pl.progressUpdated(30);
        InputStream stream = conn.getInputStream();
        //pl.progressUpdated(50);
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        //pl.progressUpdated(60);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //pl.progressUpdated(80);
        String output = "";
        String ln;
        int[] intList = new int[amount];
        int counter=0;
        while ((ln = bufferedReader.readLine()) != null)
        {
            intList[counter] = Integer.parseInt(ln);
            counter++;
        }
        //pl.progressUpdated(100);
        return intList;
    }

    public static void main(String[] args) throws IOException
    {
        System.out.println(Arrays.toString(generateTrueRandomInteger(100, 500, 1)));
    }
}
