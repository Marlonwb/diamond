package person.marlon.diamond.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CsvUtil {

	private static final Logger logger = LoggerFactory.getLogger(CsvUtil.class);

	/**
	 * 导出csv文件
	 *
	 * @param headers    内容标题
	 *                   注意：headers类型是LinkedHashMap，保证遍历输出顺序和添加顺序一致。
	 *                   而HashMap的话不保证添加数据的顺序和遍历出来的数据顺序一致，这样会出现不搭的情况
	 * @param exportData 要导出的数据集合
	 * @return
	 */
	public static byte[] exportCSV(LinkedHashMap<String, String> headers, List<LinkedHashMap<String, Object>> exportData) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter buffCvsWriter = null;

		try {
			buffCvsWriter = new BufferedWriter(new OutputStreamWriter(baos, StandardCharsets.UTF_8));
			//写入bom头信息，保证excel读取时按照UTF-8格式(即UTF-8 BOM 格式)
			buffCvsWriter.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
			// 写入cvs文件的头部
			Map.Entry propertyEntry = null;
			for (Iterator<Map.Entry<String, String>> propertyIterator = headers.entrySet().iterator(); propertyIterator.hasNext(); ) {
				propertyEntry = propertyIterator.next();
				buffCvsWriter.write("" + propertyEntry.getValue().toString() + "");
				if (propertyIterator.hasNext()) {
					buffCvsWriter.write(",");
				}
			}
			buffCvsWriter.newLine();
			// 写入文件内容
			LinkedHashMap<String, Object> row = null;
			for (Iterator<LinkedHashMap<String, Object>> iterator = exportData.iterator(); iterator.hasNext(); ) {
				row = iterator.next();
				for (Iterator<Map.Entry<String, Object>> propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
					propertyEntry = propertyIterator.next();
					buffCvsWriter.write("" + propertyEntry.getValue().toString() + "");
					if (propertyIterator.hasNext()) {
						buffCvsWriter.write(",");
					}
				}
				if (iterator.hasNext()) {
					buffCvsWriter.newLine();
				}
			}
			// 刷新缓冲区
			buffCvsWriter.flush();
		} catch (IOException e) {
			logger.error("write csv file error --> {}", e);
		} finally {
			// 释放资源
			if (buffCvsWriter != null) {
				try {
					buffCvsWriter.close();
				} catch (IOException e) {
					logger.error("close csv file error --> {}", e);
				}
			}
		}
		return baos.toByteArray();
	}
}