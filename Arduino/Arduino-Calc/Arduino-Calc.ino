#include <Wire.h> 
#include <LiquidCrystal_I2C.h>
#include <Keypad.h>

LiquidCrystal_I2C lcd(0x27,16,2); 
int ilosc_klikniec = 0;
int ktory_wiersz = 0;

int ilosc_klikniec_p = 0;
int ktory_wiersz_p = 0;

int i = 0;

float liczba[2] = {0.0, 0.0};
float wynik = 0.0;

const byte r = 4;
const byte c = 4;
char przyciski[r][c]={
  {'1','2','3','+'},
  {'4','5','6','-'},
  {'7','8','9','='},
  {'p','0','*','/'}
};

char znak_specjalny;


byte PIN_r[r] = {5,4,3,2};
byte PIN_c[c] = {8,9,10,11};

Keypad klawiatura = Keypad( makeKeymap(przyciski), PIN_r, PIN_c, r, c);
char wcisniete_przyciski[16][2];
float wartosci_liczbowe[16][2];



void setup()
{
  lcd.init();
  lcd.init();
  lcd.backlight();
  lcd.setCursor(3,0);
  lcd.print("KALKULATOR");
  lcd.setCursor(5,1);
  lcd.print("By me!");
  delay(3000);
  lcd.clear();

}


void loop()
{
 
  char wcisniety = klawiatura.getKey();
  if(wcisniety){
   lcd.setCursor(ilosc_klikniec,ktory_wiersz);
   lcd.print(wcisniety);
   ilosc_klikniec++;

   ilosc_klikniec_p = ilosc_klikniec;
   ktory_wiersz_p = ktory_wiersz;
   wcisniete_przyciski[ilosc_klikniec][ktory_wiersz] = wcisniety;
    

 // Zamiana charow na float. Znaki liczb staja sie liczbami zmiennoprzecinkowymi.
    switch(wcisniete_przyciski[ilosc_klikniec][ktory_wiersz]){
      case '0':{
        wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 0.0;
      }break;
      case '1':{
        wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 1.0;
      }break;
      case '2':{
         wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 2.0;
      }break;
      case '3':{
         wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 3.0;
      }break;
      case '4':{
         wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 4.0;
      }break;
      case '5':{
         wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 5.0;
      }break;
      case '6':{
         wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 6.0;
      }break;
      case '7':{
         wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 7.0;
      }break;
      case '8':{
         wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 8.0;
      }break;
      case '9':{
         wartosci_liczbowe[ilosc_klikniec][ktory_wiersz] = 9.0;
      }break;
      default:{ 
      }
   }


 // Przechowanie informacji o tym jaka operacja zostala wybrana.
    switch(wcisniete_przyciski[ilosc_klikniec][ktory_wiersz]){
      case '+':{
        znak_specjalny = '+';
      }break;
      case '-':{
        znak_specjalny = '-';
      }break;
      case '*':{
        znak_specjalny = '*';
      }break;
      case '/':{
       znak_specjalny = '/';
      }break;
      case 'p':{
        znak_specjalny = 'p';
      }break;
    }

   
   if(wcisniety == '+' || wcisniety == '-' || wcisniety == '*' || wcisniety == '/' || wcisniety == 'p'){
    ilosc_klikniec = 0;
    ktory_wiersz = 1;
   }

   if(wcisniety != '+' && wcisniety != '-' && wcisniety != '*' && wcisniety != '/' && wcisniety != 'p' && wcisniety != '='){
   liczba[ktory_wiersz_p] = liczba[ktory_wiersz_p]*10.0 + wartosci_liczbowe[ilosc_klikniec_p][ktory_wiersz_p];
   }


// Wykonanie operacji arytmetycznych
   if(znak_specjalny == '+' && wcisniety == '='){
      wynik = liczba[0] + liczba[1];
    }
   if(znak_specjalny == '-' && wcisniety == '='){
      wynik = liczba[0] - liczba[1];
    }
   if(znak_specjalny == '*' && wcisniety == '='){
      wynik = liczba[0] * liczba[1];
    }
   if(znak_specjalny == '/' && wcisniety == '='){
      wynik = liczba[0] / liczba[1];
    }
   if(znak_specjalny == 'p' && wcisniety == '='){
      wynik = pow(liczba[0],1.0/2.0);
    }
   
      if(wcisniety == '='){
      lcd.clear();
      lcd.setCursor(6,1);
      lcd.print("=");
      lcd.setCursor(7,1);
      lcd.print(wynik);
      liczba[0] = 0.0;
      liczba[1] = 0.0;
      ilosc_klikniec = 0;
      ktory_wiersz = 0;
    }
}
}
