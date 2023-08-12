package ru.megboyzz.data.core.parsers;

public enum CommandStatus {
    /** command terminated successfully */
    SUCCESS,
    /** command terminated but did not succeed */
    FAILED,
    /** command did not terminate within specified time */
    TIMED_OUT,
    /** command threw exception and terminated abnormally */
    EXCEPTION
}
