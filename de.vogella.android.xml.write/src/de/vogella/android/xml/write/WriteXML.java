package de.vogella.android.xml.write;

import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.widget.TextView;

public class WriteXML extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText(writeXml());

	}

	private String writeXml() {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			// Hard coded stuff
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "people");
			serializer.attribute("", "number", "1");
			serializer.startTag("", "person");
			serializer.attribute("", "date", "20100101");
			serializer.startTag("", "name");
			serializer.text("Jim Knopf");
			serializer.endTag("", "name");
			serializer.startTag("", "url");
			serializer.text("http://www.vogella.de");
			serializer.endTag("", "url");
			serializer.startTag("", "attribute");
			serializer.text("nice guy");
			serializer.endTag("", "attribute");
			serializer.endTag("", "person");
			serializer.endTag("", "people");
			serializer.endDocument();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}
}