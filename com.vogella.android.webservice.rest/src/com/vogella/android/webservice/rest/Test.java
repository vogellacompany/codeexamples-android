package com.vogella.android.webservice.rest;

public class Test {
	public static void main(String[] args) {
		RestClient client = new RestClient(LOGIN_URL);
		client.AddParam("accountType", "GOOGLE");
		client.AddParam("source", "tboda-widgalytics-0.1");
		client.AddParam("Email", _username);
		client.AddParam("Passwd", _password);
		client.AddParam("service", "analytics");
		client.AddHeader("GData-Version", "2");

		try {
			client.Execute(RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String response = client.getResponse();
	}
}
