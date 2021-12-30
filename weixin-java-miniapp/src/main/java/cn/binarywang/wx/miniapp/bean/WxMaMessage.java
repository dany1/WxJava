package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import cn.binarywang.wx.miniapp.util.xml.XStreamTransformer;
import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@XStreamAlias("xml")
@Data
public class WxMaMessage implements Serializable {
  private static final long serialVersionUID = -3586245291677274914L;

  @SerializedName("Encrypt")
  @XStreamAlias("Encrypt")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String encrypt;

  @SerializedName("ToUserName")
  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String toUser;

  @SerializedName("FromUserName")
  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String fromUser;

  @SerializedName("CreateTime")
  @XStreamAlias("CreateTime")
  private Integer createTime;

  @SerializedName("MsgType")
  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String msgType;

  @SerializedName("MsgDataFormat")
  @XStreamAlias("MsgDataFormat")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String msgDataFormat;

  @SerializedName("Content")
  @XStreamAlias("Content")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String content;

  @SerializedName("MsgId")
  @XStreamAlias("MsgId")
  private Long msgId;

  @SerializedName("PicUrl")
  @XStreamAlias("PicUrl")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String picUrl;

  @SerializedName("MediaId")
  @XStreamAlias("MediaId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String mediaId;

  @SerializedName("Event")
  @XStreamAlias("Event")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String event;

  @SerializedName("Title")
  @XStreamAlias("Title")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String title;

  @SerializedName("AppId")
  @XStreamAlias("AppId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String appId;

  @SerializedName("PagePath")
  @XStreamAlias("PagePath")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String pagePath;

  @SerializedName("ThumbUrl")
  @XStreamAlias("ThumbUrl")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String thumbUrl;

  @SerializedName("ThumbMediaId")
  @XStreamAlias("ThumbMediaId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String thumbMediaId;

  @SerializedName("SessionFrom")
  @XStreamAlias("SessionFrom")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String sessionFrom;

  /**
   * 以下是异步校验图片/音频是否含有违法违规内容的异步检测结果推送报文中的参数
   */
  @SerializedName("isrisky")
  @XStreamAlias("isrisky")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String isRisky;

  @SerializedName("extra_info_json")
  @XStreamAlias("extra_info_json")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String extraInfoJson;

  @SerializedName("appid")
  @XStreamAlias("appid")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String appid;

  @SerializedName("trace_id")
  @XStreamAlias("trace_id")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String traceId;

  @SerializedName("status_code")
  @XStreamAlias("status_code")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String statusCode;

  @SerializedName("Scene")
  @XStreamAlias("Scene")
  private Integer scene;

  @SerializedName("Query")
  @XStreamAlias("Query")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String query;

  @SerializedName("RevokeInfo")
  @XStreamAlias("RevokeInfo")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String revokeInfo;

  @SerializedName("OpenID")
  @XStreamAlias("OpenID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String openId;

  @SerializedName("PluginID")
  @XStreamAlias("PluginID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String pluginId;

  @SerializedName("OpenPID")
  @XStreamAlias("OpenPID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String openPid;

  /**
   * 订阅消息时，服务器收到的通知
   * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/subscribe-message.html
   * 当只有一条订阅模板的时候，目前生产返回json格式的字段和文档不一致，少了SubscribeMsgPopupEvent这一层而且不是数组是对象
   */
  @SerializedName("SubscribeMsgPopupEvent")
  @XStreamAlias("SubscribeMsgPopupEvent")
  private List<SubscribeMsgPopupEvent> subscribeMsgPopupEvent = new LinkedList<>();

  /**
   * 不要直接使用这个字段，该字段是为了兼容，请使用 `subscribeMsgPopupEvent`
   * 订阅消息事件推送 当小程序只订阅了一个模板，并且服务器的推送消息格式选择的是JSON的时候，实际的推送数据和官网文档不一致，这里做一个兼容
   * 文档的格式是这样
   * {
   *   "ToUserName": "gh_123456789abc",
   *   "FromUserName": "o7esq5OI1Uej6Xixw1lA2H7XDVbc",
   *   "CreateTime": "1620973045",
   *   "MsgType": "event",
   *   "Event": "subscribe_msg_popup_event",
   *   "SubscribeMsgPopupEvent": [   {
   *         "TemplateId": "hD-ixGOhYmUfjOnI8MCzQMPshzGVeux_2vzyvQu7O68",
   *         "SubscribeStatusString": "accept",
   *         "PopupScene": "0"
   *     }]
   *  }
   *  实际推送的是
   *  {
   *   "ToUserName": "gh_123456789abc",
   *   "FromUserName": "o7esq5OI1Uej6Xixw1lA2H7XDVbc",
   *   "CreateTime": "1620973045",
   *   "MsgType": "event",
   *   "Event": "subscribe_msg_popup_event",
   *   "List": {
   *         "TemplateId": "hD-ixGOhYmUfjOnI8MCzQMPshzGVeux_2vzyvQu7O68",
   *         "SubscribeStatusString": "accept",
   *         "PopupScene": "0"
   *     }
   *  }
   */
  @SerializedName("List")
  private SubscribeMsgPopupEvent oneSubscribeMsgInJson;

  @Data
  @XStreamAlias("List")
  public static class SubscribeMsgPopupEvent implements Serializable{
    private static final long serialVersionUID = 6787700849708909964L;

    /**
     * 模板id
     */
    @SerializedName("TemplateId")
    @XStreamAlias("TemplateId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String templateId;

    /**
     * 订阅结果（accept接收；reject拒收）
     */
    @SerializedName("SubscribeStatusString")
    @XStreamAlias("SubscribeStatusString")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String subscribeStatusString;

    /**
     * 弹框场景，0代表在小程序页面内
     */
    @SerializedName("PopupScene")
    @XStreamAlias("PopupScene")
    private String popupScene;
  }


  public static WxMaMessage fromXml(String xml) {
    return XStreamTransformer.fromXml(WxMaMessage.class, xml);
  }

  public static WxMaMessage fromXml(InputStream is) {
    return XStreamTransformer.fromXml(WxMaMessage.class, is);
  }

  /**
   * 从加密字符串转换.
   *
   * @param encryptedXml 密文
   * @param wxMaConfig   配置存储器对象
   * @param timestamp    时间戳
   * @param nonce        随机串
   * @param msgSignature 签名串
   */
  public static WxMaMessage fromEncryptedXml(String encryptedXml,
                                             WxMaConfig wxMaConfig, String timestamp, String nonce,
                                             String msgSignature) {
    String plainText = new WxMaCryptUtils(wxMaConfig).decryptXml(msgSignature, timestamp, nonce, encryptedXml);
    return fromXml(plainText);
  }

  public static WxMaMessage fromEncryptedXml(InputStream is, WxMaConfig wxMaConfig, String timestamp,
                                             String nonce, String msgSignature) {
    try {
      return fromEncryptedXml(IOUtils.toString(is, StandardCharsets.UTF_8), wxMaConfig,
        timestamp, nonce, msgSignature);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

  public static WxMaMessage fromJson(String json) {
    WxMaMessage wxMaMessage = WxMaGsonBuilder.create().fromJson(json, WxMaMessage.class);
    /**
     * 订阅消息事件推送 当小程序只订阅了一个模板，并且服务器的数据推送的消息格式是JSON的时候，实际的推送数据和官网文档不一致，这里做一个兼容
     */
    if (wxMaMessage.oneSubscribeMsgInJson != null) {
      wxMaMessage.getSubscribeMsgPopupEvent().add(wxMaMessage.oneSubscribeMsgInJson);
      wxMaMessage.oneSubscribeMsgInJson = null;
    }
    return wxMaMessage;
  }

  public static WxMaMessage fromEncryptedJson(String encryptedJson, WxMaConfig config) {
    try {
      WxMaMessage encryptedMessage = fromJson(encryptedJson);
      String plainText = new WxMaCryptUtils(config).decrypt(encryptedMessage.getEncrypt());
      return fromJson(plainText);
    } catch (Exception e) {
      throw new WxRuntimeException(e);
    }
  }

  public static WxMaMessage fromEncryptedJson(InputStream inputStream, WxMaConfig config) {
    try {
      return fromEncryptedJson(IOUtils.toString(inputStream, StandardCharsets.UTF_8), config);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return this.toJson();
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}
