package Controller;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import View.LectureListView;

public class LectureListController {
	
	LectureListView LLV;
	
	public LectureListController() {
		LLV = new LectureListView(true, this);
		SetScore();
	}
	
	//LectureListView를 반환하는 함수.
	public LectureListView showLLV() {
		return LLV;
	}
	
	public TableCell connectTableCell(String text,int flag) {
		return new TableCell(text,flag);
	}
	
	//신청 학점을 설정하는 메소드
	public void SetScore() {
		int sums = 0;
		for(int i = 0 ; i < LLV.getMyLectureTable().getRowCount(); i++) {
			sums += Integer.parseInt((String) LLV.getMyLectureTable().getValueAt(i, 8));
		}
		LLV.setScore(Integer.toString(sums));;
	} //SetScore()
	
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
		 
        JButton jb;
         
        //flag 1은 신청 2는 취소
        public TableCell(String text,int flag) {
            // TODO Auto-generated constructor stub
            jb = new JButton(text);
            jb.addActionListener(e -> {
            	if(flag == 2) {
            		System.out.println("취소 " + LLV.getMyLectureTable().getSelectedRow() + "  " + LLV.getMyLectureTable().getRowCount());
            		if(LLV.getMyLectureTable().getSelectedRow() >= 0 
            				&& LLV.getMyLectureTable().getSelectedRow() < LLV.getMyLectureTable().getRowCount()) {
            			LLV.getMyLectureDTM().removeRow(LLV.getMyLectureTable().getSelectedRow());
            			SetScore();
            		}
            	}
            	else { //신청인 경우
            		System.out.println("신청 " + LLV.getSearchListTable().getSelectedRow() + "  " + LLV.getSearchListTable().getRowCount());
            		if(LLV.getSearchListTable().getSelectedRow() >= 0 
            				&& LLV.getSearchListTable().getSelectedRow() < LLV.getSearchListTable().getRowCount()) {
            			Object news [] = new Object[11];
            			for(int i = 0 ; i < 11; i++)
            				news[i] = LLV.getSearchListDTM().getValueAt(LLV.getSearchListTable().getSelectedRow(), i);
            			LLV.getMyLectureDTM().addRow(news);
            			SetScore();
            		}
            	}
            	
            });
        }
         
        @Override
        public Object getCellEditorValue() {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            // TODO Auto-generated method stub
            return jb;
        }
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            // TODO Auto-generated method stub
            return jb;
        }  
    }
}
