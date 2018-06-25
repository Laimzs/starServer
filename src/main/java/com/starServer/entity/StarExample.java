package com.starServer.entity;

import java.util.ArrayList;
import java.util.List;

public class StarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StarExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStarNameIsNull() {
            addCriterion("star_name is null");
            return (Criteria) this;
        }

        public Criteria andStarNameIsNotNull() {
            addCriterion("star_name is not null");
            return (Criteria) this;
        }

        public Criteria andStarNameEqualTo(String value) {
            addCriterion("star_name =", value, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameNotEqualTo(String value) {
            addCriterion("star_name <>", value, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameGreaterThan(String value) {
            addCriterion("star_name >", value, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameGreaterThanOrEqualTo(String value) {
            addCriterion("star_name >=", value, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameLessThan(String value) {
            addCriterion("star_name <", value, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameLessThanOrEqualTo(String value) {
            addCriterion("star_name <=", value, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameLike(String value) {
            addCriterion("star_name like", value, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameNotLike(String value) {
            addCriterion("star_name not like", value, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameIn(List<String> values) {
            addCriterion("star_name in", values, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameNotIn(List<String> values) {
            addCriterion("star_name not in", values, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameBetween(String value1, String value2) {
            addCriterion("star_name between", value1, value2, "starName");
            return (Criteria) this;
        }

        public Criteria andStarNameNotBetween(String value1, String value2) {
            addCriterion("star_name not between", value1, value2, "starName");
            return (Criteria) this;
        }

        public Criteria andNameInitialsIsNull() {
            addCriterion("name_initials is null");
            return (Criteria) this;
        }

        public Criteria andNameInitialsIsNotNull() {
            addCriterion("name_initials is not null");
            return (Criteria) this;
        }

        public Criteria andNameInitialsEqualTo(String value) {
            addCriterion("name_initials =", value, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsNotEqualTo(String value) {
            addCriterion("name_initials <>", value, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsGreaterThan(String value) {
            addCriterion("name_initials >", value, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsGreaterThanOrEqualTo(String value) {
            addCriterion("name_initials >=", value, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsLessThan(String value) {
            addCriterion("name_initials <", value, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsLessThanOrEqualTo(String value) {
            addCriterion("name_initials <=", value, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsLike(String value) {
            addCriterion("name_initials like", value, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsNotLike(String value) {
            addCriterion("name_initials not like", value, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsIn(List<String> values) {
            addCriterion("name_initials in", values, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsNotIn(List<String> values) {
            addCriterion("name_initials not in", values, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsBetween(String value1, String value2) {
            addCriterion("name_initials between", value1, value2, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andNameInitialsNotBetween(String value1, String value2) {
            addCriterion("name_initials not between", value1, value2, "nameInitials");
            return (Criteria) this;
        }

        public Criteria andFilmContentIsNull() {
            addCriterion("film_content is null");
            return (Criteria) this;
        }

        public Criteria andFilmContentIsNotNull() {
            addCriterion("film_content is not null");
            return (Criteria) this;
        }

        public Criteria andFilmContentEqualTo(String value) {
            addCriterion("film_content =", value, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentNotEqualTo(String value) {
            addCriterion("film_content <>", value, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentGreaterThan(String value) {
            addCriterion("film_content >", value, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentGreaterThanOrEqualTo(String value) {
            addCriterion("film_content >=", value, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentLessThan(String value) {
            addCriterion("film_content <", value, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentLessThanOrEqualTo(String value) {
            addCriterion("film_content <=", value, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentLike(String value) {
            addCriterion("film_content like", value, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentNotLike(String value) {
            addCriterion("film_content not like", value, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentIn(List<String> values) {
            addCriterion("film_content in", values, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentNotIn(List<String> values) {
            addCriterion("film_content not in", values, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentBetween(String value1, String value2) {
            addCriterion("film_content between", value1, value2, "filmContent");
            return (Criteria) this;
        }

        public Criteria andFilmContentNotBetween(String value1, String value2) {
            addCriterion("film_content not between", value1, value2, "filmContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentIsNull() {
            addCriterion("tv_play_content is null");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentIsNotNull() {
            addCriterion("tv_play_content is not null");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentEqualTo(String value) {
            addCriterion("tv_play_content =", value, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentNotEqualTo(String value) {
            addCriterion("tv_play_content <>", value, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentGreaterThan(String value) {
            addCriterion("tv_play_content >", value, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentGreaterThanOrEqualTo(String value) {
            addCriterion("tv_play_content >=", value, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentLessThan(String value) {
            addCriterion("tv_play_content <", value, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentLessThanOrEqualTo(String value) {
            addCriterion("tv_play_content <=", value, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentLike(String value) {
            addCriterion("tv_play_content like", value, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentNotLike(String value) {
            addCriterion("tv_play_content not like", value, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentIn(List<String> values) {
            addCriterion("tv_play_content in", values, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentNotIn(List<String> values) {
            addCriterion("tv_play_content not in", values, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentBetween(String value1, String value2) {
            addCriterion("tv_play_content between", value1, value2, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTvPlayContentNotBetween(String value1, String value2) {
            addCriterion("tv_play_content not between", value1, value2, "tvPlayContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentIsNull() {
            addCriterion("trembling_content is null");
            return (Criteria) this;
        }

        public Criteria andTremblingContentIsNotNull() {
            addCriterion("trembling_content is not null");
            return (Criteria) this;
        }

        public Criteria andTremblingContentEqualTo(String value) {
            addCriterion("trembling_content =", value, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentNotEqualTo(String value) {
            addCriterion("trembling_content <>", value, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentGreaterThan(String value) {
            addCriterion("trembling_content >", value, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentGreaterThanOrEqualTo(String value) {
            addCriterion("trembling_content >=", value, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentLessThan(String value) {
            addCriterion("trembling_content <", value, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentLessThanOrEqualTo(String value) {
            addCriterion("trembling_content <=", value, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentLike(String value) {
            addCriterion("trembling_content like", value, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentNotLike(String value) {
            addCriterion("trembling_content not like", value, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentIn(List<String> values) {
            addCriterion("trembling_content in", values, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentNotIn(List<String> values) {
            addCriterion("trembling_content not in", values, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentBetween(String value1, String value2) {
            addCriterion("trembling_content between", value1, value2, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andTremblingContentNotBetween(String value1, String value2) {
            addCriterion("trembling_content not between", value1, value2, "tremblingContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentIsNull() {
            addCriterion("extre_content is null");
            return (Criteria) this;
        }

        public Criteria andExtreContentIsNotNull() {
            addCriterion("extre_content is not null");
            return (Criteria) this;
        }

        public Criteria andExtreContentEqualTo(String value) {
            addCriterion("extre_content =", value, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentNotEqualTo(String value) {
            addCriterion("extre_content <>", value, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentGreaterThan(String value) {
            addCriterion("extre_content >", value, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentGreaterThanOrEqualTo(String value) {
            addCriterion("extre_content >=", value, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentLessThan(String value) {
            addCriterion("extre_content <", value, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentLessThanOrEqualTo(String value) {
            addCriterion("extre_content <=", value, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentLike(String value) {
            addCriterion("extre_content like", value, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentNotLike(String value) {
            addCriterion("extre_content not like", value, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentIn(List<String> values) {
            addCriterion("extre_content in", values, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentNotIn(List<String> values) {
            addCriterion("extre_content not in", values, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentBetween(String value1, String value2) {
            addCriterion("extre_content between", value1, value2, "extreContent");
            return (Criteria) this;
        }

        public Criteria andExtreContentNotBetween(String value1, String value2) {
            addCriterion("extre_content not between", value1, value2, "extreContent");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgIsNull() {
            addCriterion("star_head_img is null");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgIsNotNull() {
            addCriterion("star_head_img is not null");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgEqualTo(String value) {
            addCriterion("star_head_img =", value, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgNotEqualTo(String value) {
            addCriterion("star_head_img <>", value, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgGreaterThan(String value) {
            addCriterion("star_head_img >", value, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("star_head_img >=", value, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgLessThan(String value) {
            addCriterion("star_head_img <", value, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgLessThanOrEqualTo(String value) {
            addCriterion("star_head_img <=", value, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgLike(String value) {
            addCriterion("star_head_img like", value, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgNotLike(String value) {
            addCriterion("star_head_img not like", value, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgIn(List<String> values) {
            addCriterion("star_head_img in", values, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgNotIn(List<String> values) {
            addCriterion("star_head_img not in", values, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgBetween(String value1, String value2) {
            addCriterion("star_head_img between", value1, value2, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarHeadImgNotBetween(String value1, String value2) {
            addCriterion("star_head_img not between", value1, value2, "starHeadImg");
            return (Criteria) this;
        }

        public Criteria andStarPortraitIsNull() {
            addCriterion("star_portrait is null");
            return (Criteria) this;
        }

        public Criteria andStarPortraitIsNotNull() {
            addCriterion("star_portrait is not null");
            return (Criteria) this;
        }

        public Criteria andStarPortraitEqualTo(String value) {
            addCriterion("star_portrait =", value, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitNotEqualTo(String value) {
            addCriterion("star_portrait <>", value, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitGreaterThan(String value) {
            addCriterion("star_portrait >", value, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitGreaterThanOrEqualTo(String value) {
            addCriterion("star_portrait >=", value, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitLessThan(String value) {
            addCriterion("star_portrait <", value, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitLessThanOrEqualTo(String value) {
            addCriterion("star_portrait <=", value, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitLike(String value) {
            addCriterion("star_portrait like", value, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitNotLike(String value) {
            addCriterion("star_portrait not like", value, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitIn(List<String> values) {
            addCriterion("star_portrait in", values, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitNotIn(List<String> values) {
            addCriterion("star_portrait not in", values, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitBetween(String value1, String value2) {
            addCriterion("star_portrait between", value1, value2, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andStarPortraitNotBetween(String value1, String value2) {
            addCriterion("star_portrait not between", value1, value2, "starPortrait");
            return (Criteria) this;
        }

        public Criteria andLikeCountIsNull() {
            addCriterion("like_count is null");
            return (Criteria) this;
        }

        public Criteria andLikeCountIsNotNull() {
            addCriterion("like_count is not null");
            return (Criteria) this;
        }

        public Criteria andLikeCountEqualTo(String value) {
            addCriterion("like_count =", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotEqualTo(String value) {
            addCriterion("like_count <>", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountGreaterThan(String value) {
            addCriterion("like_count >", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountGreaterThanOrEqualTo(String value) {
            addCriterion("like_count >=", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountLessThan(String value) {
            addCriterion("like_count <", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountLessThanOrEqualTo(String value) {
            addCriterion("like_count <=", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountLike(String value) {
            addCriterion("like_count like", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotLike(String value) {
            addCriterion("like_count not like", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountIn(List<String> values) {
            addCriterion("like_count in", values, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotIn(List<String> values) {
            addCriterion("like_count not in", values, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountBetween(String value1, String value2) {
            addCriterion("like_count between", value1, value2, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotBetween(String value1, String value2) {
            addCriterion("like_count not between", value1, value2, "likeCount");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}