package com.starServer.service;


import com.starServer.entity.Comment;
import com.starServer.entity.response.ResponseData;

import java.util.List;

/**
 * Created by zhangsong on 2017/12/11.
 */
public interface CommentService {
    boolean addCream(int id);
    boolean deleteCream(int id);
    Integer saveComment(Comment comment);
    Integer deleteComment(int id);
/*    Boolean newLike(int id, int userId);
    Boolean deleteLike(int id, int userId);

    List<Integer> getAllLikeByUserId(Integer userId);*/
    Boolean releaseComment(int id);
    /*Boolean deleteCommentByUser(int commentId, int userId);*/
    ResponseData<List<Comment>> getComments(int page, int pageSize);
    ResponseData<List<Comment>> getCommentsByStarId(int starId, int page, int pageSize);
}
