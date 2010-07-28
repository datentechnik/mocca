/*
* Copyright 2009 Federal Chancellery Austria and
* Graz University of Technology
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package at.gv.egiz.bku.online.webapp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;


public class TransactionIdResponseWrapperTest {

  @Test
  public void testEncodeRedirectURLfString() {
    
    String sessionId = "nQeu67vFMC+OTCXI67woBlpYgCxn";
    String tidx = "2";
    
    TransactionIdResponseWrapper wrapper = new TransactionIdResponseWrapper(new ServletResponse(), sessionId, tidx);
    
    assertEquals(";jsessionid=nQeu67vFMC+OTCXI67woBlpYgCxn?tidx=2", wrapper.encodeRedirectURL(""));
    assertEquals("ui;jsessionid=nQeu67vFMC+OTCXI67woBlpYgCxn?tidx=2", wrapper.encodeRedirectURL("ui"));
    assertEquals("/mocca/ui;jsessionid=nQeu67vFMC+OTCXI67woBlpYgCxn?param1=test&tidx=2", wrapper.encodeRedirectURL("/mocca/ui?param1=test"));
    
  }

  @Test
  public void testEncodeURLString() {

    String sessionId = "nQeu67vFMC+OTCXI67woBlpYgCxn";
    String tidx = "2";
    
    TransactionIdResponseWrapper wrapper = new TransactionIdResponseWrapper(new ServletResponse(), sessionId, tidx);
    
    assertEquals("?tidx=2", wrapper.encodeURL(""));
    assertEquals("ui?tidx=2", wrapper.encodeURL("ui"));
    assertEquals("/mocca/ui?param1=test&tidx=2", wrapper.encodeURL("/mocca/ui?param1=test"));
    assertEquals("stal?wsdl", wrapper.encodeUrl("stal?wsdl"));

  }

  public class ServletResponse implements HttpServletResponse {

    @Override
    public void addCookie(Cookie cookie) {
    }

    @Override
    public void addDateHeader(String name, long date) {
    }

    @Override
    public void addHeader(String name, String value) {
    }

    @Override
    public void addIntHeader(String name, int value) {
    }

    @Override
    public boolean containsHeader(String name) {
      return false;
    }

    @Override
    public String encodeRedirectURL(String url) {
      return url;
    }

    @Override
    public String encodeRedirectUrl(String url) {
      return url;
    }

    @Override
    public String encodeURL(String url) {
      return url;
    }

    @Override
    public String encodeUrl(String url) {
      return url;
    }

    @Override
    public void sendError(int sc) throws IOException {
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
    }

    @Override
    public void sendRedirect(String location) throws IOException {
    }

    @Override
    public void setDateHeader(String name, long date) {
    }

    @Override
    public void setHeader(String name, String value) {
    }

    @Override
    public void setIntHeader(String name, int value) {
    }

    @Override
    public void setStatus(int sc) {
    }

    @Override
    public void setStatus(int sc, String sm) {
    }

    @Override
    public void flushBuffer() throws IOException {
    }

    @Override
    public int getBufferSize() {
      return 0;
    }

    @Override
    public String getCharacterEncoding() {
      return null;
    }

    @Override
    public String getContentType() {
      return null;
    }

    @Override
    public Locale getLocale() {
      return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
      return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
      return null;
    }

    @Override
    public boolean isCommitted() {
      return false;
    }

    @Override
    public void reset() {
    }

    @Override
    public void resetBuffer() {
    }

    @Override
    public void setBufferSize(int size) {
    }

    @Override
    public void setCharacterEncoding(String charset) {
    }

    @Override
    public void setContentLength(int len) {
    }

    @Override
    public void setContentType(String type) {
    }

    @Override
    public void setLocale(Locale loc) {
    }
    
  }
  
}
