package in.co.bus.ticket.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import in.co.bus.ticket.bean.DropdownListBean;


/**
 * HTML Utility class to produce HTML contents like Dropdown List.
 *
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 *
 */
public class HTMLUtility {
	/**
	 * Create HTML SELECT list from MAP paramters values
	 *
	 * @param name
	 * @param selectedVal
	 * @param map
	 * @return
	 */

	public static String getList(final String name, final String selectedVal,final HashMap<String, String> map) {
		
		/*Map<String, String> treeMap = new LinkedHashMap<String, String>(map);*/
	
		final StringBuffer sb = new StringBuffer(	"<select class='form-control' name='" + name + "'>");

		final Set<String> keys = map.keySet();
		String val = null;
		final String select = "-----Select-----";
		sb.append("<option selected value='" + select + "'>" + select+ "</option>");
		for (final String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {

				sb.append("<option selected value='" + key + "'>" + val
						+ "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	/**
	 * Create HTML SELECT List from List parameter
	 *
	 * @param name
	 * @param selectedVal
	 * @param list
	 * @return
	 */
	public static String getList(final String name, final String selectedVal, final List<DropdownListBean> list) {
		
		 

		final List<DropdownListBean> dd = (List<DropdownListBean>) list;

		final StringBuffer sb = new StringBuffer("<select class='form-control' name='" + name + "' id='" + name+ "'>");
		
		String key = null;
		
		String val = null;
		
		final String select = "-----Select-----";
		
		sb.append("<option selected value='" + select + "'>" + select+ "</option>");
		
		for (final DropdownListBean obj : dd) {
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val+ "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}

		sb.append("</select>");
		return sb.toString();
	}
}
