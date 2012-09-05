/*
 * IRremote: IRsendDemo - demonstrates sending IR codes with IRsend
 * An IR LED must be connected to Arduino PWM pin 3.
 * Version 0.1 July, 2009
 * Copyright 2009 Ken Shirriff
 * http://arcfn.com
 */

#include <IRremote.h>
#include <Max3421e.h>
#include <Usb.h>
#include <AndroidAccessory.h>

#define  LED           12

AndroidAccessory acc("Google, Inc.",
		     "DemoKit",
		     "DemoKit Arduino Board",
		     "1.0",
		     "http://www.android.com",
		     "0000000012345678");

void setup();
void loop();

IRsend irsend;

void setup()
{
  Serial.begin(9600);
}

void loop() {
  byte msg[3];

  if (acc.isConnected()) {
                
    int len = acc.read(msg, sizeof(msg), 1);

    if (len > 0) {

      // msg[0] : Command
      // msg[1] : Target
      // msg[2] : Value
        
      Serial.print("len: ");
      Serial.println(len);
      Serial.print("msg[0]: ");
      Serial.println(msg[0]);
      Serial.print("msg[1]: ");
      Serial.println(msg[1]);
      Serial.print("msg[2]: ");
      Serial.println(msg[2]);

      if (msg[0] == 0x03) {
        int code;
        switch (msg[2]) {
          case 1:
            code = 0x0010;
            break;
          case 2:
            code = 0x0810;
            break;
          case 4:
            code = 0x0c10;
            break;
          case 5:
            code = 0x0210;
            break;
          case 6:
            code = 0x0a10;
            break;
          case 7:
            code = 0x0610;
            break;
          case 8:
            code = 0x0e10;
            break;
        }
        for (int i = 0; i < 3; i++) {
          irsend.sendSony(code, LED); // Sony TV power code
          delay(40);
        }
      }
    }
  }
  delay(10);
}
