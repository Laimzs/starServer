package com.starServer.service.serviceImpl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starServer.dao.CommentMapper;
import com.starServer.entity.Comment;
import com.starServer.entity.CommentExample;
import com.starServer.entity.response.ResponseData;
import com.starServer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangsong on 2017/12/11.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;


    @Override
    public boolean addCream(int id) {
        return commentMapper.addCream(id);
    }

    @Override
    public boolean deleteCream(int id) {
        return commentMapper.deleteCream(id);
    }

    @Override
    public Integer saveComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public Integer deleteComment(int id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Comment getCommentById(int id) {
        return commentMapper.selectByPrimaryKey(id);
    }



/*    @Override
    public Boolean newLike(int id, int userId) {
        //先进行点赞记录
        CommentLikeRelation commentLikeRelation = new CommentLikeRelation();
        commentLikeRelation.setCommentId(id);
        commentLikeRelation.setUserId(userId);
        commentLikeRelation.setAgree(true);
        int res = commentLikeRelationMapper.insert(commentLikeRelation);
        if (res == 1) {
            return commentMapper.newLike(id);
        }
        return false;
    }*/



    @Override
    public Boolean releaseComment(int id) {
        return commentMapper.releaseComment(id);
    }

    @Override
    public ResponseData<List<Comment>> getComments(int page, int pageSize) {
        PageHelper.startPage(page+1, pageSize);
        ResponseData<List<Comment>> responseData=new ResponseData<>();
        CommentExample commentExample = new CommentExample();
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo=new PageInfo<>(commentList);
        responseData.jsonFill(1,null,commentList);
        responseData.setCount((int) pageInfo.getTotal());
        return responseData;
    }

/*    @Override
    public Boolean deleteCommentByUser(int commentId, int userId) {
        CommentExample commentExample = new CommentExample();
        //通过criteria构造查询条件
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andIdEqualTo(commentId);
        criteria.andUserIdEqualTo(userId);
        int res = commentMapper.deleteByExample(commentExample);
        if (res==1){
            return true;
        }
        return false;
    }*/

    @Override
    public ResponseData<List<Comment>> getCommentsByStarId(int starId, int page, int pageSize) {
        PageHelper.startPage(page+1, pageSize);
        ResponseData<List<Comment>> responseData=new ResponseData<>();
        List<Comment> commentList = commentMapper.getCommentsByStarId(starId);
        PageInfo<Comment> pageInfo=new PageInfo<>(commentList);
        responseData.jsonFill(1,null,commentList);
        responseData.setCount((int) pageInfo.getTotal());
        return responseData;
    }


}
