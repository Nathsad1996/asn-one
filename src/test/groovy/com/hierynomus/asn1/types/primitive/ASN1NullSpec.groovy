/*
 *    Copyright 2016 Jeroen van Erp <jeroen@hierynomus.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.hierynomus.asn1.types.primitive

import com.hierynomus.asn1.ASN1InputStream
import com.hierynomus.asn1.ASN1OutputStream
import com.hierynomus.asn1.encodingrules.ber.BERDecoder
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ASN1NullSpec extends Specification {

  def "should parse ASN.1 NULL with value #value"() {
    expect:
    new ASN1InputStream(new BERDecoder(), new ByteArrayInputStream(buffer as byte[])).readObject() == value

    where:
    buffer       | value
    [0x05, 0x00] | new ASN1Null()
  }

  def "should write ASN.1 NULL as bytes #bytes"() {
    given:
    def stream = new ByteArrayOutputStream()

    when:
    new ASN1OutputStream(null, stream).writeObject(value)

    then:
    stream.toByteArray() == bytes

    where:
    value | bytes
    new ASN1Null() | [0x05, 0x00] as byte[]
  }
}
