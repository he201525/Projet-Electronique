#include <18F458.h>
#device ADC=10
#use delay(crystal=20MHz)
#use rs232(UART1,baud=9600,parity=N,xmit=PIN_C6,rcv=PIN_C7,bits=8,stream=COM2)
