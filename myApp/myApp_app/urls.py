# myApp_app/urls.py

from django.urls import path
from . import views


urlpatterns = [
    path('mqtt-data/', views.MQTTDataListView.as_view(), name='mqttdata-list'),
]
urlpatterns = [
    path('api/mqtt-data/', views.MQTTDataList.as_view(), name='mqttdata-list'),
]
