import javax.swing.table.AbstractTableModel;

class FieldTableModel extends AbstractTableModel {
    private String[] columnNames;
    private ClassModel data;

    public FieldTableModel(String[] columnNames, ClassModel classModel)
    {
        this.columnNames = columnNames;
        data = classModel;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex==2)
            return true;
        return false;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.fieldName.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        switch (col){
            case 0:
                return data.fieldName.get(row);
            case 1:
                return data.fieldType.get(row);
            case 2:
                return data.fieldValue.get(row);
            default:
                return "Other";
        }
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
        fireTableDataChanged();
    }

    public void setValueAt(Object value, int row, int col) {
        switch (col) {
            case 0: {
                break;
            }
            case 1:{
                break;
            }
            case 2:{
                data.fieldValue.set(row, Reflection.parse(data.fieldValue.get(row).getClass(), (String)value));
                data.setValue(row, Reflection.parse(data.fieldValue.get(row).getClass(), (String)value));
                break;
            }
        }
        fireTableCellUpdated(row, col);
    }
}