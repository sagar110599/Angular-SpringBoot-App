package com.luv2code.springboot.cruddemo.security.jwt;
import com.razorpay.RazorpayException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.json.JSONObject;
import java.security.SignatureException;
public class RazorPayUtils{
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    public static Order createRazorPayOrder(String amount, RazorpayClient client) throws RazorpayException{

    JSONObject options=new JSONObject();
    
    options.put("amount",amount);
    options.put("currency","INR");
    return client.Orders.create(options);
}
public static void capturePayment(String pid,String amount, RazorpayClient client)throws RazorpayException{

    JSONObject options=new JSONObject();
    
    options.put("amount",amount);
    options.put("currency","INR");
    client.Payments.capture(pid,options);
}


    
    public static String calculateRFC2104HMAC(String data, String mysecret) throws java.security.SignatureException {
        String result;
        try {
 
            // get an hmac_sha256 key from the raw secret bytes
			System.out.println("Inside calculate");
			System.out.println(data+"       "+mysecret);
            SecretKeySpec signingKey = new SecretKeySpec(mysecret.getBytes(), HMAC_SHA256_ALGORITHM);
           System.out.println(signingKey);
            // get an hmac_sha256 Mac instance and initialize with the signing
            // key
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
 
            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes());
 
            // base64-encode the hmac
            result = DatatypeConverter.printHexBinary(rawHmac).toLowerCase();
       System.out.println(result);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }
}