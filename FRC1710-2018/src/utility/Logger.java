package utility;

import java.io.File;

import com.opencsv.CSVWriter;

public class Logger {

	CSVWriter _writer;
	
	public Logger(CSVWriter writer, String[] header) {
		_writer = writer;
		addRow(header);
	}
	
	public void addRow(String[] row) {
		_writer.writeNext(row);
	}
	
}
