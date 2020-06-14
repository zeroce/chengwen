package cn.zeroce.design.chengwen.dto.respBody;

import cn.zeroce.design.chengwen.entity.Post;
import cn.zeroce.design.chengwen.entity.Users;

/**
 * @author: zeroce
 * @date 20.5.25 16:21
 */
public class PostDetailRespBody {
    private Post post;
    private Users author;

    public PostDetailRespBody() { }

    public PostDetailRespBody(Post post, Users author) {
        this.post = post;
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "PostDetailRespBody{" +
                "post=" + post +
                ", author=" + author +
                '}';
    }
}
