package ui;

import model.EventLog;
import model.exception.LogException;

// Source from: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
/**
 * Defines behaviours that event log printers must support.
 */
public interface LogPrinter {
	/**
	 * Prints the log
	 * @param el  the event log to be printed
	 * @throws LogException when printing fails for any reason
	 */
    void printLog(EventLog el) throws LogException;
}
