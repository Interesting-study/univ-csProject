/*****

  All the resources for this project:
  https://randomnerdtutorials.com/

*****/

// Loading the ESP8266WiFi library and the PubSubClient library
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <Wire.h>
//#include "DHT.h"


// Uncomment one of the lines bellow for whatever DHT sensor type you're using!
//#define DHTTYPE DHT11   // DHT 11
//#define DHTTYPE DHT21   // DHT 21 (AM2301)
//#define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321
#define DS3231_I2C_ADDRESS 104

//DS3231 데이터 핀 연결
// SCL - GPIO 05
// SDA - GPIO 04

// Change the credentials below, so your ESP8266 connects to your router
const char* ssid = "SLOWTH";
const char* password = "023956487";

// Change the variable to your Raspberry Pi IP address, so it connects to your MQTT broker
const char* mqtt_server = "kimdh0508.myq-see.com";

// Initializes the espClient
WiFiClient espClient;
PubSubClient client(espClient);

// Connect an LED to each GPIO of your ESP8266
const int ledGPIO14 = 14; //plug01
const int ledGPIO12 = 12; //plug02
const int ledGPIO13 = 13; //plug03
const int ledGPIO02 = 2; //plug04

// DHT Sensor - GPIO 3 = D9 on ESP-12E NodeMCU board
//const int DHTPin = 3;

// We have 30 amps version sensor connected to A0 pin of arduino
// Replace with your version if necessary

//DS3231 Seonsor - GPIO 05. GPIO 04 on ESP-12E NodeMCU board
byte seconds, minutes, hours, day, date, month, year;
byte reserv_seconds[4], reserv_minutes[4], reserv_hours[4], resev_day[4], reserv_date[4], reserv_month[4], reserv_year[4];
char weekDay[4];
byte tMSB, tLSB;
float temp3231;
String topic_reserv[4] = {"empty", "empty", "empty", "empty"};
String reservStatus[4] = {"off", "off", "off", "off"};

// Initialize DHT sensor.
//DHT dht(DHTPin, DHTTYPE);
/*
  float f = 0.0;
  float h = 0.0;
  float t = 0.0;*/

// Timers auxiliar variables
long now = millis();
long lastMeasure = 0;

// Don't change the function below. This functions connects your ESP8266 to your router
void setup_wifi() {
  delay(10);
  // We start by connecting to a WiFi network
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("WiFi connected - ESP IP address: ");
  Serial.println(WiFi.localIP());
}

// This functions is executed when some device publishes a message to a topic that your ESP8266 is subscribed to
// Change the function below to add logic to your program, so when a device publishes a message to a topic that
// your ESP8266 is subscribed you can actually do something
void callback(String topic, byte* message, unsigned int length) {
  Serial.print("Message arrived on topic: ");
  Serial.print(topic);
  Serial.print(". Message: ");
  String messageStatus;


  for (int i = 0; i < length; i++) {
    Serial.print((char)message[i]);
    messageStatus += (char)message[i];
  }
  Serial.println();

  if (topic.startsWith("esp8266/reservPlug"))
    makeMessageTemp(topic, message, length);
  // Feel free to add more if statements to control more GPIOs with MQTT
  // If a message is received on the topic home/office/esp1/gpio2, you check if the message is either 1 or 0. Turns the ESP GPIO according to the message
  if (topic == "esp8266/plug01") {
    Serial.print("Changing Plug01 to ");
    if (messageStatus == "on") {
      digitalWrite(ledGPIO14, LOW);
      Serial.print("On");
    }
    else if (messageStatus == "off") {
      digitalWrite(ledGPIO14, HIGH);
      Serial.print("Off");
    }
  }
  else if (topic == "esp8266/plug02") {
    Serial.print("Changing Plug02 to ");
    if (messageStatus == "on") {
      digitalWrite(ledGPIO12, LOW);
      Serial.print("On");
    }
    else if (messageStatus == "off") {
      digitalWrite(ledGPIO12, HIGH);
      Serial.print("Off");
    }
  }
  else if (topic == "esp8266/plug03") {
    Serial.print("Changing Plug03 to ");
    if (messageStatus == "on") {
      digitalWrite(ledGPIO13, LOW);
      Serial.print("On");
    }
    else if (messageStatus == "off") {
      digitalWrite(ledGPIO13, HIGH);
      Serial.print("Off");
    }
  }
  else if (topic == "esp8266/plug04") {
    Serial.print("Changing plug04 to ");
    if (messageStatus == "on") {
      digitalWrite(ledGPIO02, LOW);
      Serial.print("On");
    }
    else if (messageStatus == "off") {
      digitalWrite(ledGPIO02, HIGH);
      Serial.print("Off");
    }
  }
  else if (topic == "esp8266/EntirePlug") {
    Serial.print("Changing Entire Plug to ");
    if (messageStatus == "on") {
      digitalWrite(ledGPIO14, LOW);
      delay(100);
      digitalWrite(ledGPIO12, LOW);
      delay(100);
      digitalWrite(ledGPIO13, LOW);
      delay(100);
      digitalWrite(ledGPIO02, LOW);
      Serial.print("On");
    }
    else if (messageStatus == "off") {
      digitalWrite(ledGPIO14, HIGH);
      delay(100);
      digitalWrite(ledGPIO12, HIGH);
      delay(100);
      digitalWrite(ledGPIO13, HIGH);
      delay(100);
      digitalWrite(ledGPIO02, HIGH);
      Serial.print("Off");
    }
  }//구별 + 전체 원격 제어
  Serial.println();
}

void makeMessageTemp(String topic, byte* message, unsigned int length) {
  // put your setup code here, to run once:
  String messageTemp;
  String strTime;
  int toIntTime;
  int wantTime;
  int plug_num = (topic.substring(18)).toInt();
  topic_reserv[plug_num - 1] = topic;
  get3231Date();

  for (int i = 0; i < length; i++) {
    Serial.print((char)message[i]);
    messageTemp += (char)message[i];
  }
  Serial.println();

  if (messageTemp.indexOf("{\"Status\":false", 0) != -1) {
    reservStatus[plug_num - 1].remove(0);
    reservStatus[plug_num - 1] = messageTemp.substring(10, 15);
    strTime = messageTemp.substring(23, length - 1);
    toIntTime = strTime.toInt();
  }
  else {
    reservStatus[plug_num - 1].remove(0);
    reservStatus[plug_num - 1] = messageTemp.substring(10, 14);
    strTime = messageTemp.substring(22, length - 1);
    toIntTime = strTime.toInt();
  }

  wantTime = minutes + toIntTime;
  Serial.println(wantTime);
  if ( wantTime >= 60 ) {
    Serial.print("\t1. 예약시간산출중 : ");

    reserv_seconds[plug_num - 1] = seconds;
    reserv_minutes[plug_num - 1] = wantTime % 60;
    reserv_hours[plug_num - 1] = hours + (wantTime / 60);
    reserv_date[plug_num - 1] = date;
    reserv_month[plug_num - 1] = month;
    reserv_year[plug_num - 1] = year;

    if (reserv_hours[plug_num - 1] >= 24) {
      reserv_date[plug_num - 1] = date + (reserv_hours[plug_num - 1] / 24);
      reserv_hours[plug_num - 1] %= 24;
      switch (month) {
        case 1:
          if (reserv_date[plug_num - 1] >= 32) {
            reserv_date[plug_num - 1] -= 31;
            reserv_month[plug_num - 1] = month + 1;
          }
          break;
        case 3:
          if (reserv_date[plug_num - 1] >= 32) {
            reserv_date[plug_num - 1] -= 31;
            reserv_month[plug_num - 1] = month + 1;
            reserv_year[plug_num - 1] = year + 1;
          }
          break;
        case 5:
          if (reserv_date[plug_num - 1] >= 32) {
            reserv_date[plug_num - 1] -= 31;
            reserv_month[plug_num - 1] = month + 1;
            reserv_year[plug_num - 1] = year + 1;
          }
          break;
        case 7:
          if (reserv_date[plug_num - 1] >= 32) {
            reserv_date[plug_num - 1] -= 31;
            reserv_month[plug_num - 1] = month + 1;
            reserv_year[plug_num - 1] = year + 1;
          }
          break;
        case 8:
          if (reserv_date[plug_num - 1] >= 32) {
            reserv_date[plug_num - 1] -= 31;
            reserv_month[plug_num - 1] = month + 1;
            reserv_year[plug_num - 1] = year + 1;
          }
          break;
        case 10:
          if (reserv_date[plug_num - 1] >= 32) {
            reserv_date[plug_num - 1] -= 31;
            reserv_month[plug_num - 1] = month + 1;
            reserv_year[plug_num - 1] = year + 1;
          }
          break;
        case 12:
          if (reserv_date[plug_num - 1] >= 32) {
            reserv_date[plug_num - 1] -= 31;
            reserv_month[plug_num - 1] = month + 1;
            reserv_year[plug_num - 1] = year + 1;
          }
          break;
        case 4:
          if (reserv_date[plug_num - 1] >= 31) {
            reserv_date[plug_num - 1] -= 30;
            reserv_month[plug_num - 1] = month + 1;
          }
          break;
        case 6:
          if (reserv_date[plug_num - 1] >= 31) {
            reserv_date[plug_num - 1] -= 30;
            reserv_month[plug_num - 1] = month + 1;
          }
          break;
        case 9:
          if (reserv_date[plug_num - 1] >= 31) {
            reserv_date[plug_num - 1] -= 30;
            reserv_month[plug_num - 1] = month + 1;
          }
          break;
        case 11:
          if (reserv_date[plug_num - 1] >= 31) {
            reserv_date[plug_num - 1] -= 30;
            reserv_month[plug_num - 1] = month + 1;
          }
          break;
        case 2 :
          if ( (year % 4 == 0) && (year % 100 != 0) && (year % 400  == 0) && reserv_date[plug_num - 1] >= 30) {
            reserv_date[plug_num - 1] -= 29;
            reserv_month[plug_num - 1] = 3;
          }
          else if (reserv_date[plug_num - 1] >= 29) {
            reserv_date[plug_num - 1] -= 28;
            reserv_month[plug_num - 1] = 3;
          }
          break;
      }
    }
    Serial.print("topic : ");
    Serial.print(topic_reserv[plug_num - 1]);
    Serial.print(", plug num : ");
    Serial.print(plug_num);
    Serial.print(", reserv status : ");
    Serial.print(reservStatus[plug_num - 1]);
    Serial.print(" | ");
    Serial.print(weekDay);
    Serial.print(", 20");
    Serial.print(reserv_year[plug_num - 1], DEC);
    Serial.print("/");
    Serial.print(reserv_month[plug_num - 1], DEC);
    Serial.print("/");
    Serial.print(reserv_date[plug_num - 1], DEC);
    Serial.print(" - ");
    Serial.print(reserv_hours[plug_num - 1], DEC);
    Serial.print(":");
    Serial.print(reserv_minutes[plug_num - 1], DEC);
    Serial.print(":");
    Serial.println(reserv_seconds[plug_num - 1], DEC);
  }
  else {
    reserv_seconds[plug_num - 1] = seconds;
    reserv_minutes[plug_num - 1] = wantTime;
    reserv_hours[plug_num - 1] = hours;
    resev_day[plug_num - 1] = day;
    reserv_date[plug_num - 1] = date;
    reserv_month[plug_num - 1] = month;
    reserv_year[plug_num - 1] = year;

    Serial.print("\t2. 예약시간산출중 : ");
    Serial.print("plug num : ");
    Serial.print(plug_num);
    Serial.print(", reserv status : ");
    Serial.print(reservStatus[plug_num - 1]);
    Serial.print(" | ");
    Serial.print(weekDay);
    Serial.print(", 20");
    Serial.print(reserv_year[plug_num - 1], DEC);
    Serial.print("/");
    Serial.print(reserv_month[plug_num - 1], DEC);
    Serial.print("/");
    Serial.print(reserv_date[plug_num - 1], DEC);
    Serial.print(" - ");
    Serial.print(reserv_hours[plug_num - 1], DEC);
    Serial.print(":");
    Serial.print(reserv_minutes[plug_num - 1], DEC);
    Serial.print(":");
    Serial.println(reserv_seconds[plug_num - 1], DEC);
  }

  Serial.println();

}

// This functions reconnects your ESP8266 to your MQTT broker
// Change the function below if you want to subscribe to more topics with your ESP8266
void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    /*
      YOU  NEED TO CHANGE THIS NEXT LINE, IF YOU'RE HAVING PROBLEMS WITH MQTT MULTIPLE CONNECTIONS
      To change the ESP device ID, you will have to give a unique name to the ESP8266.
      Here's how it looks like now:
      if (client.connect("ESP8266Client")) {
      If you want more devices connected to the MQTT broker, you can do it like this:
      if (client.connect("ESPOffice")) {
      Then, for the other ESP:
      if (client.connect("ESPGarage")) {
      That should solve your MQTT multiple connections problem

      THE SECTION IN loop() function should match your device name
    */
    if (client.connect("ESP8266Client")) {
      Serial.println("connected");
      // Subscribe or resubscribe to a topic
      // You can subscribe to more topics (to control more LEDs in this example)
      client.subscribe("esp8266/plug01");
      client.subscribe("esp8266/plug02");
      client.subscribe("esp8266/plug03");
      client.subscribe("esp8266/plug04");
      client.subscribe("esp8266/EntirePlug");
      client.subscribe("esp8266/reservPlug01");
      client.subscribe("esp8266/reservPlug02");
      client.subscribe("esp8266/reservPlug03");
      client.subscribe("esp8266/reservPlug04");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

// The setup function sets your ESP GPIOs to Outputs, starts the serial communication at a baud rate of 115200
// Sets your mqtt broker and sets the callback function
// The callback function is what receives messages and actually controls the LEDs
void setup() {
  pinMode(ledGPIO14, OUTPUT);
  pinMode(ledGPIO12, OUTPUT);
  pinMode(ledGPIO13, OUTPUT);
  pinMode(ledGPIO02, OUTPUT);

  digitalWrite(ledGPIO14, HIGH);
  digitalWrite(ledGPIO12, HIGH);
  digitalWrite(ledGPIO13, HIGH);
  digitalWrite(ledGPIO02, HIGH);

  //dht.begin();
  Wire.begin();
  Serial.begin(115200);
  setup_wifi();
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
}

// For this project, you don't need to change anything in the loop function.
// Basically it ensures that you ESP is connected to your broker
void loop() {
  if (!client.connected()) {
    reconnect();
  }
  if (!client.loop())
    client.connect("ESP8266Client");
  /*
    YOU  NEED TO CHANGE THIS NEXT LINE, IF YOU'RE HAVING PROBLEMS WITH MQTT MULTIPLE CONNECTIONS
    To change the ESP device ID, you will have to give a unique name to the ESP8266.
    Here's how it looks like now:
    client.connect("ESP8266Client");
    If you want more devices connected to the MQTT broker, you can do it like this:
    client.connect("ESPOffice");
    Then, for the other ESP:
    client.connect("ESPGarage");
    That should solve your MQTT multiple connections problem

    THE SECTION IN recionnect() function should match your device name
  */
  //RTC DS3231 Set up and Print
  static char temperatureTemp[7];
  watchConsole();
  get3231Date();
  temp3231 = get3231Temp();


  now = millis();
  // Publishes new temperature and humidity every 30 seconds
  if (now - lastMeasure > 10000) {
    lastMeasure = now;
    // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
    //h = dht.readHumidity();
    // Read temperature as Celsius (the default)

    // Read temperature as Fahrenheit (isFahrenheit = true)
    //f = dht.readTemperature(true);

    // Check if any reads failed and exit early (to try again).
    if (isnan(temp3231)) {
      Serial.println("Failed to read from Temperature and RTC sensor!");
      return;
    }

    /*
      // Computes temperature values in Celsius
      float hic = dht.computeHeatIndex(t, h, false);
      static char temperatureTemp[7];
      dtostrf(hic, 6, 2, temperatureTemp);

      // Uncomment to compute temperature values in Fahrenheit
      // float hif = dht.computeHeatIndex(f, h);
      // static char temperatureTemp[7];
      // dtostrf(hic, 6, 2, temperatureTemp);

      static char humidityTemp[7];
      dtostrf(h, 6, 2, humidityTemp);*/




    // Publishes Temperature and Humidity values
    dtostrf(temp3231, 6, 2, temperatureTemp);
    client.publish("esp8266/temperature", temperatureTemp);
    //client.publish("esp8266/humidity", humidityTemp);

    // Serial.print("Humidity: ");
    // Serial.print(h);

    Serial.print(" %\t Temperature: ");
    Serial.print(temp3231);
    Serial.println(" *C ");

    Serial.print(weekDay);
    Serial.print(", 20");
    Serial.print(year, DEC);
    Serial.print("/");
    Serial.print(month, DEC);
    Serial.print("/");
    Serial.print(date, DEC);
    Serial.print(" - ");
    Serial.print(hours, DEC);
    Serial.print(":");
    Serial.print(minutes, DEC);
    Serial.print(":");
    Serial.println(seconds, DEC);
    /*
      Serial.print(f);
      Serial.print(" *F\t Heat index: ");
      Serial.print(hic);
      Serial.println(" *C ");
    */
    // Serial.print(hif);
    // Serial.println(" *F");
  }

  if (temp3231 >= 50.0) {
    dtostrf(temp3231, 6, 2, temperatureTemp);
    client.publish("esp8266/temperature", temperatureTemp);

    Serial.print("현재 온도는 ");
    Serial.print(temp3231);
    Serial.println(" *C 입니다. 과열로 인하여 모든 전원을 차단합니다.\n");

    digitalWrite(ledGPIO14, HIGH);
    delay(100);
    digitalWrite(ledGPIO12, HIGH);
    delay(100);
    digitalWrite(ledGPIO13, HIGH);
    delay(100);
    digitalWrite(ledGPIO02, HIGH);
    
    client.publish("esp8266/completed_reserv_01", "off");
    client.publish("esp8266/completed_reserv_02", "off");
    client.publish("esp8266/completed_reserv_03", "off");
    client.publish("esp8266/completed_reserv_04", "off");

    Serial.print("모든 전원이 안전하게 차단되었습니다.\n");
  }//과열 전원 차단 제어

  //예약전원제어
  if (topic_reserv[0] == "esp8266/reservPlug01" && (year == reserv_year[0]) &&
      (month == reserv_month[0]) && (date == reserv_date[0]) && (hours == reserv_hours[0])
      && (minutes == reserv_minutes[0]) && (seconds == reserv_seconds[0])) {
    Serial.print("Changing Reservation Plug01 to ");
    if (reservStatus[0] == "true") {
      digitalWrite(ledGPIO14, LOW);
      client.publish("esp8266/completed_reserv_01", "on");
      Serial.println("On");
      topic_reserv[0].remove(0);
      reservStatus[0].remove(0);
    }
    else if (reservStatus[0] == "false") {
      digitalWrite(ledGPIO14, HIGH);
      client.publish("esp8266/completed_reserv_01", "off");
      Serial.println("Off");
      topic_reserv[0].remove(0);
      reservStatus[0].remove(0);
    }
  }
  else if (topic_reserv[1] == "esp8266/reservPlug02" && (year == reserv_year[1]) &&
           (month == reserv_month[1]) && (date == reserv_date[1]) && (hours == reserv_hours[1])
           && (minutes == reserv_minutes[1]) && (seconds == reserv_seconds[1])) {
    Serial.print("Changing Reservation Plug02 to ");
    if (reservStatus[1] == "true") {
      digitalWrite(ledGPIO12, LOW);
      client.publish("esp8266/completed_reserv_02", "on");
      Serial.println("On");
      topic_reserv[1].remove(0);
      reservStatus[1].remove(0);
    }
    else if (reservStatus[1] == "false") {
      digitalWrite(ledGPIO12, HIGH);
      client.publish("esp8266/completed_reserv_02", "off");
      Serial.println("Off");
      topic_reserv[1].remove(0);
      reservStatus[1].remove(0);
    }
  }
  else if (topic_reserv[2] == "esp8266/reservPlug03" && (year == reserv_year[2]) &&
           (month == reserv_month[2]) && (date == reserv_date[2]) && (hours == reserv_hours[2])
           && (minutes == reserv_minutes[2]) && (seconds == reserv_seconds[2])) {
    Serial.print("Changing Reservation Plug03 to ");
    if (reservStatus[2] == "true") {
      digitalWrite(ledGPIO13, LOW);
      client.publish("esp8266/completed_reserv_03", "on");
      Serial.println("On");
      topic_reserv[2].remove(0);
      reservStatus[2].remove(0);
    }
    else if (reservStatus[2] == "false") {
      digitalWrite(ledGPIO13, HIGH);
      client.publish("esp8266/completed_reserv_03", "off");
      Serial.println("Off");
      topic_reserv[2].remove(0);
      reservStatus[2].remove(0);
    }
  }
  else if (topic_reserv[3] == "esp8266/reservPlug04" && (year == reserv_year[3]) &&
           (month == reserv_month[3]) && (date == reserv_date[3]) && (hours == reserv_hours[3])
           && (minutes == reserv_minutes[3]) && (seconds == reserv_seconds[3])) {
    Serial.print("Changing Reservation Plug01 to ");
    if (reservStatus[3] == "true") {
      digitalWrite(ledGPIO02, LOW);
      client.publish("esp8266/completed_reserv_04", "on");
      Serial.println("On");
      topic_reserv[3].remove(0);
      reservStatus[3].remove(0);
    }
    else if (reservStatus[3] == "false") {
      digitalWrite(ledGPIO02, HIGH);
      client.publish("esp8266/completed_reserv_04", "off");
      Serial.println("Off");
      topic_reserv[3].remove(0);
      reservStatus[3].remove(0);
    }
  }
}

void get3231Date()
{
  // send request to receive data starting at register 0
  Wire.beginTransmission(DS3231_I2C_ADDRESS); // 104 is DS3231 device address
  Wire.write(0x00); // start at register 0
  Wire.endTransmission();
  Wire.requestFrom(DS3231_I2C_ADDRESS, 7); // request seven bytes

  if (Wire.available()) {
    seconds = Wire.read(); // get seconds
    minutes = Wire.read(); // get minutes
    hours   = Wire.read();   // get hours
    day     = Wire.read();
    date    = Wire.read();
    month   = Wire.read(); //temp month
    year    = Wire.read();

    seconds = (((seconds & B11110000) >> 4) * 10 + (seconds & B00001111)); // convert BCD to decimal
    minutes = (((minutes & B11110000) >> 4) * 10 + (minutes & B00001111)); // convert BCD to decimal
    hours   = (((hours & B00110000) >> 4) * 10 + (hours & B00001111)); // convert BCD to decimal (assume 24 hour mode)
    day     = (day & B00000111); // 1-7
    date    = (((date & B00110000) >> 4) * 10 + (date & B00001111)); // 1-31
    month   = (((month & B00010000) >> 4) * 10 + (month & B00001111)); //msb7 is century overflow
    year    = (((year & B11110000) >> 4) * 10 + (year & B00001111));
  }
  else {
    //oh noes, no data!
  }

  switch (day) {
    case 1:
      strcpy(weekDay, "Sun");
      break;
    case 2:
      strcpy(weekDay, "Mon");
      break;
    case 3:
      strcpy(weekDay, "Tue");
      break;
    case 4:
      strcpy(weekDay, "Wed");
      break;
    case 5:
      strcpy(weekDay, "Thu");
      break;
    case 6:
      strcpy(weekDay, "Fri");
      break;
    case 7:
      strcpy(weekDay, "Sat");
      break;
  }
}

float get3231Temp()
{
  //temp registers (11h-12h) get updated automatically every 64s
  Wire.beginTransmission(DS3231_I2C_ADDRESS);
  Wire.write(0x11);
  Wire.endTransmission();
  Wire.requestFrom(DS3231_I2C_ADDRESS, 2);

  if (Wire.available()) {
    tMSB = Wire.read(); //2's complement int portion
    tLSB = Wire.read(); //fraction portion

    temp3231 = (tMSB & B01111111); //do 2's math on Tmsb
    temp3231 += ( (tLSB >> 6) * 0.25 ); //only care about bits 7 & 8
  }
  else {
    //error! no data!
  }
  return temp3231;
}
void watchConsole()
{
  if (Serial.available()) {      // Look for char in serial queue and process if found
    if (Serial.read() == 84) {   //If command = "T" Set Date
      set3231Date();
      get3231Date();
      Serial.println(" ");
    }
  }
}
void set3231Date()
{
  year    = (byte) ((Serial.read() - 48) * 10 +  (Serial.read() - 48));
  month   = (byte) ((Serial.read() - 48) * 10 +  (Serial.read() - 48));
  date    = (byte) ((Serial.read() - 48) * 10 +  (Serial.read() - 48));
  hours   = (byte) ((Serial.read() - 48) * 10 +  (Serial.read() - 48));
  minutes = (byte) ((Serial.read() - 48) * 10 +  (Serial.read() - 48));
  seconds = (byte) ((Serial.read() - 48) * 10 + (Serial.read() - 48));
  day     = (byte) (Serial.read() - 48);

  Wire.beginTransmission(DS3231_I2C_ADDRESS);
  Wire.write(0x00);
  Wire.write(decToBcd(seconds));
  Wire.write(decToBcd(minutes));
  Wire.write(decToBcd(hours));
  Wire.write(decToBcd(day));
  Wire.write(decToBcd(date));
  Wire.write(decToBcd(month));
  Wire.write(decToBcd(year));
  Wire.endTransmission();
}
byte decToBcd(byte val)
{
  return ( (val / 10 * 16) + (val % 10) );
}
