/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.utility;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

/**
 *
 * @author hp
 */
public class OTPsender implements Sender {

    @Override
    public boolean send(String number, String data) throws Exception {
        Unirest.setTimeouts(0, 0);
        String mobNo = number;
        System.out.println(mobNo+" "+data );
        String url = "https://2factor.in/API/V1//93775d49-77b9-11ee-8cbb-0200cd936042/SMS/" + mobNo + "/" + data + "/OTP1";
        HttpResponse<String> ht = Unirest.get(url).asString();
        String reply = ht.getBody();
        return reply.contains("Success");
    }

}
