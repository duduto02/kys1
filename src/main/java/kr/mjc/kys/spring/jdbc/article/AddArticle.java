package kr.mjc.kys.spring.jdbc.article;

import kr.mjc.kys.basics.jdbc.Article.Article;
import kr.mjc.kys.basics.jdbc.Article.ArticleDao;
import kr.mjc.kys.spring.jdbc.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@Slf4j
public class AddArticle {
    public static void main(String[] args) {
        Article article = new Article();
        Scanner scanner = new Scanner(System.in).useDelimiter("//");
        System.out.print("title// : ");
        article.setTitle(scanner.next());
        System.out.print("content// : ");
        article.setContent(scanner.next());
        scanner.close();
        article.setUserId(141);
        article.setName("superuserman");

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        ArticleDao articleDao = applicationContext.getBean(ArticleDao.class);

        articleDao.addArticle(article);
        log.info("저장 완료");
    }
}
