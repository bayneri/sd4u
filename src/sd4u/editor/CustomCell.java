package sd4u.editor;

import javafx.scene.control.ListCell;

/**
 * This method creates aspect of elements of the list
 * @author H. Cetiner & Y.H. Kalayci
 *
 */
public class CustomCell extends ListCell<CustomCellContent>{
	
	public CustomCell() {
		// TODO Auto-generated constructor stub
	}
	
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
