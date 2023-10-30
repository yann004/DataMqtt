#include <WiFi.h>
#include <PubSubClient.h>
#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <DHT_U.h>

// Remplacez ces valeurs par vos informations de réseau WiFi et MQTT
const char* ssid = "NomDuReseauWiFi";
const char* password = "MotDePasseWiFi";
const char* mqtt_server = "AdresseServeurMQTT";
const int mqtt_port = 1883;
const char* mqtt_user = "NomUtilisateurMQTT";
const char* mqtt_password = "MotDePasseMQTT";
const char* mqtt_topic = "sensor_data";

// Configuration du capteur DHT22
#define DHTPIN 2  // Broche de données du DHT22 (GPIO 2)
#define DHTTYPE DHT22

WiFiClient espClient;
PubSubClient client(espClient);
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }

  client.setServer(mqtt_server, mqtt_port);
  client.setCallback(callback);
  dht.begin();
}

void callback(char* topic, byte* payload, unsigned int length) {
  // Gérer les messages MQTT reçus, si nécessaire
}

void reconnect() {
  while (!client.connected()) {
    Serial.print("Connecting to MQTT...");
    if (client.connect("ESP32Client", mqtt_user, mqtt_password)) {
      Serial.println("connected");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      delay(5000);
    }
  }
}

void loop() {
  if (!client.connected()) {
    reconnect();
  }

  client.loop();

  // Lire les données du capteur DHT22
  float humidity = dht.readHumidity();
  float temperature = dht.readTemperature();

  if (isnan(humidity) || isnan(temperature)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Convertir les données en chaînes de caractères
  String payload = String(temperature) + "," + String(humidity);

  // Publier les données sur le serveur MQTT
  client.publish(mqtt_topic, payload.c_str());

  Serial.print("Published data: ");
  Serial.println(payload);

  delay(5000);  // Envoyer les données toutes les 5 secondes
}
