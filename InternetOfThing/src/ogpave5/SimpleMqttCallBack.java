package ogpave5;

import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;

public class SimpleMqttCallBack implements MqttCallback {
    int status = 0;
    double temp;
    double hum;
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String res= new String(mqttMessage.getPayload());
        System.out.println(res);
        JSONObject jo = new JSONObject(res);
        JSONObject jo1 = jo.getJSONObject("AM2301");
        temp = jo1.getDouble("Temperature");
        hum = jo1.getDouble("Humidity");

        System.out.println("Temp: "+temp +" Hum: "+hum);
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // not used in this example
    }

    public double getHum() {
        return hum;
    }
}
