import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RssTest {
    @Test
    fun `데이터가 있다`() {
        val rss = Rss()
        val data = rss.getElenmetByTagName()
        println(data)
    }
}