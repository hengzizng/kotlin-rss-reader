import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RssTest {
    @Test
    fun `데이터가 있다`() {
        val rss = Rss()
       rss.getElenmetByTagName()
        rss.articleList
        rss.sortArticleByDate()
        //rss.articleList
        //rss.printArticle(10)
        val findArticle = rss.findArticleByWord("데이터")
        rss.printArticle(findArticle)
    }
}