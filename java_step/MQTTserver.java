package java_step;

import org.eclipse.paho.mqtt.server.MqttServer;
import org.eclipse.paho.mqtt.server.MqttServerPersistence;
import org.eclipse.paho.mqtt.server.MqttServerPersistenceFactory;

public class MQTTServer {
    public static void main(String[] args) {
        // Configuration du serveur MQTT
        int port = 1883; // Port MQTT par défaut
        String serverURI = "tcp://0.0.0.0:" + port; // Adresse IP et port du serveur

        try {
            // Créez une instance de MqttServerPersistence
            MqttServerPersistence persistence = new MqttServerPersistence() {
                // Implémentez les méthodes nécessaires de l'interface MqttServerPersistence
                // pour gérer le stockage des messages MQTT.

                // Exemple de méthode à implémenter : 
                @Override
                public void open(String clientId, String serverURI) {
                    // Implémentez l'ouverture de stockage pour le client spécifié.
                }
                // ...
            };

            // Créez et configurez le serveur MQTT
            MqttServer mqttServer = new MqttServer(serverURI, persistence);

            // Démarrez le serveur MQTT
            mqttServer.start();

            System.out.println("Serveur MQTT démarré sur le port " + port);

            // Le serveur MQTT est maintenant en cours d'exécution et peut recevoir et publier des données.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
