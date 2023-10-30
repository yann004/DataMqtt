# mqtt_app/views.py

from rest_framework import generics
from django.views.generic.list import ListView
from .models import MQTTData
from .serializers import MQTTDataSerializer

class MQTTDataList(generics.ListCreateAPIView):
    queryset = MQTTData.objects.all()
    serializer_class = MQTTDataSerializer

class MQTTDataListView(ListView):
    model = MQTTData
    template_name = 'myApp_app/data.html'
    context_object_name = 'mqttdata_list'