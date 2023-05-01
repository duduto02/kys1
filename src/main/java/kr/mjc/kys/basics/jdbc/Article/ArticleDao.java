package kr.mjc.kys.basics.jdbc.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> listArticles(int count, int page);

    Article getArticle(int articleId);

    void addArticle(Article article);

    int updateArticle(Article article);

    int deleteArticle(int articleId, int userId);
}
