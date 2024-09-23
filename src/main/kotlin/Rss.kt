import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.Node.ELEMENT_NODE
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

// 우아한 형제들: https://techblog.woowahan.com/feed
// 라인: https://techblog.lycorp.co.jp/ko/feed/index.xml
// 뱅크샐러: https://blog.banksalad.com/rss.xml

class Rss {
    val articleList = mutableListOf<Article>()
    fun getElenmetByTagName() {
        val factory = DocumentBuilderFactory.newInstance()
        val xml = factory.newDocumentBuilder().parse("https://techblog.woowahan.com/feed")
        parseArticle(xml)

        val xml2 = factory.newDocumentBuilder().parse("https://techblog.lycorp.co.jp/ko/feed/index.xml")
        parseArticle(xml2)

        val xml3 = factory.newDocumentBuilder().parse("https://blog.banksalad.com/rss.xml")
        parseArticle(xml3)
    }

    private fun parseArticle(xml: Document): Node? {
        val dateFormat = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)

        val channel = xml.getElementsByTagName("channel").item(0)
        val channelElement = channel as Element
        val items = channelElement.getElementsByTagName("item")

        for (i in 0 until items.length) {
            val itemNode = items.item(i)
            if (itemNode.nodeType == ELEMENT_NODE) {
                val itemElement = itemNode as Element
                val article = Article(
                    LocalDateTime.parse(itemElement.getElementsByTagName("pubDate").item(0).textContent, dateFormat),
                    itemElement.getElementsByTagName("title").item(0).textContent,
                    itemElement.getElementsByTagName("link").item(0).textContent,
                    itemElement.getElementsByTagName("description").item(0).textContent
                )
                articleList.add(article)
            }
        }
        return channel
    }

    fun sortArticleByDate() {
        articleList.sortByDescending { it.date }
    }

    fun printArticle(number: Int) {
        articleList.take(number).forEach {
            println(it.date)
            println(it.link)
            println(it.title)
            println(it.description)
        }
    }
    fun printArticle(article: List<Article>) {
        article.forEach {
            println(it.date)
            println(it.link)
            println(it.title)
            println(it.description)
        }
    }

    fun findArticleByWord(word:String) : List<Article> {
        val findArticle = articleList.filter { it.title!!.contains(word) }
        return findArticle
    }
}

class Article(
    //val date : Date ,
    val date : LocalDateTime,
    val title : String? ,
    val link : String? ,
    val description: String? ,
){


}
