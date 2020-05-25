package com.zstu.ky.kyykt.demo;

import com.zstu.ky.kyykt.demo.Impl.SimHashServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimHashApplicationTests {
@Autowired
    SimHashServiceImpl simHashService;
    @Test
    void contextLoads() {
        String  str1 = "而钱江新城，是建在江面宽达1000米、比黄浦江甚至宽一倍钱塘江边上。因此，新中心区的空间规划布局上，将从大范围、大尺度、高视点的角度，体现现代城市以高层建筑群为核心的特色。在建筑风格上，将以香港中环和纽约曼哈顿为标榜，在建筑的“高度”、“亮度”和“密度”三方面将达到世界一流水平。重点突出100米以上的超高层建筑群，最高天际线可以达到300余米。不仅完美体现了杭州作为国际化大都市的风貌，也尊重了西湖“三面云山一面城”的历史格局。";
        String str2 = "杭州有着十分独特的自然环境。杭州城市形态的演变，始终既受到自然环境的限制，也在将自然环境的优势发挥到最大。自五代十国时期的吴越国时期起，杭州的中心城区范围就基本定型，中心城区以西是以西湖以及西湖景区内的群山1996年时，将主城区东面的下沙，与主城区隔江相望的滨江，和城市西北部的三墩和蒋村并入杭州市区，就是第一次向上述空间方向上的拓展。";
        String str3 = "但是钱江新城，比黄浦江甚至宽好几倍的钱塘江边上。因此，新中心区的空间规划布局上，将从大范围、大尺度、高视点的角度，体现现代城市以高层建筑群为核心的特色。在建筑风格上，将以香港中环和纽约曼哈顿为标榜，在建筑的“高度”、“亮度”和“密度”三方面将达到世界一流水平。重点突出100米以上的超高层建筑群，最高天际线可以达到300余米。不仅完美体现了杭州作为国际化大都市的风貌，也尊重了西湖“三面云山一面城”的历史格局。";
        String str4 = "而温州新城，是建在江面宽达1000米、比黄河甚至宽一倍钱塘江边上。因此，新中心区的空间规划布局上，将从大范围、大尺度、高视点的角度，体现古代城市以高层建筑群为核心的特色。在建筑风格上，将以澳门中环和华盛顿曼哈顿为标榜，在建筑的“高度”、“亮度”和“密度”三方面将达到世界一流水平。重点突出100米以上的超高层建筑群，最高天际线可以达到300余米。不仅完美体现了杭州作为国际化大都市的风貌，也尊重了西湖“三面云山一面城”的历史格局。";
        String str5 = "使用了JSP+servlet+Tomcat实现了页面的功能。学会了配置环境与搭建Tomcat容器，了解了JSP中的步骤：首先，建立TCP服务器，为每个请求客户端建立连接；然后，接收客户端请求信息，并解析请求消息；接着，使用POST或Get来处理用户请求；最后，将处理结果封转成，HTTP响应消息发送给客户端。学会了如何配置Tomcat、JSP、servlet的环境，学会了使用jsp动态生成表格，操作数据库。";
        String str6 = "使用了JSP+servlet+Tomcat实现了页面的功能。学会了配置环境与搭建Tomcat容器，了解了JSP中的步骤：首先，建立TCP服务器，为每个请求客户端建立连接；然后，接收客户端请求信息，并解析请求消息";
        String str7 = "而在2001年至2013年间，杭州城市建成区在南向的扩张上保持了较高的增速，这是因为这一时期江南的发展非常迅速，滨江区和萧山区的城区部分逐渐发展形成一体，另外两个发展方向，一个是向东北的临平方向扩张，另外一个是向西部的老余杭方向扩张，都对应着余杭区范围内的建成区扩展。";
//        string str8 = "有大量互联网企业驻扎的杭州建立了大量的轻量型的写字楼。总所周知，互联网一直是时代的领导者，走在人类潮流的最前端，各色样式，各种建筑形态的写字楼层出不穷。近年来新建的写字楼，材料不同，构架不同，形态不同，特色不同，总的来说，与西方发达国家的高楼大厦差不多。";
//        string str9 = "现在的杭州，有着大量的互联网企业在这里驻扎，这也让杭州建立了大量的轻量型的写字楼。大家都知道，互联网一直是时代的领导者，走在人类潮流的最前端，于是，各色样式，各种建筑形态的写字楼，被新文化影响着，由此诞生。近年来新建的写字楼，不同于以往的材料，不同于以往的构架，不同于以往的形态，不同于以往的特色，整体看来，与西方发达国家的高楼大厦亦不分伯仲。";
//        string str10 = "public static int Times33(string value){int hash = 0;for (int i = 0; i < value.Length; i++){hash = 33 * hash + value[i];}return hash;}";
//        string str11 = "public static int Times33(string Value){int Hash = 0;for (int j = 0; j < Value.Length; j++){hash = 33 * Hash + Value[j];}return Hash;}";

        System.out.println(simHashService.CacuSimHash(str1, str7));
    }


}
