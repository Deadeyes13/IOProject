/**
 * Frame for IO Project.
 */
package view;

import controller.IOcontroller;

import javax.swing.JFrame;

/**
 * @author CJ Oman
 * @version 1.0
 * @date Dec 13, 2013 8:37:36 AM
 */
public class IOframe extends JFrame
{
	/**
	 * IOpanel for the frame.
	 */
	private IOpanel basePanel;
	
	/**
	 * Constructor for IOframe
	 * @param baseController IOcontroller passed in for MVC relationship.
	 */
	public IOframe(IOcontroller baseController)
	{
		basePanel = new IOpanel(baseController);
		
		setupFrame();
	}
	
	/**
	 * Sets up the frame size and loads the contentPane.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setSize(400, 400);
		this.setVisible(true);
	}

}
