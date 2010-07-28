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

/**
 * 
 */
package at.gv.egiz.bku.online.webapp;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class TransactionIdResponseWrapper extends HttpServletResponseWrapper {
  
  private String sessionId;
  
  private String tidx;

  public TransactionIdResponseWrapper(HttpServletResponse response, String sessionId, String tidx) {
    super(response);
    this.sessionId = sessionId;
    this.tidx = tidx;
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServletResponseWrapper#encodeRedirectURL(java.lang.String)
   */
  @Override
  public String encodeRedirectURL(String url) {
    // ensure jsessionid and tidx parameters
    String encodedUrl = super.encodeRedirectUrl(url);
    int i = encodedUrl.indexOf('?');
    StringBuilder u = new StringBuilder();
    if (i > 0) {
      u.append(encodedUrl.substring(0, i));
    } else {
      u.append(encodedUrl);
    }
    if (!encodedUrl.contains(";jsessionid=")) {
      u.append(";jsessionid=");
      u.append(sessionId);
    }
    if (i < 0) {
      u.append('?');
    } else if (i < encodedUrl.length() - 1) {
      u.append(encodedUrl.substring(i));
      u.append('&');
    }
    u.append("tidx=");
    u.append(tidx);
    
    return u.toString();
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServletResponseWrapper#encodeURL(java.lang.String)
   */
  @Override
  public String encodeURL(String url) {
    // ensure tidx parameter
    String encodedUrl = super.encodeUrl(url);
    if (url.endsWith("?wsdl")) {
      // don't add parameters to ?wsdl URLs
      return encodedUrl;
    }
    int i = encodedUrl.indexOf('?');
    StringBuilder u = new StringBuilder();
    if (i > 0) {
      u.append(encodedUrl.substring(0, i));
    } else {
      u.append(encodedUrl);
    }
    if (i < 0) {
      u.append('?');
    } else if (i < encodedUrl.length() - 1) {
      u.append(encodedUrl.substring(i));
      u.append('&');
    }
    u.append("tidx=");
    u.append(tidx);
    
    return u.toString();
  }
  
}