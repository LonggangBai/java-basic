/*package com.easyway.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*//**
 * 
 * @author Administrator
 *
 *//*
public class DispositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger log = LoggerFactory
			.getLogger(DispositionServlet.class);

	*//**
	 * @see HttpServlet#HttpServlet()
	 *//*
	public DispositionServlet() {
		super();
	}

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String filepath = request.getSession().getServletContext()
				.getRealPath("/")
				+ File.separator + "xx.db";
		File proposeFile = new File(filepath);
		log.debug("下载文件路径：" + proposeFile.getPath());
		long fSize = proposeFile.length();
		// 下载
		response.setContentType("application/x-download");
		String isoFileName = "easyway.db";
		response.setHeader("Accept-Ranges", "bytes");
		response.setHeader("Content-Length", String.valueOf(fSize));
		response.setHeader("Content-Disposition", "attachment; filename="
				+ isoFileName);
		long pos = 0;
		if (null != request.getHeader("Range")) {
			// 断点续传
			response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
			try {
				pos = Long.parseLong(request.getHeader("Range")
						.replaceAll("bytes=", "").replaceAll("-", ""));
			} catch (NumberFormatException e) {
				log.error(request.getHeader("Range") + " is not Number!");
				pos = 0;
			}
		}
		ServletOutputStream out = response.getOutputStream();
		BufferedOutputStream bufferOut = new BufferedOutputStream(out);
		InputStream inputStream = new FileInputStream(proposeFile);
		String contentRange = new StringBuffer("bytes ")
				.append(new Long(pos).toString()).append("-")
				.append(new Long(fSize - 1).toString()).append("/")
				.append(new Long(fSize).toString()).toString();
		response.setHeader("Content-Range", contentRange);
		log.debug("Content-Range", contentRange);
		inputStream.skip(pos);
		byte[] buffer = new byte[5 * 1024];
		int length = 0;
		while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
			bufferOut.write(buffer, 0, length);
		}
		bufferOut.flush();
		bufferOut.close();
		out.close();
		inputStream.close();
	}

}
*/