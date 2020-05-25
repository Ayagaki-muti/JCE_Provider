package com.No_N_Name.jce.Info;

import com.sun.jna.ptr.PointerByReference;

public class Device_Info {
	//µ¥ÀýÄ£Ê½
	private volatile static Device_Info singleton;  
	private Device_Info (){
		this.Device_pointer = new PointerByReference();
	}  
	public static Device_Info getSingleton() {  
	if (singleton == null) {  
		synchronized (Device_Info.class) {  
			if (singleton == null) {  
				singleton = new Device_Info();
			}
		}
	}
	return singleton;
	}
	
	private PointerByReference Device_pointer;
	public PointerByReference getDevice_pointer() {
		return Device_pointer;
	}
	
	public void setDevice_pointer(PointerByReference device_pointer) {
		Device_pointer = device_pointer;
	}
	
	
}
