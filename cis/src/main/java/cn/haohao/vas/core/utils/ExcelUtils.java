package cn.haohao.vas.core.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jxl.CellType;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.PropertyUtils;

import cn.haohao.vas.core.vo.TableColumn;

public class ExcelUtils {

	// 临时文件根目录
	private static String tempFilePath = "d:/";

	// 临时文件名计数器
	private static int counter = 1;

	public static <T> String listToExcelWithTitle(List<T> datas, List<TableColumn> columns, String relePath,
			String prefixFileName) {
		String fileName = tempFilePath + "/";// 临时文件绝对路径
		if (counter < 10) {
			counter++;
		} else {
			counter = 1;
		}
		relePath = relePath == null ? "" : relePath;
		if (prefixFileName != null) {
			fileName = fileName + relePath + "/" + prefixFileName + "_" + counter + ".xls";
		} else {
			fileName = fileName + relePath + "/" + "temp_file_" + counter + ".xls";
		}
		WritableWorkbook workbook;
		try {

			OutputStream os = new FileOutputStream(fileName);
			workbook = Workbook.createWorkbook(os);// 创建工作簿
			WritableSheet sheet = workbook.createSheet("sheet1", 0); // 添加第一个工作表
			// 填写表格头
			int m = 0;
			for (TableColumn field : columns) {
				modiStrCell(sheet, true, m, 0, field.getColumnTitle(), null);
				m++;
			}
			int i = 0;
			// 填写数据
			for (T data : datas) {
				int j = 0;
				for (TableColumn field : columns) {
					Object pro = PropertyUtils.getProperty(data, field.getColumnId());
					if (pro != null && pro instanceof Date) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						modiStrCell(sheet, true, j, // 列
								1 + i,// 行
								sdf.format(pro), null);
					} else {
						modiStrCell(sheet, true, j, // 列
								1 + i,// 行
								pro == null ? "" : String.valueOf(pro), null);
					}
					j++;
				}
				i++;
			}
			workbook.write();
			workbook.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("文件生成失败!" + e.getMessage());

		}
		return fileName;
	}

	public static void modiStrCell(WritableSheet dataSheet, Boolean bool, int col, int row, String str,
			CellFormat format) throws RowsExceededException, WriteException {
		// 获得单元格对象
		WritableCell cell = dataSheet.getWritableCell(col, row);
		if (str == null) {
			str = "";
		}
		// 判断单元格的类型, 做出相应的转化
		if (cell.getType() == CellType.EMPTY) {
			Label lbl = new Label(col, row, str);
			if (null != format) {
				lbl.setCellFormat(format);
			} else {
				format = (CellFormat) createWcfText(bool, false, false);
				lbl.setCellFormat(format);
			}
			dataSheet.addCell(lbl);
		} else if (cell.getType() == CellType.LABEL) {
			Label lbl = (Label) cell;
			lbl.setString(str);
		} else if (cell.getType() == CellType.NUMBER) {
			// 数字单元格修改
			Number n1 = (Number) cell;
			n1.setValue(42.05);
		}
	}

	/**
	 * 返回正文格式
	 */
	public static WritableCellFormat createWcfText(Boolean bool, Boolean isNum, Boolean isCount) {
		WritableFont wfc_small = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcf_text = null;
		if (isNum) {
			NumberFormat nf = null;
			if (isCount) {
				nf = new NumberFormat("###,##0");
			} else {
				nf = new NumberFormat("###,##0.00");
			}
			wcf_text = new WritableCellFormat(wfc_small, nf);
		} else {
			wcf_text = new WritableCellFormat(wfc_small);
		}
		try {
			wcf_text.setBorder(Border.ALL, BorderLineStyle.THIN);
			if (bool) {
				wcf_text.setAlignment(Alignment.LEFT);
			} else {
				wcf_text.setAlignment(Alignment.CENTRE);
			}
			wcf_text.setVerticalAlignment(VerticalAlignment.CENTRE);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf_text;
	}

	public void setTempFilePath(String tempFilePath) {
		ExcelUtils.tempFilePath = tempFilePath;
	}
}
