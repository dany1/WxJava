package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.xml.XStreamTransformer;
import me.chanjar.weixin.common.api.WxConsts;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
public class WxMaMessageTest {

  public void testFromXml() {
    String xml = "<xml>\n" +
      "   <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "   <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
      "   <CreateTime>1482048670</CreateTime>\n" +
      "   <MsgType><![CDATA[text]]></MsgType>\n" +
      "   <Content><![CDATA[this is a test]]></Content>\n" +
      "   <MsgId>1234567890123456</MsgId>\n" +
      "   <PicUrl><![CDATA[this is a url]]></PicUrl>\n" +
      "   <MediaId><![CDATA[media_id]]></MediaId>\n" +
      "   <Title><![CDATA[Title]]></Title>\n" +
      "   <AppId><![CDATA[AppId]]></AppId>\n" +
      "   <PagePath><![CDATA[PagePath]]></PagePath>\n" +
      "   <ThumbUrl><![CDATA[ThumbUrl]]></ThumbUrl>\n" +
      "   <ThumbMediaId><![CDATA[ThumbMediaId]]></ThumbMediaId>\n" +
      "   <Event><![CDATA[user_enter_tempsession]]></Event>\n" +
      "   <SessionFrom><![CDATA[sessionFrom]]></SessionFrom>\n" +
      "</xml>";
    WxMaMessage wxMessage = WxMaMessage.fromXml(xml);
    assertEquals(wxMessage.getToUser(), "toUser");
    assertEquals(wxMessage.getFromUser(), "fromUser");
    assertEquals(wxMessage.getCreateTime(),new Integer(1482048670));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.TEXT);
    assertEquals(wxMessage.getContent(), "this is a test");
    assertEquals(wxMessage.getMsgId(), new Long(1234567890123456L));
    assertEquals(wxMessage.getPicUrl(), "this is a url");
    assertEquals(wxMessage.getMediaId(), "media_id");
    assertEquals(wxMessage.getTitle(), "Title");
    assertEquals(wxMessage.getPagePath(), "PagePath");
    assertEquals(wxMessage.getThumbUrl(), "ThumbUrl");
    assertEquals(wxMessage.getThumbMediaId(), "ThumbMediaId");
    assertEquals(wxMessage.getAppId(), "AppId");
    assertEquals(wxMessage.getEvent(), "user_enter_tempsession");
    assertEquals(wxMessage.getSessionFrom(), "sessionFrom");
  }


  public void testSubscribeMsgPopupEvent() {
    testSubscribeMsgPopupEventXml();
    testSubscribeMsgPopupEventJson();
  }

  private void testSubscribeMsgPopupEventXml() {
    String xml = "<xml>" +
      "<ToUserName><![CDATA[gh_123456789abc]]></ToUserName>\n" +
      "<FromUserName><![CDATA[otFpruAK8D-E6EfStSYonYSBZ8_4]]></FromUserName>\n" +
      "<CreateTime>1610969440</CreateTime>\n" +
      "<MsgType><![CDATA[event]]></MsgType>\n" +
      "<Event><![CDATA[subscribe_msg_popup_event]]></Event>\n" +
      "<SubscribeMsgPopupEvent>\n" +
      " <List>\n" +
      "   <TemplateId><![CDATA[VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc]]></TemplateId>\n" +
      "   <SubscribeStatusString><![CDATA[accept]]></SubscribeStatusString>\n" +
      "   <PopupScene>0</PopupScene>\n" +
      " </List>\n" +
      "</SubscribeMsgPopupEvent>" +
      "</xml>";

    WxMaMessage wxMessage = WxMaMessage.fromXml(xml);
    assertEquals(wxMessage.getToUser(), "gh_123456789abc");
    assertEquals(wxMessage.getFromUser(), "otFpruAK8D-E6EfStSYonYSBZ8_4");
    assertEquals(wxMessage.getCreateTime(),new Integer(1610969440));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), "subscribe_msg_popup_event");
    assertEquals(wxMessage.getSubscribeMsgPopupEvent().size(), 1);
  }

  private void testSubscribeMsgPopupEventJson() {
    // 订阅单个模板的通知
    String json = "{\"ToUserName\":\"gh_123456789abc\",\"FromUserName\":\"otFpruAK8D-E6EfStSYonYSBZ8_4\",\"CreateTime\":1610969440,\"MsgType\":\"event\",\"Event\":\"subscribe_msg_popup_event\",\"List\":{\"PopupScene\":\"0\",\"SubscribeStatusString\":\"accept\",\"TemplateId\":\"VRR0UEO9VJOLs0MHlU0OilqX6MVFDwH3_3gz3Oc0NIc\"}}";
    WxMaMessage wxMessage = WxMaMessage.fromJson(json);
    assertEquals(wxMessage.getToUser(), "gh_123456789abc");
    assertEquals(wxMessage.getFromUser(), "otFpruAK8D-E6EfStSYonYSBZ8_4");
    assertEquals(wxMessage.getCreateTime(),new Integer(1610969440));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), "subscribe_msg_popup_event");
    assertEquals(wxMessage.getSubscribeMsgPopupEvent().size(), 1);
    // 订阅多条模板的通知
    json = "{\"ToUserName\": \"gh_123456789abc\",\"FromUserName\": \"o7esq5OI1Uej6Xixw1lA2H7XDVbc\",\"CreateTime\": \"1620973045\",\"MsgType\": \"event\",\"Event\":\"subscribe_msg_popup_event\",\"SubscribeMsgPopupEvent\": [{\"TemplateId\": \"hD-ixGOhYmUfjOnI8MCzQMPshzGVeux_2vzyvQu7O68\",\"SubscribeStatusString\": \"accept\",\"PopupScene\": \"0\"}]}";
    wxMessage = WxMaMessage.fromJson(json);
    assertEquals(wxMessage.getToUser(), "gh_123456789abc");
    assertEquals(wxMessage.getFromUser(), "o7esq5OI1Uej6Xixw1lA2H7XDVbc");
    assertEquals(wxMessage.getCreateTime(),new Integer(1620973045));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), "subscribe_msg_popup_event");
    assertEquals(wxMessage.getSubscribeMsgPopupEvent().size(), 1);
  }

}
