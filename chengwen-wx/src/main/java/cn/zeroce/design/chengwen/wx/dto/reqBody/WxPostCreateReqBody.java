package cn.zeroce.design.chengwen.wx.dto.reqBody;

import java.util.List;

/**
 * 微信端 article 创建 request body
 * @author: zeroce
 * @date 20.4.23 14:51
 */
public class WxPostCreateReqBody {
    private String hotqinsessionid;
    private String title;
    private String content;
    private List<String> images;

    public WxPostCreateReqBody() { }

    public WxPostCreateReqBody(String hotqinsessionid, String title, String content, List<String> images) {
        this.hotqinsessionid = hotqinsessionid;
        this.title = title;
        this.content = content;
        this.images = images;
    }

    public String getHotqinsessionid() {
        return hotqinsessionid;
    }

    public void setHotqinsessionid(String hotqinsessionid) {
        this.hotqinsessionid = hotqinsessionid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
