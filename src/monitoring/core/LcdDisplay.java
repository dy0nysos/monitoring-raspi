package monitoring.core;

import org.apache.commons.lang3.StringUtils;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class LcdDisplay {
	private GpioController gpioController = GpioFactory.getInstance();
	private final GpioPinDigitalOutput LCD_RS = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_11);//BCM 7
	private final GpioPinDigitalOutput LCD_E = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_10);//BCM 8
	private final GpioPinDigitalOutput LCD_D4 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_06);//BCM 25
	private final GpioPinDigitalOutput LCD_D5 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05);//BCM 24
	private final GpioPinDigitalOutput LCD_D6 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04);//BCM 23
	private final GpioPinDigitalOutput LCD_D7 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01);//BCM 18
	public final static Integer LCD_WIDTH = 16;
	public final static Boolean LCD_CHAR = true;
	public final static Boolean LCD_CMD = false;
	public final static Integer LCD_LINE_1 = 0x80;
	public final static Integer LCD_LINE_2= 0xC0;
	public final static Integer E_PULSE = 5;
	public final static Integer E_DELAY = 5;
	
	public LcdDisplay() throws InterruptedException{
		lcdInit();
	}
	
	private void lcdInit() throws InterruptedException{
		lcdByte(0x33, LCD_CMD);
		lcdByte(0x32, LCD_CMD);
		lcdByte(0x06, LCD_CMD);
		lcdByte(0x0C, LCD_CMD);
		lcdByte(0x28, LCD_CMD);
		lcdByte(0x01, LCD_CMD);
		
		Thread.sleep(0L,E_DELAY);
	}
	
	private void lcdByte(Integer bits,Boolean mode) throws InterruptedException{
		LCD_RS.setState(mode);
		LCD_D4.setState(false);
		LCD_D5.setState(false);
		LCD_D6.setState(false);
		LCD_D7.setState(false);
		if ((bits&0x10) == 0x10){
			LCD_D4.setState(true);
		}
		if ((bits&0x20) == 0x20){
			LCD_D5.setState(true);
		}
		if ((bits&0x40) == 0x40){
			LCD_D6.setState(true);
		}
		if ((bits&0x80) == 0x80){
			LCD_D7.setState(true);
		}
		
		lcdToggleEnable();
		
		LCD_D4.setState(false);
		LCD_D5.setState(false);
		LCD_D6.setState(false);
		LCD_D7.setState(false);
		if ((bits&0x01) == 0x01){
			LCD_D4.setState(true);
		}
		if ((bits&0x02) == 0x02){
			LCD_D5.setState(true);
		}
		if ((bits&0x04) == 0x04){
			LCD_D6.setState(true);
		}
		if ((bits&0x08) == 0x08){
			LCD_D7.setState(true);
		}
		
		lcdToggleEnable();
	}
	
	private void lcdToggleEnable() throws InterruptedException{
		Thread.sleep(0L,E_DELAY);
		LCD_E.setState(true);
		Thread.sleep(0L,E_DELAY);
		LCD_E.setState(false);
		Thread.sleep(0L,E_DELAY);
	}
	
	public void lcdString(String message, Integer lcdLine) throws InterruptedException{
		message = StringUtils.rightPad(message, LCD_WIDTH);
		
		lcdByte(lcdLine, LCD_CMD);
		
		for(char c : message.toCharArray()){
			lcdByte((int)c, LCD_CHAR);
		}
		
	}
	
}
