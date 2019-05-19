#include <main.h>
#include <stdlib.h>

//CONVERTISSEUR
#define CONVERTION 0.48875855327

//AFFICHEURS 7SEGMENTS
#define DIZAINES PIN_E0
#define UNITE PIN_E1

//LEDS
#define LEDGREEN PIN_C0
#define LEDRED PIN_C1


   int maxTemp = 15;
   int affUnite;
   int affDizaines;
   
void convert(int temp) {

   affUnite = temp % 10;
   affDizaines = (temp / 10) % 10;
   
   //AFFICHAGE DES DIZAINES
   output_d(affDizaines);
   output_bit(DIZAINES,1);
   output_bit(UNITE,0);
   delay_ms(2);

   //AFFICHAGE DES UNITES
   output_d(affUnite);
   output_bit(DIZAINES,0);
   output_bit(UNITE,1);
   delay_ms(2);
}


void activeLed(int temp) {

   //PUT GREEN HIGH  
   if (temp >= maxTemp) {
      output_bit(LEDGREEN, 0);
      output_bit(LEDRED, 1);
   } 
   //PUT RED HIGH
   else {
      output_bit(LEDGREEN, 1);
      output_bit(LEDRED, 0);
   }
}

void main()
{
   
   setup_adc_ports(AN0);
   set_adc_channel(0); 
   setup_adc(ADC_CLOCK_INTERNAL);
   
   setup_low_volt_detect(FALSE);
   enable_interrupts(GLOBAL);

   int temp;

   delay_ms(10);

   while(TRUE) {
      
      /*if(kbhit()){
         maxTemp = (int) getc();
      }*/
      
      temp = read_adc() * CONVERTION /5;

      activeLed(temp);
      convert(temp);

   }
}
