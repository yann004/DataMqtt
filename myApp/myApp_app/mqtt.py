# myApp_app/mqtt.py

#import os
#from django.conf import settings
#from django.core.wsgi import get_wsgi_application

import paho.mqtt.client as mqtt

from myApp import settings 

def on_connect(client, userdata, flags, rc):
    
    client.subscribe(settings.MQTT_TOPIC)

def on_message(client, userdata, msg):
    # Traitez les données reçues depuis le serveur MQTT
    data = msg.payload.decode('utf-8')
    # Enregistrez les données dans votre base de données Django ou dans un modèle
    # Vous pouvez également utiliser Django Signals pour déclencher une mise à jour de l'API

client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message
client.connect(settings.MQTT_BROKER_HOST, settings.MQTT_BROKER_PORT, 60)
client.loop_start()
