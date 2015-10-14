package com.beingjavaguys.services;

import com.beingjavaguys.model.XEventSMSModel;

public interface SmsService {
	
	boolean sendSMS(XEventSMSModel smsModel); 

}
