package edu.ncsu.csc316.dsa.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * StudentReader processes input CSV files that contain
 * student information.
 * 
 * Input CSV files should be in the following format:
 * 
 *    FIRST_NAME,LAST_NAME,UNITY_ID,STUDENT_ID,GPA,CREDIT_HOURS
 * 
 * @author Dr. King
 *
 */
public class StudentReader {

	/**
	 * Returns the input CSV file as an array of Student objects
	 * @param filePath - the path to the input CSV file
	 * @return an array of Student objects
	 */
	public static Student[] readInputAsArray(String filePath)
	{
		Student[] list = new Student[10];
		try(Scanner scan = new Scanner(new FileInputStream(filePath), "UTF8"))
		{
			scan.nextLine(); // SKIP HEADER LINE
			int index = 0;
			while(scan.hasNextLine())
			{
				if(index >= list.length)
				{
					list = Arrays.copyOf(list, list.length * 2 + 1);
				}
				list[index] = processLine(scan.nextLine());
				index++;
			}
			list = Arrays.copyOf(list, index);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found: " + e.getMessage());
		}
		return list;
	}

	/**
	 * Processes a single line from the input file to construct a Student.
	 * @param line - the input line from the input file
	 * @return a Student representation of the input line
	 */
	private static Student processLine(String line) {
		Scanner scnr = new Scanner(line);
		scnr.useDelimiter(",");
		String first = scnr.next();
		String last = scnr.next();
		String unityID = scnr.next();
		int studentID = scnr.nextInt();
		double gpa = scnr.nextDouble();
		int creditHours = scnr.nextInt();
		scnr.close();
		Student s = new Student(first, last, studentID, creditHours, gpa, unityID);
		return s;
	}
}
