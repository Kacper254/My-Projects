#include <Wire.h> 
#include <LiquidCrystal_I2C.h>
#include <Keypad.h>

LiquidCrystal_I2C lcd(0x27,16,2); 

const byte rows = 4;
const byte columns = 4;
char przyciski[rows][columns]={
  {'a','b','c','+'},
  {'d','e','f','-'},
  {'1','T','N','='},
  {'2','0','*','O'}
};

byte PIN_r[rows] = {5,4,3,2};
byte PIN_c[columns] = {8,9,10,11};
int i = 0;
int ktora_kawa = 0;
int next = 0;
int cukier = 0;
int mleko = 0;
int gotowa = 0;

Keypad klawiatura = Keypad( makeKeymap(przyciski), PIN_r, PIN_c, rows, columns);

void setup() {
  lcd.init();
  lcd.init();
  lcd.backlight();
  lcd.setCursor(4,0);
  lcd.print("EKSPRES");
  lcd.setCursor(4,1);
  lcd.print("DO KAWY");
  delay(3000);
  lcd.clear();


}

void loop() {

 
  // Menu programu - opis
  while(i<1){
  lcd.setCursor(1,0);
  lcd.print("Wybierz kawe:");
  delay(2000);
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("a) Cappuccino");
  lcd.setCursor(0,1);
  lcd.print("b) Espresso");
  delay(2000);
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("c) Americano");
  lcd.setCursor(0,1);
  lcd.print("d) Czarna");
  delay(2000);
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("e) Kawa Mokka");
  lcd.setCursor(0,1);
  lcd.print("f) Flat White");
  delay(2000);
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("a)  b)  c)");
  lcd.setCursor(0,1);
  lcd.print("d)  e)  f)");
  gotowa = 0;
  ktora_kawa = 0;
  cukier = 0;
  i = 1;
  }

  // Wybranie opcji 
  char wcisniety = klawiatura.getKey();
  if(wcisniety){
    switch(wcisniety){
      case 'a':{
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("a)");
        delay(500);
        ktora_kawa = 1;
      }break;
      case 'b':{
        lcd.clear();
        lcd.setCursor(4,0);
        lcd.print("b)");
        delay(500);
        ktora_kawa = 2;
      }break;
      case 'c':{
        lcd.clear();
        lcd.setCursor(8,0);
        lcd.print("c)");
        delay(500);
        ktora_kawa = 3;
      }break;
      case 'd':{
        lcd.clear();
        lcd.setCursor(0,1);
        lcd.print("d)");
        delay(500);
        ktora_kawa = 4;
      }break;
      case 'e':{
        lcd.clear();
        lcd.setCursor(4,1);
        lcd.print("e)");
        delay(500);
        ktora_kawa = 5;
      }break;
      case 'f':{
        lcd.clear();
        lcd.setCursor(8,1);
        lcd.print("f)");
        delay(500);
        ktora_kawa = 6;
      }break;
    }
       
      // Wybranie ilości cukru
      if(next == 0){
        if(wcisniety == '+'){
          cukier++;
        }
        if(wcisniety == '-' & cukier > 0){
          cukier--;
        }
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Cukier: ");
        lcd.setCursor(8,0);
        lcd.print(cukier);
        if(wcisniety == 'O'){
          next = 1;
          delay(500);
          lcd.clear();
        }
      }

    //Wybranie opcji z mlekiem lub bez
    if((next == 1 && ktora_kawa == 4) || (next == 1 && ktora_kawa == 5)){
      lcd.setCursor(0,0);
      lcd.print("Z mlekiem?");
      lcd.setCursor(0,1);
      lcd.print("TAK   NIE");
       if(wcisniety == 'T'){
         lcd.clear();
         lcd.setCursor(0,0);
         lcd.print("Z mlekiem?");
         lcd.setCursor(0,1);
         lcd.print("TAK");
         delay(500);
         lcd.clear();
         mleko = 1;
         next = 2;
        }
       if(wcisniety == 'N'){
         lcd.clear();
         lcd.setCursor(0,0);
         lcd.print("Z mlekiem?");
         lcd.setCursor(6,1);
         lcd.print("NIE");
         delay(500);
         lcd.clear();
         mleko = 0;
         next = 2;
        }
      }
       if(next == 1 && ktora_kawa == 1){
        next = 2;
       }
       if(next == 1 && ktora_kawa == 2){
        next = 2;
       }
       if(next == 1 && ktora_kawa == 3){
        next = 2;
       }
       if(next == 1 && ktora_kawa == 6){
        next = 2;
       }
     
   

    //Przygotowanie wybranej kawy
    if(next == 2){
      lcd.setCursor(0,0);
      lcd.print("Poczekaj...");
      delay(1000);
      lcd.clear();
      lcd.setCursor(0,0);
      lcd.print("Miele ziarna");
      delay(2000);
      lcd.clear();
      lcd.setCursor(0,0);
      lcd.print("Grzeje wode");
      delay(5000);
      lcd.clear();
      lcd.setCursor(0,0);
      lcd.print("Parze kawe");
      delay(2000);
      lcd.clear();
      
      if(ktora_kawa == 1){
        
        lcd.setCursor(0,0);
        lcd.print("Spieniam mleko");
        delay(3000);
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Wlewam mleko");
        delay(1500);
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Wlewam kawe");
        delay(1500);
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Spieniam mleko");
        delay(3000);
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Wlewam mleko");
        delay(1500);
        lcd.clear();
        
        while(cukier>=0){
          lcd.setCursor(0,0);
          lcd.print("Dodaje cukier: ");
          lcd.setCursor(15,0);
          lcd.print(cukier);
          cukier--;
          delay(750);
        }
        
        lcd.clear();
        gotowa = 1;
      }
      
      if(ktora_kawa == 2){
        
        lcd.setCursor(0,0);
        lcd.print("Wlewam kawe");
        delay(1500);
        lcd.clear();
        
        while(cukier>=0){
          lcd.setCursor(0,0);
          lcd.print("Dodaje cukier: ");
          lcd.setCursor(15,0);
          lcd.print(cukier);
          cukier--;
          delay(750);
        }
        
        lcd.clear();
        gotowa = 1;
      }
      
      if(ktora_kawa == 3){
        
        lcd.setCursor(0,0);
        lcd.print("Wlewam kawe");
        delay(1500);
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Grzeje mleko");
        delay(5000);
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Wlewam mleko");
        delay(1500);
        lcd.clear();
        
        while(cukier>=0){
          lcd.setCursor(0,0);
          lcd.print("Dodaje cukier: ");
          lcd.setCursor(15,0);
          lcd.print(cukier);
          cukier--;
          delay(750);
        }
        
        lcd.clear();
        gotowa = 1;
      }
      
      if(ktora_kawa == 4){

        lcd.setCursor(0,0);
        lcd.print("Wlewam kawe");
        delay(1500);
        lcd.clear();
        
        if(mleko == 1){
          lcd.setCursor(0,0);
          lcd.print("Wlewam mleko");
          delay(1500);
          lcd.clear();
        }
        
        while(cukier>=0){
          lcd.setCursor(0,0);
          lcd.print("Dodaje cukier: ");
          lcd.setCursor(15,0);
          lcd.print(cukier);
          cukier--;
          delay(750);
        }
        
        lcd.clear();
        gotowa = 1;
      }
      
      if(ktora_kawa == 5){
        
      lcd.setCursor(0,0);
      lcd.print("Grzeje mleko");
      delay(5000);
      lcd.clear();
      lcd.setCursor(0,0);
      lcd.print("Wlewam czekolade");
      delay(1500);
      lcd.clear();
      lcd.setCursor(0,0);
      lcd.print("Wlewam mleko");
      delay(1500);
      lcd.clear();
      lcd.setCursor(0,0);
      lcd.print("Wlewam kawe");
      delay(1500);
      lcd.clear();

        if(mleko == 1){
          lcd.setCursor(0,0);
          lcd.print("Wlewam mleko");
          delay(1500);
          lcd.clear();
        }
        
        while(cukier>=0){
          lcd.setCursor(0,0);
          lcd.print("Dodaje cukier: ");
          lcd.setCursor(15,0);
          lcd.print(cukier);
          cukier--;
          delay(750);
        }
        lcd.clear();
        gotowa = 1;
      }
      
      if(ktora_kawa == 6){
        
        lcd.setCursor(0,0);
        lcd.print("Grzeje mleko");
        delay(5000);
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Wlewam mleko");
        delay(1500);
        lcd.setCursor(0,0);
        lcd.print("Wlewam kawe");
        delay(1500);
        lcd.clear();
        
        while(cukier>=0){
          lcd.setCursor(0,0);
          lcd.print("Dodaje cukier: ");
          lcd.setCursor(15,0);
          lcd.print(cukier);
          cukier--;
          delay(750);
        }
        
        lcd.clear();
        gotowa = 1;
      }
    }


    if(gotowa == 1){
      lcd.setCursor(4,0);
      lcd.print("Gotowa!");
      delay(2000);
      lcd.clear();
      next = 0;
      i = 0;
    }
  }
   
       

}
