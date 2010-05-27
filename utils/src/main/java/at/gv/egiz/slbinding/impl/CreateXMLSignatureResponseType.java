/*
* Copyright 2008 Federal Chancellery Austria and
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.gv.egiz.slbinding.impl;

import at.gv.egiz.slbinding.RedirectCallback;
import at.gv.egiz.slbinding.RedirectEventFilter;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.stream.XMLStreamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author clemens
 */
public class CreateXMLSignatureResponseType extends at.buergerkarte.namespaces.securitylayer._1.CreateXMLSignatureResponseType implements RedirectCallback {

    @XmlTransient
    private final Logger log = LoggerFactory.getLogger(CreateXMLSignatureResponseType.class);
    @XmlTransient
    protected ByteArrayOutputStream redirectOS = null;

    @Override
    public void enableRedirect(RedirectEventFilter filter) throws XMLStreamException {
        log.trace("enabling event redirection for XMLContentType");
        redirectOS = new ByteArrayOutputStream();
        filter.setRedirectStream(redirectOS);
    }

    @Override
    public void disableRedirect(RedirectEventFilter filter) throws XMLStreamException {
        log.trace("disabling event redirection for XMLContentType");
        filter.flushRedirectStream();
        filter.setRedirectStream(null);
        if (log.isTraceEnabled()) {
          try {
            log.trace("redirected events (UTF-8): " + redirectOS.toString("UTF-8"));
          } catch (UnsupportedEncodingException ex) {
            log.error("failed to log redirected events", ex);
          }
        }
    }

    @Override
    public ByteArrayOutputStream getRedirectedStream() {
        return redirectOS;
    }
}