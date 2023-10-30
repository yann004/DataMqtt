# mqtt_app/models.py

from django.db import models

class MQTTData(models.Model):
    timestamp = models.DateTimeField(auto_now_add=True)
    data = models.CharField(max_length=255)
