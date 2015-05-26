package sd4u.editor;

import javafx.scene.control.ListCell;

// DEGISECEK

/**
 * This class creates HTML Editor in default sizes and updates it according to made changes
 */

public class CustomCell extends ListCell<CustomCellContent>{
	
	/**
     * This method creates HTML Editor in default sizes
     */
	public CustomCell() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * This method updates HTML Editor according to made changes
     */
	@Override
	protected void updateItem(CustomCellContent item, boolean empty) {
		// TODO Auto-generated method stub
		super.updateItem(item, empty);
		if( empty ){
			setText(null);
			setGraphic(null);
		}else{
			setText(item.title);
			setGraphic(null);
		}
	}
}
