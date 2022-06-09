package com.humber.demo.handler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class MyServiceHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public void close(MessageContext arg0) {

	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext messageContext) {
		String outProperty = SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY;
		boolean outgoing = (Boolean) messageContext.get(outProperty);

		SOAPMessage msg = messageContext.getMessage();

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY.MM.dd.HH.mm.ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			String filename = "SOAP_XML_" + simpleDateFormat.format(timestamp);

			String loggingFile = "C:/Users/Jatinder/sts workspace/Jax-WS-JPA-A1-Jatinder/src/main/java/com/humber/demo/soapxmls/"
					+ filename + ".xml";
			System.out.println(loggingFile);

			msg.writeTo(new FileOutputStream(loggingFile, true));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SOAPException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

}
