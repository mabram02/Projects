#include <LiquidCrystal.h>

LiquidCrystal lcd(8, 9, 10, 11, 12, 13);

//Pin labels
const int interruptPin = 2;

//Global variables
int breakCount = 0;
unsigned long startTime = 0;
unsigned long endTime = 0;
int rpm = 0;
int N = 0;
int Pout = 0;
int Pin = 0;
int nin = rpm;
int tin = 0;
int tout = 0;
int nout = 0;
int Gr = 8;
int Nmotor = 0;
int Pinmotor = 0;
int Nn = 0;

// Display Setting
int displaySetting = 1;
const int MAX_DISPLAYS = 3;
const int buttonPin = 3;
unsigned long lastDisplaySwitch = millis();
const int DISPLAY_DELAY = 250;

void setup() {
  // put your setup code here, to run once:
  lcd.begin(16, 2);
  lcd.display();

  pinMode(2, INPUT);
  attachInterrupt(digitalPinToInterrupt(interruptPin), broken, FALLING);

  pinMode(buttonPin, INPUT);
  attachInterrupt(digitalPinToInterrupt(buttonPin), changeDisplaySetting, FALLING);

  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  delay(1000);
  displayLCD();

  if (breakCount >= 30){
    endTime = millis(); 
    rpm = (float(breakCount))/ (float(endTime)-float(startTime)) * 60000;

    Serial.print(rpm);
    Serial.println("RPM");
    breakCount = 0;
  }

  nout = float(Gr) * float(nin);
  tout = (float(nin) - 12800) / (-40);
  tin = (float(tout) * float(nout)) / (float(nin));
  Pin = (float(tin) * float(nin)) / 9549;
  if (tout <= 48){
   Nmotor = (-0.001618 * float(tout^2)) + (0.363542 * float(tout)) + 49.376500;
  }
  else (tout > 48); {
    Nmotor = float(-0.000434*(tout^2) - 0.072269*(tout) + 67.566800);
  }
  Pinmotor = float(-0.000428*(tout^2) +0.136943*(tout) + 0.000739);
  Pout = float(Nmotor) * float(Pinmotor);
  N = float(Pout) / float(Pin);
  Nn = N*100;
}

void broken() {
  if (breakCount == 0) {
    startTime = millis();
  }
  breakCount++;
  Serial.print(breakCount);
  Serial.println("BC");
}

void displayLCD(){
  switch(displaySetting){
    case 1:
      lcd.clear();
      lcd.print(rpm);
      break;
     case 2:
      lcd.clear();
      lcd.print(Pout);
      break;
     case 3:
      lcd.clear();
      lcd.print(Nn,"%");
      break;
     default:
      lcd.clear();
      lcd.print("Unknown Setting!");
  }
}

void changeDisplaySetting(){
  if(lastDisplaySwitch + DISPLAY_DELAY < millis()) {
    lastDisplaySwitch = millis();
    Serial.println("Switching display");
    displaySetting++;
    if(displaySetting > MAX_DISPLAYS) {
      displaySetting = 1;
    }
    displayLCD();
  }
}
