package java_step;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTDataPublisher {
    public static void main(String[] args) {
        String brokerUrl = "tcp://broker.hivemq.com:1883"; // URL du serveur MQTT
        String clientId = ""; // ID du client MQTT
        String topic = "sensor/data"; // Sujet MQTT où vous souhaitez publier les données
        String data = "Hello"; // Les données du capteur à publier

        try {
            // Créez un client MQTT
            IMqttClient client = new MqttClient(brokerUrl, clientId);

            // Configuration des options de connexion
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            // Connexion au serveur MQTT
            client.connect(connOpts);

            // Créez un message MQTT avec les données du capteur
            MqttMessage message = new MqttMessage(data.getBytes());
            message.setQos(1); // Qualité de service (QoS)

            // Publiez le message sur le sujet
            client.publish(topic, message);

            // Déconnexion du serveur MQTT
            client.disconnect();

            System.out.println("Données publiées avec succès sur " + topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}