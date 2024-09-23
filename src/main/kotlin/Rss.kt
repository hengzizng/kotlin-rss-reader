import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.Node.ELEMENT_NODE
import org.xml.sax.InputSource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

// 우아한 형제들: https://techblog.woowahan.com/feed
// 라인: https://techblog.lycorp.co.jp/ko/feed/index.xml
// 뱅크샐러: https://blog.banksalad.com/rss.xml

class Rss {
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
        val channel = xml.getElementsByTagName("channel").item(0)
        val channelElement = channel as Element
        val items = channelElement.getElementsByTagName("item")
        for (i in 0 until items.length) {
            val itemNode = items.item(i)
            if (itemNode.nodeType == ELEMENT_NODE) {
                val itemElement = itemNode as Element

                println(itemElement.getElementsByTagName("title").item(0).textContent)
                println(itemElement.getElementsByTagName("link").item(0).textContent)
                println(itemElement.getElementsByTagName("description").item(0).textContent)
            }
        }
        return channel
    }
}
