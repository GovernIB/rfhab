package es.caib.rfhab.back.utils;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 
 * @author anadal
 *
 */
public class RFHabMaxUploadSizeExceededException extends MaxUploadSizeExceededException {

  final String msgCode;

  /**
   * @param msg
   */
  public RFHabMaxUploadSizeExceededException(Throwable cause, long maxSize, String msgCode) {
    super(maxSize, cause);    
    this.msgCode = msgCode;
  }

  public String getMsgCode() {
    return msgCode;
  }

}
