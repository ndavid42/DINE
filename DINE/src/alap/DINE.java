/* DINE 0.1
 * DevicePictureZipper Is Not an Editor
 * 2018 by ndavid
 */

package alap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;



public class DINE {
	
	public static void main(String[] args) {
		Display display = new Display();
		//Shell shell = new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE));
		Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		
		shell.setSize(834, 463);
		shell.setText("IM");
		shell.setLayout(null);
		
		System.out.println("syspath: " + System.getProperty("java.library.path")); 
	
		//content
		new Content().run(shell);
		
		//show the shell
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();		
	}	
}
