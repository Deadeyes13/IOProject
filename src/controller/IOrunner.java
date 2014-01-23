/**
 * The runner class for IO Project.
 */
package controller;

/**
 * @author CJ Oman
 * @version 1.0
 * @date Dec 13, 2013 8:19:29 AM
 */
public class IOrunner
{

	/**
	 * Runs the program.
	 */
	public static void main(String[] args)
	{
		IOcontroller appController = new IOcontroller();
		appController.start();
	}

}