package egovframework.dnworks.cmm.synap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

public class SynapSrvDocView 
{
	private static final Pattern LINE_SEPARATOR_REGEX = Pattern.compile("(\r\n|\r|\n|\n\r)");

	/**
	 * request server
	 * @param targetUrl serverURL URL ex) http://{URL}
	 * @param timeoutParam connect timeout (default : 360000)
	 * @param request HttpServletRequest (http://{proxyServerURL}/SynapDocViewServer/job) Request
	 * @param response
	 * @throws Exception
	 */
	public static void synapSrvDocView(String targetUrl, String timeoutParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		synapSrvDocView(targetUrl, request.getRequestURI(), timeoutParam, request, response);
	}
	public static void synapSrvDocView(String targetUrl, String originReqUrl, String timeoutParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (targetUrl.isEmpty()) {
			new NullPointerException("targetUrl is NULL");
		}
		int timeout = 360000;
		if (!timeoutParam.isEmpty()) {
			timeout = Integer.parseInt(timeoutParam);
		}

		String originReqQuery = request.getQueryString();
		StringBuilder requestUrl = new StringBuilder();
		requestUrl.append(targetUrl).append(originReqUrl);
		if (originReqQuery != null) {
			requestUrl.append("?" + originReqQuery);
		}

		URL url = new URL(requestUrl.toString());

		String boundary = "^-----^";
		String LINE_FEED = "\r\n";
		final String hyphens = "--";

		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(timeout);
			con.setReadTimeout(timeout);
			con.setInstanceFollowRedirects(false);

			//request header
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = request.getHeader(headerName);
				
				headerValue = headerValue.replaceAll("\r","").replaceAll("\n","");
				if(headerValue != null) {
					con.setRequestProperty(headerName, headerValue);
				}
			}

			//request cookies
			if (request.getCookies() != null) {
				StringBuilder cookieData = new StringBuilder();
				for (Cookie cookie : request.getCookies()) {
					String cookieStr = String.format("%s=%s; ", cookie.getName(), cookie.getValue());
					cookieData.append(cookieStr);
				}
				con.setRequestProperty("Cookie", cookieData.toString());
			}

			String method = request.getMethod();

			con.setRequestMethod(method);
			if ("POST".equals(method)) {
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setUseCaches(false);

				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				if (request.getContentType().contains("multipart/form-data")) {
					boundary = request.getHeader("Content-Type").split("=")[1];
					boolean isFile = false;
					for (Part part : request.getParts()) {
						wr.writeBytes(hyphens + boundary + LINE_FEED);
						for (String name : part.getHeaderNames()) {
							String isEmptyContentType = part.getHeader("Content-Type");
							if (isEmptyContentType != null && isEmptyContentType.startsWith("application/")) {
								wr.writeBytes(name + ": " + part.getHeader(name) + LINE_FEED);
								isFile = true;
							} else {
								isFile = false;
								wr.writeBytes(name + ": " + part.getHeader(name) + LINE_FEED);
								wr.writeBytes(LINE_FEED);
								wr.writeBytes(SynapSrvDocView.getFormValue(part));
								wr.writeBytes(LINE_FEED);
							}
						}
						if (isFile) {
							wr.writeBytes(LINE_FEED);
							SynapSrvDocView.getBytesFromInputStream(part, wr);
							wr.writeBytes(LINE_FEED);
							isFile = false;
						}
					}
					wr.writeBytes(LINE_FEED);
					wr.writeBytes(hyphens + boundary + hyphens + LINE_FEED);
				} else {
					String body = SynapSrvDocView.getBody(request);
					if (body.length() == 0 && request.getParameterMap().size() > 0) {
						StringBuilder parmasStr = new StringBuilder();
						Enumeration names = request.getParameterNames();
						while (names.hasMoreElements()) {
							String key = (String) names.nextElement();
							String params = String.format("%s=%s&", key, request.getParameter(key));
							parmasStr.append(params);
						}
						body = parmasStr.toString();
					}
					wr.writeBytes(body);
				}

				wr.flush();
				if (wr != null) wr.close();
			}

			// send
			int responseCode = con.getResponseCode();

			response.setStatus(responseCode);

			//response header, cookie
			for (Map.Entry<String, List<String>> entries : con.getHeaderFields().entrySet()) {
				if (entries.getKey() == null) {
					continue;
				} 

				if ("Location".equals(entries.getKey())) {
					URL location = new URL(entries.getValue().get(0));
					String value = LINE_SEPARATOR_REGEX.matcher(location.getFile()).replaceAll("");
					response.addHeader(entries.getKey(), value);
				} else {
					for(String value : entries.getValue()){
						value = LINE_SEPARATOR_REGEX.matcher(value).replaceAll("");
						response.addHeader(entries.getKey(), value);
					}
				}
			}

			//set response body 
			if (con.getInputStream() != null) {
				byte[] buffer = new byte[10240];
				InputStream input = null;
				OutputStream output = null;
				try {
					input = con.getInputStream();
					output = response.getOutputStream();
					for (int length = 0; (length = input.read(buffer)) > 0; ) {
						output.write(buffer, 0, length);
					}
					output.flush();
				} finally {
					if (input != null) input.close();
					if (output != null) output.close();
				}
			}

		} catch (IOException ie) {
			System.out.println("IOException RequestSynapDocView requestSynapDocViewServer");
		} catch (ServletException e) {
			System.out.println("ServletException RequestSynapDocView requestSynapDocViewServer");
		}
	}


	public static String getBody(HttpServletRequest request) throws IOException {
		StringBuilder lines = new StringBuilder();
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			inputStream = request.getInputStream();
			
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.append(line);
			}
		} finally {
			if (bufferedReader != null) bufferedReader.close();
			if (inputStreamReader != null) inputStreamReader.close();
			if (inputStream != null) inputStream.close();
		}
		return lines.toString();
	}

	public static String getFormValue(Part part) throws IOException {
		StringBuilder lines = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		try {
			inputStream = part.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.append(line);
			}
		} finally {
			if (bufferedReader != null) bufferedReader.close();
			if (inputStream != null) inputStream.close();
		}
		return lines.toString();
	}

	public static void getBytesFromInputStream(Part part, DataOutputStream wr) throws IOException {

		InputStream is = null;
		try {
			is = part.getInputStream();
			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = is.read(data, 0, data.length)) != -1) {
				wr.write(data, 0, nRead);
			}
		} catch (IOException ie) {
			ie.printStackTrace();
			new IOException(ie.getMessage());
		} finally {
			if (is != null) is.close();
		}
	}
}
