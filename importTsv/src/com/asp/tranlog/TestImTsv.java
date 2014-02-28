package com.asp.tranlog;

import java.util.ArrayList;

import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.util.Bytes;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class TestImTsv {
//	private final byte[][] families;
//	private final byte[][] qualifiers;
//	private final byte[] colType; // 0- String, 1- int, 2- long, 3- double,
//									// 4- timestamp
//	private int[] keyColIndex = null;// Save the columns that will make up
//										// the row key.
//	private int[] keyColLen = null;// Save the length of ever column that
//									// will compose the row key.

//	private final byte separatorByte;
//	public static void main(String[] args) {
//		String columnsSpecification = "COLUMNS=f:ZKHPID0:String,f:ZKFPBZ0:String,f:ZKJYBZ0:String,f:ZKYSZLB:String";
//		String[] keyColumns ={"0","1",};
//		String separatorStr =",";
//		byte[] separator = Bytes.toBytes(separatorStr);
//		Preconditions.checkArgument(separator.length == 1,
//				"TsvParser only supports single-byte separators");
////		separatorByte = separator[0];
//
//		// Configure columns
//		ArrayList<String> columnStrings = Lists.newArrayList(Splitter
//				.on(',').trimResults().split(columnsSpecification));
//
////		families = new byte[columnStrings.size()][];
////		qualifiers = new byte[columnStrings.size()][];
////		colType = new byte[columnStrings.size()];
//
//		for (int i = 0; i < columnStrings.size(); i++) {
//			String str = columnStrings.get(i);
//			// if (ROWKEY_COLUMN_SPEC.equals(str)) {
//			// rowKeyColumnIndex = i;
//			// continue;
//			// }
//			String[] parts = str.split(":", 3);
//			if (parts.length == 1) {
////				families[i] = str.getBytes();
////				qualifiers[i] = HConstants.EMPTY_BYTE_ARRAY;
////				colType[i] = COL_TYPE_STRING;
//			} else {
////				families[i] = parts[0].getBytes();
////				qualifiers[i] = parts[1].getBytes();
//				if (parts.length > 2) {
////					colType[i] = parseColType(parts[2]);
//				} else{
//					
//				}
////					colType[i] = COL_TYPE_STRING;
//			}
//			// System.out.println(str + ", idex " + i + ", coltpe: " +
//			// colType[i]);
//		}
//		if (keyColumns != null) {
////			keyColIndex = new int[keyColumns.length];
////			keyColLen = new int[keyColumns.length];
//			for (int i = 0; i < keyColumns.length; i++) {
//				String[] strKdef = keyColumns[i].split(":", 2);
////				keyColIndex[i] = Integer.parseInt(strKdef[0]);
////				if (keyColIndex[i] >= qualifiers.length)
////					keyColIndex[i] = 0;
////				if (strKdef.length > 1)
////					keyColLen[i] = Integer.parseInt(strKdef[1]);
////				else
////					keyColLen[i] = 0;// 0 means not specify the length
//			}
//		}
//		if (keyColumns != null) {
//			keyColIndex = new int[keyColumns.length];
//			keyColLen = new int[keyColumns.length];
//			for (int i = 0; i < keyColumns.length; i++) {
//				String[] strKdef = keyColumns[i].split(":", 2);
//				keyColIndex[i] = Integer.parseInt(strKdef[0]);
//				if (keyColIndex[i] >= qualifiers.length)
//					keyColIndex[i] = 0;
//				if (strKdef.length > 1)
//					keyColLen[i] = Integer.parseInt(strKdef[1]);
//				else
//					keyColLen[i] = 0;// 0 means not specify the length
//			}
//		}
//	}
}
