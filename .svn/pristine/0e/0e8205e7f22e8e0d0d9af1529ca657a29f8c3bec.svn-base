package egovframework.dnworks.cmm.xss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;

public class XSSMultipartRequestWrapper extends HttpServletRequestWrapper {

	private final Map<String, List<String>> paramMap = new LinkedHashMap<String, List<String>>();
	private final Map<String, FileItem> fileMap = new LinkedHashMap<String, FileItem>();
	private static final int FIRST_VALUE = 0;
	private static final String CHARSET = "UTF-8";
	
	public XSSMultipartRequestWrapper(HttpServletRequest servletRequest) throws IOException {
		super(servletRequest);
		/*
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		try {
			List<FileItem> fileItems = upload.parseRequest(servletRequest);
			convertToMaps(fileItems);
		} catch (Exception e) {
			throw new IOException("Cannot parse underlying request: " + e.toString());
		}
		*/
	}

	@SuppressWarnings("unused")
	private void convertToMaps(List<FileItem> fileItems) throws Exception {
		for (FileItem item : fileItems) {
			if (isFileUploadField(item)) {
				fileMap.put(item.getFieldName(), item);
			} else {
				if (alreadyHasValue(item)) {
					addMultivaluedItem(item);
				} else {
					addSingleValueItem(item);
				}
			}
		}
	}

	private boolean isFileUploadField(FileItem aFileItem) {
		return !aFileItem.isFormField();
	}

	private boolean alreadyHasValue(FileItem aItem) {
		return paramMap.get(aItem.getFieldName()) != null;
	}

	private void addSingleValueItem(FileItem item) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add(stripXSS(item.getString(CHARSET)));
		paramMap.put(item.getFieldName(), list);
	}

	private void addMultivaluedItem(FileItem item)  throws Exception {
		List<String> values = paramMap.get(item.getFieldName());
		values.add(stripXSS(item.getString(CHARSET)));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Enumeration getParameterNames() {
		Set<String> allNames = new LinkedHashSet<String>();
		allNames.addAll(paramMap.keySet());
		allNames.addAll(fileMap.keySet());
		return Collections.enumeration(allNames);
	}

	@Override
	public String[] getParameterValues(String aName) {
		String[] result = null;
		List<String> values = paramMap.get(aName);
		if (values != null) {
			result = values.toArray(new String[values.size()]);
		}
		return result;
	}

	@Override
	public String getParameter(String aName) {
		String result = null;
		List<String> values = paramMap.get(aName);
		if (values == null) {
			// you might try the wrappee, to see if it has a value
		} else if (values.isEmpty()) {
			// param name known, but no values present
			result = "";
		} else {
			// return first value in list
			result = values.get(FIRST_VALUE);
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map getParameterMap() {
		return Collections.unmodifiableMap(paramMap);
	}

	public List<FileItem> getFileItems() {
		return new ArrayList<FileItem>(fileMap.values());
	}

	/**
	 * Return the {@link FileItem} of the given name.
	 * <P>
	 * If the name is unknown, then return <tt>null</tt>.
	 */
	public FileItem getFileItem(String aFieldName) {
		return fileMap.get(aFieldName);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return stripXSS(value);
	}

	private String stripXSS(String value) {
		if (value != null) {
			value = value.replaceAll("", "");

			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			/*
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			 */
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
		}
		return value;
	}
}
