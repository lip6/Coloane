

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class SingleMultiLists {
  Display display = new Display();
  Shell shell = new Shell(display);

  public SingleMultiLists() {
    init();
    
    GridLayout gridLayout = new GridLayout(2, true);
    
    shell.setLayout(gridLayout);
    
    (new Label(shell, SWT.NULL)).setText("SINGLE");
    (new Label(shell, SWT.NULL)).setText("MULTI");
    
    List singleSelectList = new List(shell, SWT.BORDER);
    
    List mutliSelectList = new List(shell, SWT.MULTI | SWT.BORDER);
    
    String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4"};
    
    for(int i=0; i<items.length; i++) {
      singleSelectList.add(items[i]);
      mutliSelectList.add(items[i]);
    }

    shell.pack();
    shell.open();
    //textUser.forceFocus();

    // Set up the event loop.
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        // If no more entries in event queue
        display.sleep();
      }
    }

    display.dispose();
  }

  private void init() {

  }

  public static void main(String[] args) {
    new SingleMultiLists();
  }
}
