#include <18F458.h>
#device ADC=10
#use delay(crystal=40000000)
#define LED_GREEN PIN_C2
#define LED_RED PIN_C1

//#USE RS232 (BAUD = 19200, XMIT = PIN_C7, RCV = PIN_C6, stream = RF)

void main()
{
    int temperature_brut;
    int temperature_max = 30;
    float temperature_celcius;
    
    setup_adc_ports(RA0_RA1_RA3_ANALOG); 
    setup_adc(ADC_CLOCK_DIV_8);
   
   while(TRUE)
   {
      // Read temp on temp sensor
      set_adc_channel(0); 
      delay_ms(10);
      temperature_brut = read_adc();
      printf("%c",temperature_brut);
      
      
      //temperature_celcius = temperature_brut * (5.0 / 1023.0 * 100.0);
      temperature_celcius = (float) temperature_brut * (9.009);
      
      if(temperature_celcius > 30){
      
         //Green led low & Red led high
         output_bit(LED_RED,1);
         output_bit(LED_GREEN,0);
      }
      else{
         //Green led high & Red led low
         output_bit(LED_RED,0);
         output_bit(LED_GREEN,1);
      
      }
      
      
   }
}

