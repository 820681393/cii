package com.purchase.utils;


import com.alibaba.fastjson.JSONArray;

/**
 * Created by Miracle on 2020/9/11.
 */
public class TelCountryUtil {

    private static String telCounTry="[\n" +
            "  {\n" +
            "    \"cnName\": \"中国\",\n" +
            "    \"enName\": \"China\",\n" +
            "    \"id\": 1,\n" +
            "    \"mobileCode\": \"86\",\n" +
            "    \"twName\": \"中國\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"中国台湾\",\n" +
            "    \"enName\": \"Taiwan Prov\",\n" +
            "    \"id\": 2,\n" +
            "    \"mobileCode\": \"886\",\n" +
            "    \"twName\": \"中國臺灣\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"中国香港\",\n" +
            "    \"enName\": \"HongKong\",\n" +
            "    \"id\": 3,\n" +
            "    \"mobileCode\": \"852\",\n" +
            "    \"twName\": \"中國香港\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"中国澳门\",\n" +
            "    \"enName\": \"Macau\",\n" +
            "    \"id\": 4,\n" +
            "    \"mobileCode\": \"853\",\n" +
            "    \"twName\": \"中國澳門\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"美国\",\n" +
            "    \"enName\": \"Usa\",\n" +
            "    \"id\": 5,\n" +
            "    \"mobileCode\": \"1\",\n" +
            "    \"twName\": \"美國\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"俄罗斯\",\n" +
            "    \"enName\": \"Russia\",\n" +
            "    \"id\": 6,\n" +
            "    \"mobileCode\": \"7\",\n" +
            "    \"twName\": \"俄羅斯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"加拿大\",\n" +
            "    \"enName\": \"Canada\",\n" +
            "    \"id\": 7,\n" +
            "    \"mobileCode\": \"1\",\n" +
            "    \"twName\": \"加拿大\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"南非\",\n" +
            "    \"enName\": \"South Africa\",\n" +
            "    \"id\": 8,\n" +
            "    \"mobileCode\": \"27\",\n" +
            "    \"twName\": \"南非\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"希腊\",\n" +
            "    \"enName\": \"Greece\",\n" +
            "    \"id\": 9,\n" +
            "    \"mobileCode\": \"30\",\n" +
            "    \"twName\": \"希臘\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"荷兰\",\n" +
            "    \"enName\": \"Netherlands\",\n" +
            "    \"id\": 10,\n" +
            "    \"mobileCode\": \"31\",\n" +
            "    \"twName\": \"荷蘭\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"比利时\",\n" +
            "    \"enName\": \"Belgium\",\n" +
            "    \"id\": 11,\n" +
            "    \"mobileCode\": \"32\",\n" +
            "    \"twName\": \"比利時\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"法国\",\n" +
            "    \"enName\": \"France\",\n" +
            "    \"id\": 12,\n" +
            "    \"mobileCode\": \"33\",\n" +
            "    \"twName\": \"法國\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"西班牙\",\n" +
            "    \"enName\": \"Spain\",\n" +
            "    \"id\": 13,\n" +
            "    \"mobileCode\": \"34\",\n" +
            "    \"twName\": \"西班牙\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"匈牙利\",\n" +
            "    \"enName\": \"Hungary\",\n" +
            "    \"id\": 14,\n" +
            "    \"mobileCode\": \"36\",\n" +
            "    \"twName\": \"匈牙利\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"意大利\",\n" +
            "    \"enName\": \"Italy\",\n" +
            "    \"id\": 15,\n" +
            "    \"mobileCode\": \"39\",\n" +
            "    \"twName\": \"義大利\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"罗马尼亚\",\n" +
            "    \"enName\": \"Rumania\",\n" +
            "    \"id\": 16,\n" +
            "    \"mobileCode\": \"40\",\n" +
            "    \"twName\": \"羅馬尼亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"瑞士\",\n" +
            "    \"enName\": \"Switzerland\",\n" +
            "    \"id\": 17,\n" +
            "    \"mobileCode\": \"41\",\n" +
            "    \"twName\": \"瑞士\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"奥地利\",\n" +
            "    \"enName\": \"Austria\",\n" +
            "    \"id\": 18,\n" +
            "    \"mobileCode\": \"43\",\n" +
            "    \"twName\": \"奧地利\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"英国\",\n" +
            "    \"enName\": \"U.K.\",\n" +
            "    \"id\": 19,\n" +
            "    \"mobileCode\": \"44\",\n" +
            "    \"twName\": \"英國\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"丹麦\",\n" +
            "    \"enName\": \"Denmark\",\n" +
            "    \"id\": 20,\n" +
            "    \"mobileCode\": \"45\",\n" +
            "    \"twName\": \"丹麥\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"瑞典\",\n" +
            "    \"enName\": \"Sweden\",\n" +
            "    \"id\": 21,\n" +
            "    \"mobileCode\": \"46\",\n" +
            "    \"twName\": \"瑞典\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"挪威\",\n" +
            "    \"enName\": \"Norway\",\n" +
            "    \"id\": 22,\n" +
            "    \"mobileCode\": \"47\",\n" +
            "    \"twName\": \"挪威\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"波兰\",\n" +
            "    \"enName\": \"Poland\",\n" +
            "    \"id\": 23,\n" +
            "    \"mobileCode\": \"48\",\n" +
            "    \"twName\": \"波蘭\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"德国\",\n" +
            "    \"enName\": \"Germany\",\n" +
            "    \"id\": 24,\n" +
            "    \"mobileCode\": \"49\",\n" +
            "    \"twName\": \"德國\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"秘鲁\",\n" +
            "    \"enName\": \"Peru\",\n" +
            "    \"id\": 25,\n" +
            "    \"mobileCode\": \"51\",\n" +
            "    \"twName\": \"秘魯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"墨西哥\",\n" +
            "    \"enName\": \"Mexico\",\n" +
            "    \"id\": 26,\n" +
            "    \"mobileCode\": \"52\",\n" +
            "    \"twName\": \"墨西哥\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"古巴\",\n" +
            "    \"enName\": \"Cuba\",\n" +
            "    \"id\": 27,\n" +
            "    \"mobileCode\": \"53\",\n" +
            "    \"twName\": \"古巴\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"阿根廷\",\n" +
            "    \"enName\": \"Argentina\",\n" +
            "    \"id\": 28,\n" +
            "    \"mobileCode\": \"54\",\n" +
            "    \"twName\": \"阿根廷\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"巴西\",\n" +
            "    \"enName\": \"Brazil\",\n" +
            "    \"id\": 29,\n" +
            "    \"mobileCode\": \"55\",\n" +
            "    \"twName\": \"巴西\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"智利\",\n" +
            "    \"enName\": \"Chile\",\n" +
            "    \"id\": 30,\n" +
            "    \"mobileCode\": \"56\",\n" +
            "    \"twName\": \"智利\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"哥伦比亚\",\n" +
            "    \"enName\": \"Colombia\",\n" +
            "    \"id\": 31,\n" +
            "    \"mobileCode\": \"57\",\n" +
            "    \"twName\": \"哥倫比亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"委内瑞拉\",\n" +
            "    \"enName\": \"Venezuela\",\n" +
            "    \"id\": 32,\n" +
            "    \"mobileCode\": \"58\",\n" +
            "    \"twName\": \"委內瑞拉\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马来西亚\",\n" +
            "    \"enName\": \"Malaysia\",\n" +
            "    \"id\": 33,\n" +
            "    \"mobileCode\": \"60\",\n" +
            "    \"twName\": \"馬來西亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"澳大利亚\",\n" +
            "    \"enName\": \"Australia\",\n" +
            "    \"id\": 34,\n" +
            "    \"mobileCode\": \"61\",\n" +
            "    \"twName\": \"澳大利亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"印尼\",\n" +
            "    \"enName\": \"Indonesia\",\n" +
            "    \"id\": 35,\n" +
            "    \"mobileCode\": \"62\",\n" +
            "    \"twName\": \"印尼\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"菲律宾\",\n" +
            "    \"enName\": \"Philippines\",\n" +
            "    \"id\": 36,\n" +
            "    \"mobileCode\": \"63\",\n" +
            "    \"twName\": \"菲律賓\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"新西兰\",\n" +
            "    \"enName\": \"New Zealand\",\n" +
            "    \"id\": 37,\n" +
            "    \"mobileCode\": \"64\",\n" +
            "    \"twName\": \"新西蘭\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"新加坡\",\n" +
            "    \"enName\": \"Singapore\",\n" +
            "    \"id\": 38,\n" +
            "    \"mobileCode\": \"65\",\n" +
            "    \"twName\": \"新加坡\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"泰国\",\n" +
            "    \"enName\": \"Thailand\",\n" +
            "    \"id\": 39,\n" +
            "    \"mobileCode\": \"66\",\n" +
            "    \"twName\": \"泰國\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"日本\",\n" +
            "    \"enName\": \"Japan\",\n" +
            "    \"id\": 40,\n" +
            "    \"mobileCode\": \"81\",\n" +
            "    \"twName\": \"日本\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"韩国\",\n" +
            "    \"enName\": \"Korea\",\n" +
            "    \"id\": 41,\n" +
            "    \"mobileCode\": \"82\",\n" +
            "    \"twName\": \"韓國\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"越南\",\n" +
            "    \"enName\": \"Vietnam\",\n" +
            "    \"id\": 42,\n" +
            "    \"mobileCode\": \"84\",\n" +
            "    \"twName\": \"越南\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"土耳其\",\n" +
            "    \"enName\": \"Turkey\",\n" +
            "    \"id\": 43,\n" +
            "    \"mobileCode\": \"90\",\n" +
            "    \"twName\": \"土耳其\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"印度\",\n" +
            "    \"enName\": \"India\",\n" +
            "    \"id\": 44,\n" +
            "    \"mobileCode\": \"91\",\n" +
            "    \"twName\": \"印度\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"巴基斯坦\",\n" +
            "    \"enName\": \"Pakistan\",\n" +
            "    \"id\": 45,\n" +
            "    \"mobileCode\": \"92\",\n" +
            "    \"twName\": \"巴基斯坦\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"阿富汗\",\n" +
            "    \"enName\": \"Afghanistan\",\n" +
            "    \"id\": 46,\n" +
            "    \"mobileCode\": \"93\",\n" +
            "    \"twName\": \"阿富汗\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"斯里兰卡\",\n" +
            "    \"enName\": \"Srilanka\",\n" +
            "    \"id\": 47,\n" +
            "    \"mobileCode\": \"94\",\n" +
            "    \"twName\": \"斯里蘭卡\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"缅甸\",\n" +
            "    \"enName\": \"Myanmar\",\n" +
            "    \"id\": 48,\n" +
            "    \"mobileCode\": \"95\",\n" +
            "    \"twName\": \"緬甸\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"伊朗\",\n" +
            "    \"enName\": \"Iran\",\n" +
            "    \"id\": 49,\n" +
            "    \"mobileCode\": \"98\",\n" +
            "    \"twName\": \"伊朗\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"摩洛哥\",\n" +
            "    \"enName\": \"Morocco\",\n" +
            "    \"id\": 50,\n" +
            "    \"mobileCode\": \"212\",\n" +
            "    \"twName\": \"摩洛哥\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"阿尔及利亚\",\n" +
            "    \"enName\": \"Algeria\",\n" +
            "    \"id\": 51,\n" +
            "    \"mobileCode\": \"213\",\n" +
            "    \"twName\": \"阿爾及利亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"突尼斯\",\n" +
            "    \"enName\": \"Tuisia\",\n" +
            "    \"id\": 52,\n" +
            "    \"mobileCode\": \"216\",\n" +
            "    \"twName\": \"突尼斯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"利比亚\",\n" +
            "    \"enName\": \"Libya\",\n" +
            "    \"id\": 53,\n" +
            "    \"mobileCode\": \"218\",\n" +
            "    \"twName\": \"利比亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"冈比亚\",\n" +
            "    \"enName\": \"Gambia\",\n" +
            "    \"id\": 54,\n" +
            "    \"mobileCode\": \"220\",\n" +
            "    \"twName\": \"岡比亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"塞内加尔\",\n" +
            "    \"enName\": \"Senegal\",\n" +
            "    \"id\": 55,\n" +
            "    \"mobileCode\": \"221\",\n" +
            "    \"twName\": \"塞內加爾\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"毛利塔尼亚\",\n" +
            "    \"enName\": \"Mauritania\",\n" +
            "    \"id\": 56,\n" +
            "    \"mobileCode\": \"222\",\n" +
            "    \"twName\": \"毛利塔尼亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马里\",\n" +
            "    \"enName\": \"Mali\",\n" +
            "    \"id\": 57,\n" +
            "    \"mobileCode\": \"223\",\n" +
            "    \"twName\": \"馬里\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"几内亚\",\n" +
            "    \"enName\": \"Guinea\",\n" +
            "    \"id\": 58,\n" +
            "    \"mobileCode\": \"224\",\n" +
            "    \"twName\": \"幾內亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"象牙海岸\",\n" +
            "    \"enName\": \"Ivory Coast\",\n" +
            "    \"id\": 59,\n" +
            "    \"mobileCode\": \"225\",\n" +
            "    \"twName\": \"象牙海岸\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"布基纳法索\",\n" +
            "    \"enName\": \"Burkinafaso\",\n" +
            "    \"id\": 60,\n" +
            "    \"mobileCode\": \"226\",\n" +
            "    \"twName\": \"布基納法索\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"尼日尔\",\n" +
            "    \"enName\": \"Niger\",\n" +
            "    \"id\": 61,\n" +
            "    \"mobileCode\": \"227\",\n" +
            "    \"twName\": \"尼日爾\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"多哥\",\n" +
            "    \"enName\": \"Togo\",\n" +
            "    \"id\": 62,\n" +
            "    \"mobileCode\": \"228\",\n" +
            "    \"twName\": \"多哥\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"贝宁\",\n" +
            "    \"enName\": \"Benin\",\n" +
            "    \"id\": 63,\n" +
            "    \"mobileCode\": \"229\",\n" +
            "    \"twName\": \"貝寧\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"毛里求斯\",\n" +
            "    \"enName\": \"Mauritius\",\n" +
            "    \"id\": 64,\n" +
            "    \"mobileCode\": \"230\",\n" +
            "    \"twName\": \"毛里求斯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"利比里亚\",\n" +
            "    \"enName\": \"Liberia\",\n" +
            "    \"id\": 65,\n" +
            "    \"mobileCode\": \"231\",\n" +
            "    \"twName\": \"利比理亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"塞拉利昂\",\n" +
            "    \"enName\": \"Sierra Leone\",\n" +
            "    \"id\": 66,\n" +
            "    \"mobileCode\": \"232\",\n" +
            "    \"twName\": \"塞拉里昂\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"加纳\",\n" +
            "    \"enName\": \"Ghana\",\n" +
            "    \"id\": 67,\n" +
            "    \"mobileCode\": \"233\",\n" +
            "    \"twName\": \"加納\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"尼日利亚\",\n" +
            "    \"enName\": \"Nigeria\",\n" +
            "    \"id\": 68,\n" +
            "    \"mobileCode\": \"234\",\n" +
            "    \"twName\": \"尼日利亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"乍得\",\n" +
            "    \"enName\": \"Chad\",\n" +
            "    \"id\": 69,\n" +
            "    \"mobileCode\": \"235\",\n" +
            "    \"twName\": \"乍得\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"非洲中部\",\n" +
            "    \"enName\": \"Central Africa\",\n" +
            "    \"id\": 70,\n" +
            "    \"mobileCode\": \"236\",\n" +
            "    \"twName\": \"非洲中部\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"喀麦隆\",\n" +
            "    \"enName\": \"Cameroon\",\n" +
            "    \"id\": 71,\n" +
            "    \"mobileCode\": \"237\",\n" +
            "    \"twName\": \"喀麥隆\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"佛得角\",\n" +
            "    \"enName\": \"Cape Verde\",\n" +
            "    \"id\": 72,\n" +
            "    \"mobileCode\": \"238\",\n" +
            "    \"twName\": \"佛得角\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"圣多美和普林西比\",\n" +
            "    \"enName\": \"Sao Tome And Principe\",\n" +
            "    \"id\": 73,\n" +
            "    \"mobileCode\": \"239\",\n" +
            "    \"twName\": \"聖多美和普林西比\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"赤道几内亚\",\n" +
            "    \"enName\": \"Equatorial Guinea\",\n" +
            "    \"id\": 74,\n" +
            "    \"mobileCode\": \"240\",\n" +
            "    \"twName\": \"赤道幾內亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"加蓬\",\n" +
            "    \"enName\": \"Gabon\",\n" +
            "    \"id\": 75,\n" +
            "    \"mobileCode\": \"241\",\n" +
            "    \"twName\": \"加蓬\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"刚果\",\n" +
            "    \"enName\": \"Congo\",\n" +
            "    \"id\": 76,\n" +
            "    \"mobileCode\": \"242\",\n" +
            "    \"twName\": \"剛果\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"刚果(金)\",\n" +
            "    \"enName\": \"Congo(Dem Rep)\",\n" +
            "    \"id\": 77,\n" +
            "    \"mobileCode\": \"243\",\n" +
            "    \"twName\": \"剛果(金)\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"安哥拉\",\n" +
            "    \"enName\": \"Angola\",\n" +
            "    \"id\": 78,\n" +
            "    \"mobileCode\": \"244\",\n" +
            "    \"twName\": \"安哥拉\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"几内亚比绍\",\n" +
            "    \"enName\": \"Guinea－Bissau\",\n" +
            "    \"id\": 79,\n" +
            "    \"mobileCode\": \"245\",\n" +
            "    \"twName\": \"幾內亞比紹\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"迪戈加西亚岛\",\n" +
            "    \"enName\": \"Diego Garcia\",\n" +
            "    \"id\": 80,\n" +
            "    \"mobileCode\": \"246\",\n" +
            "    \"twName\": \"迪戈加西亞島\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"提升\",\n" +
            "    \"enName\": \"Ascension\",\n" +
            "    \"id\": 81,\n" +
            "    \"mobileCode\": \"247\",\n" +
            "    \"twName\": \"提升\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"塞舌尔共和国\",\n" +
            "    \"enName\": \"Seychellies\",\n" +
            "    \"id\": 82,\n" +
            "    \"mobileCode\": \"248\",\n" +
            "    \"twName\": \"塞舌耳共和國\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"苏丹\",\n" +
            "    \"enName\": \"Sudan\",\n" +
            "    \"id\": 83,\n" +
            "    \"mobileCode\": \"249\",\n" +
            "    \"twName\": \"蘇丹\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"卢旺达\",\n" +
            "    \"enName\": \"Rwanda\",\n" +
            "    \"id\": 84,\n" +
            "    \"mobileCode\": \"250\",\n" +
            "    \"twName\": \"盧旺達\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"埃塞俄比亚\",\n" +
            "    \"enName\": \"Ethiopia\",\n" +
            "    \"id\": 85,\n" +
            "    \"mobileCode\": \"251\",\n" +
            "    \"twName\": \"埃塞俄比亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"索马里\",\n" +
            "    \"enName\": \"Somail\",\n" +
            "    \"id\": 86,\n" +
            "    \"mobileCode\": \"252\",\n" +
            "    \"twName\": \"索馬里\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"吉布提\",\n" +
            "    \"enName\": \"Djibouti\",\n" +
            "    \"id\": 87,\n" +
            "    \"mobileCode\": \"253\",\n" +
            "    \"twName\": \"吉布提\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"肯尼亚\",\n" +
            "    \"enName\": \"Kenya\",\n" +
            "    \"id\": 88,\n" +
            "    \"mobileCode\": \"254\",\n" +
            "    \"twName\": \"肯雅\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"坦桑尼亚\",\n" +
            "    \"enName\": \"Tanzania\",\n" +
            "    \"id\": 89,\n" +
            "    \"mobileCode\": \"255\",\n" +
            "    \"twName\": \"坦桑尼亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"乌干达\",\n" +
            "    \"enName\": \"Uganda\",\n" +
            "    \"id\": 90,\n" +
            "    \"mobileCode\": \"256\",\n" +
            "    \"twName\": \"烏干達\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"布隆迪\",\n" +
            "    \"enName\": \"Burundi\",\n" +
            "    \"id\": 91,\n" +
            "    \"mobileCode\": \"257\",\n" +
            "    \"twName\": \"布隆迪\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"莫桑比克\",\n" +
            "    \"enName\": \"Mozambique\",\n" +
            "    \"id\": 92,\n" +
            "    \"mobileCode\": \"258\",\n" +
            "    \"twName\": \"莫三比克\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"桑给巴尔岛\",\n" +
            "    \"enName\": \"Zanzibar\",\n" +
            "    \"id\": 93,\n" +
            "    \"mobileCode\": \"259\",\n" +
            "    \"twName\": \"桑吉巴島\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"赞比亚\",\n" +
            "    \"enName\": \"Zambia\",\n" +
            "    \"id\": 94,\n" +
            "    \"mobileCode\": \"260\",\n" +
            "    \"twName\": \"尚比亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马达加斯加\",\n" +
            "    \"enName\": \"Madagascar\",\n" +
            "    \"id\": 95,\n" +
            "    \"mobileCode\": \"261\",\n" +
            "    \"twName\": \"馬達加斯加\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"聚会是\",\n" +
            "    \"enName\": \"Reunion Is\",\n" +
            "    \"id\": 96,\n" +
            "    \"mobileCode\": \"262\",\n" +
            "    \"twName\": \"聚會是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"津巴布韦\",\n" +
            "    \"enName\": \"Zimbabwe\",\n" +
            "    \"id\": 97,\n" +
            "    \"mobileCode\": \"263\",\n" +
            "    \"twName\": \"辛巴威\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"纳米比亚\",\n" +
            "    \"enName\": \"Namibia\",\n" +
            "    \"id\": 98,\n" +
            "    \"mobileCode\": \"264\",\n" +
            "    \"twName\": \"納米比亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马拉维\",\n" +
            "    \"enName\": \"Malawi\",\n" +
            "    \"id\": 99,\n" +
            "    \"mobileCode\": \"265\",\n" +
            "    \"twName\": \"馬拉維\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"莱索托\",\n" +
            "    \"enName\": \"Lesotho\",\n" +
            "    \"id\": 100,\n" +
            "    \"mobileCode\": \"266\",\n" +
            "    \"twName\": \"萊索托\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"博茨瓦纳\",\n" +
            "    \"enName\": \"Botswana\",\n" +
            "    \"id\": 101,\n" +
            "    \"mobileCode\": \"267\",\n" +
            "    \"twName\": \"博茨瓦納\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"斯威士兰\",\n" +
            "    \"enName\": \"Swaziland\",\n" +
            "    \"id\": 102,\n" +
            "    \"mobileCode\": \"268\",\n" +
            "    \"twName\": \"斯威士蘭\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"科摩罗\",\n" +
            "    \"enName\": \"Comoro\",\n" +
            "    \"id\": 103,\n" +
            "    \"mobileCode\": \"269\",\n" +
            "    \"twName\": \"科摩羅\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"ST.HELENA\",\n" +
            "    \"enName\": \"ST.Helena\",\n" +
            "    \"id\": 104,\n" +
            "    \"mobileCode\": \"290\",\n" +
            "    \"twName\": \"ST.HELENA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"厄立特里亚\",\n" +
            "    \"enName\": \"Eritrea\",\n" +
            "    \"id\": 105,\n" +
            "    \"mobileCode\": \"291\",\n" +
            "    \"twName\": \"厄立特里亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"法罗是\",\n" +
            "    \"enName\": \"Faroe Is\",\n" +
            "    \"id\": 106,\n" +
            "    \"mobileCode\": \"298\",\n" +
            "    \"twName\": \"法羅是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"格陵兰岛是\",\n" +
            "    \"enName\": \"Greenland Is\",\n" +
            "    \"id\": 107,\n" +
            "    \"mobileCode\": \"299\",\n" +
            "    \"twName\": \"格陵蘭島是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"直布罗陀\",\n" +
            "    \"enName\": \"Gibraltar\",\n" +
            "    \"id\": 108,\n" +
            "    \"mobileCode\": \"350\",\n" +
            "    \"twName\": \"直布羅陀\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"葡萄牙\",\n" +
            "    \"enName\": \"Portugal\",\n" +
            "    \"id\": 109,\n" +
            "    \"mobileCode\": \"351\",\n" +
            "    \"twName\": \"葡萄牙\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"卢森堡\",\n" +
            "    \"enName\": \"Luxembourg\",\n" +
            "    \"id\": 110,\n" +
            "    \"mobileCode\": \"352\",\n" +
            "    \"twName\": \"盧森堡\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"爱尔兰\",\n" +
            "    \"enName\": \"Ireland\",\n" +
            "    \"id\": 111,\n" +
            "    \"mobileCode\": \"353\",\n" +
            "    \"twName\": \"愛爾蘭\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"冰岛\",\n" +
            "    \"enName\": \"Iceland\",\n" +
            "    \"id\": 112,\n" +
            "    \"mobileCode\": \"354\",\n" +
            "    \"twName\": \"冰島\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"阿尔巴尼亚\",\n" +
            "    \"enName\": \"Albania\",\n" +
            "    \"id\": 113,\n" +
            "    \"mobileCode\": \"355\",\n" +
            "    \"twName\": \"阿爾巴尼亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马耳他\",\n" +
            "    \"enName\": \"Malta\",\n" +
            "    \"id\": 114,\n" +
            "    \"mobileCode\": \"356\",\n" +
            "    \"twName\": \"馬爾他\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"塞浦路斯\",\n" +
            "    \"enName\": \"Cyprus\",\n" +
            "    \"id\": 115,\n" +
            "    \"mobileCode\": \"357\",\n" +
            "    \"twName\": \"賽普勒斯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"芬兰\",\n" +
            "    \"enName\": \"Finland\",\n" +
            "    \"id\": 116,\n" +
            "    \"mobileCode\": \"358\",\n" +
            "    \"twName\": \"芬蘭\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"保加利亚\",\n" +
            "    \"enName\": \"Bulgaria\",\n" +
            "    \"id\": 117,\n" +
            "    \"mobileCode\": \"359\",\n" +
            "    \"twName\": \"保加利亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"立陶宛\",\n" +
            "    \"enName\": \"Lithuania\",\n" +
            "    \"id\": 118,\n" +
            "    \"mobileCode\": \"370\",\n" +
            "    \"twName\": \"立陶宛\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"拉脱维亚\",\n" +
            "    \"enName\": \"Latvia\",\n" +
            "    \"id\": 119,\n" +
            "    \"mobileCode\": \"371\",\n" +
            "    \"twName\": \"拉脫維亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"爱沙尼亚\",\n" +
            "    \"enName\": \"Estionia\",\n" +
            "    \"id\": 120,\n" +
            "    \"mobileCode\": \"372\",\n" +
            "    \"twName\": \"愛沙尼亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"摩尔多瓦\",\n" +
            "    \"enName\": \"Mildova\",\n" +
            "    \"id\": 121,\n" +
            "    \"mobileCode\": \"373\",\n" +
            "    \"twName\": \"莫爾達瓦\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"亚美尼亚\",\n" +
            "    \"enName\": \"Armenia\",\n" +
            "    \"id\": 122,\n" +
            "    \"mobileCode\": \"374\",\n" +
            "    \"twName\": \"亞美尼亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"白俄罗斯\",\n" +
            "    \"enName\": \"Belarus\",\n" +
            "    \"id\": 123,\n" +
            "    \"mobileCode\": \"375\",\n" +
            "    \"twName\": \"白俄羅斯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"安道尔\",\n" +
            "    \"enName\": \"Andorra\",\n" +
            "    \"id\": 124,\n" +
            "    \"mobileCode\": \"376\",\n" +
            "    \"twName\": \"安道爾\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"摩纳哥\",\n" +
            "    \"enName\": \"Monaco\",\n" +
            "    \"id\": 125,\n" +
            "    \"mobileCode\": \"377\",\n" +
            "    \"twName\": \"摩納哥\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"圣马力诺\",\n" +
            "    \"enName\": \"San Marino\",\n" +
            "    \"id\": 126,\n" +
            "    \"mobileCode\": \"378\",\n" +
            "    \"twName\": \"聖馬力諾\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"乌克兰\",\n" +
            "    \"enName\": \"Ukraine\",\n" +
            "    \"id\": 127,\n" +
            "    \"mobileCode\": \"380\",\n" +
            "    \"twName\": \"烏克蘭\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"南斯拉夫\",\n" +
            "    \"enName\": \"Yugoslavia\",\n" +
            "    \"id\": 128,\n" +
            "    \"mobileCode\": \"381\",\n" +
            "    \"twName\": \"南斯拉夫\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"克罗地亚\",\n" +
            "    \"enName\": \"Croatia\",\n" +
            "    \"id\": 129,\n" +
            "    \"mobileCode\": \"385\",\n" +
            "    \"twName\": \"克羅地亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"斯洛文尼亚\",\n" +
            "    \"enName\": \"Slovenia\",\n" +
            "    \"id\": 130,\n" +
            "    \"mobileCode\": \"386\",\n" +
            "    \"twName\": \"斯洛文尼亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"波黑\",\n" +
            "    \"enName\": \"Bosnia And Herzegovina\",\n" +
            "    \"id\": 131,\n" +
            "    \"mobileCode\": \"387\",\n" +
            "    \"twName\": \"波黑\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马其顿\",\n" +
            "    \"enName\": \"Macedonija\",\n" +
            "    \"id\": 132,\n" +
            "    \"mobileCode\": \"389\",\n" +
            "    \"twName\": \"馬其頓\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"捷克\",\n" +
            "    \"enName\": \"Czech\",\n" +
            "    \"id\": 133,\n" +
            "    \"mobileCode\": \"420\",\n" +
            "    \"twName\": \"捷克\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"斯洛伐克语\",\n" +
            "    \"enName\": \"Slovak\",\n" +
            "    \"id\": 134,\n" +
            "    \"mobileCode\": \"421\",\n" +
            "    \"twName\": \"斯洛伐克語\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"福克兰是\",\n" +
            "    \"enName\": \"Falkland Is\",\n" +
            "    \"id\": 135,\n" +
            "    \"mobileCode\": \"500\",\n" +
            "    \"twName\": \"福克蘭是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"伯利兹\",\n" +
            "    \"enName\": \"Belize\",\n" +
            "    \"id\": 136,\n" +
            "    \"mobileCode\": \"501\",\n" +
            "    \"twName\": \"伯利茲\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"GUATEMAILA\",\n" +
            "    \"enName\": \"Guatemaila\",\n" +
            "    \"id\": 137,\n" +
            "    \"mobileCode\": \"502\",\n" +
            "    \"twName\": \"GUATEMAILA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"萨尔瓦多\",\n" +
            "    \"enName\": \"El Salvador\",\n" +
            "    \"id\": 138,\n" +
            "    \"mobileCode\": \"503\",\n" +
            "    \"twName\": \"薩爾瓦多\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"宏都拉斯\",\n" +
            "    \"enName\": \"Honduras\",\n" +
            "    \"id\": 139,\n" +
            "    \"mobileCode\": \"504\",\n" +
            "    \"twName\": \"宏都拉斯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"尼加拉瓜\",\n" +
            "    \"enName\": \"Nicaragua\",\n" +
            "    \"id\": 140,\n" +
            "    \"mobileCode\": \"505\",\n" +
            "    \"twName\": \"尼加拉瓜\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"哥斯达黎加\",\n" +
            "    \"enName\": \"Costa Rica\",\n" +
            "    \"id\": 141,\n" +
            "    \"mobileCode\": \"506\",\n" +
            "    \"twName\": \"哥斯大黎加\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"巴拿马\",\n" +
            "    \"enName\": \"Panama\",\n" +
            "    \"id\": 142,\n" +
            "    \"mobileCode\": \"507\",\n" +
            "    \"twName\": \"巴拿馬\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"圣皮埃尔和密克隆群岛\",\n" +
            "    \"enName\": \"ST.Pierre And Miquelon\",\n" +
            "    \"id\": 143,\n" +
            "    \"mobileCode\": \"508\",\n" +
            "    \"twName\": \"聖皮埃爾和密克隆群島\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"海地\",\n" +
            "    \"enName\": \"Haiti\",\n" +
            "    \"id\": 144,\n" +
            "    \"mobileCode\": \"509\",\n" +
            "    \"twName\": \"海地\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"瓜德罗普岛是\",\n" +
            "    \"enName\": \"Guadeloupe Is\",\n" +
            "    \"id\": 145,\n" +
            "    \"mobileCode\": \"590\",\n" +
            "    \"twName\": \"瓜德羅普島是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"玻利维亚\",\n" +
            "    \"enName\": \"Bolivia\",\n" +
            "    \"id\": 146,\n" +
            "    \"mobileCode\": \"591\",\n" +
            "    \"twName\": \"玻利維亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"GUYANY\",\n" +
            "    \"enName\": \"Guyany\",\n" +
            "    \"id\": 147,\n" +
            "    \"mobileCode\": \"592\",\n" +
            "    \"twName\": \"GUYANY\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"厄瓜多尔\",\n" +
            "    \"enName\": \"Ecuador\",\n" +
            "    \"id\": 148,\n" +
            "    \"mobileCode\": \"593\",\n" +
            "    \"twName\": \"厄瓜多爾\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"法属圭亚那\",\n" +
            "    \"enName\": \"French Guiana\",\n" +
            "    \"id\": 149,\n" +
            "    \"mobileCode\": \"594\",\n" +
            "    \"twName\": \"法屬圭亞那\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"巴拉圭\",\n" +
            "    \"enName\": \"Paraguay\",\n" +
            "    \"id\": 150,\n" +
            "    \"mobileCode\": \"595\",\n" +
            "    \"twName\": \"巴拉圭\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马提尼克岛\",\n" +
            "    \"enName\": \"Martinique\",\n" +
            "    \"id\": 151,\n" +
            "    \"mobileCode\": \"596\",\n" +
            "    \"twName\": \"馬提尼克島\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"苏里南\",\n" +
            "    \"enName\": \"Suriname\",\n" +
            "    \"id\": 152,\n" +
            "    \"mobileCode\": \"597\",\n" +
            "    \"twName\": \"蘇里南\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"乌拉圭\",\n" +
            "    \"enName\": \"Uruguay\",\n" +
            "    \"id\": 153,\n" +
            "    \"mobileCode\": \"598\",\n" +
            "    \"twName\": \"烏拉圭\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"诺福克是\",\n" +
            "    \"enName\": \"Norfolk Is\",\n" +
            "    \"id\": 154,\n" +
            "    \"mobileCode\": \"672\",\n" +
            "    \"twName\": \"諾福克是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"文莱\",\n" +
            "    \"enName\": \"Brunei\",\n" +
            "    \"id\": 155,\n" +
            "    \"mobileCode\": \"673\",\n" +
            "    \"twName\": \"汶萊\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"瑙鲁\",\n" +
            "    \"enName\": \"Nauru\",\n" +
            "    \"id\": 156,\n" +
            "    \"mobileCode\": \"674\",\n" +
            "    \"twName\": \"瑙魯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"巴布亚新几内亚\",\n" +
            "    \"enName\": \"Papua New Guinea\",\n" +
            "    \"id\": 157,\n" +
            "    \"mobileCode\": \"675\",\n" +
            "    \"twName\": \"巴布亞新幾內亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"汤加\",\n" +
            "    \"enName\": \"Tonga\",\n" +
            "    \"id\": 158,\n" +
            "    \"mobileCode\": \"676\",\n" +
            "    \"twName\": \"湯加\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"所罗门群岛\",\n" +
            "    \"enName\": \"Solomon Is\",\n" +
            "    \"id\": 159,\n" +
            "    \"mobileCode\": \"677\",\n" +
            "    \"twName\": \"所羅門群島\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"瓦努阿图\",\n" +
            "    \"enName\": \"Vanuatu\",\n" +
            "    \"id\": 160,\n" +
            "    \"mobileCode\": \"678\",\n" +
            "    \"twName\": \"瓦努阿圖\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"斐济\",\n" +
            "    \"enName\": \"Fiji\",\n" +
            "    \"id\": 161,\n" +
            "    \"mobileCode\": \"679\",\n" +
            "    \"twName\": \"斐濟\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"帕劳\",\n" +
            "    \"enName\": \"Palau\",\n" +
            "    \"id\": 162,\n" +
            "    \"mobileCode\": \"680\",\n" +
            "    \"twName\": \"帕勞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"库克是\",\n" +
            "    \"enName\": \"Cook Is\",\n" +
            "    \"id\": 163,\n" +
            "    \"mobileCode\": \"682\",\n" +
            "    \"twName\": \"庫克是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"纽埃岛是\",\n" +
            "    \"enName\": \"Niue Is\",\n" +
            "    \"id\": 164,\n" +
            "    \"mobileCode\": \"683\",\n" +
            "    \"twName\": \"紐埃島是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"萨摩亚、东部\",\n" +
            "    \"enName\": \"Samoa，Eastern\",\n" +
            "    \"id\": 165,\n" +
            "    \"mobileCode\": \"684\",\n" +
            "    \"twName\": \"薩摩亞、東部\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"萨摩亚,西方\",\n" +
            "    \"enName\": \"Samoa，Western\",\n" +
            "    \"id\": 166,\n" +
            "    \"mobileCode\": \"685\",\n" +
            "    \"twName\": \"薩摩亞,西方\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"基里巴斯\",\n" +
            "    \"enName\": \"Kiribati\",\n" +
            "    \"id\": 167,\n" +
            "    \"mobileCode\": \"686\",\n" +
            "    \"twName\": \"基裏巴斯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"图瓦卢\",\n" +
            "    \"enName\": \"Tuvalu\",\n" +
            "    \"id\": 168,\n" +
            "    \"mobileCode\": \"688\",\n" +
            "    \"twName\": \"圖瓦盧\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"法属波利尼西亚\",\n" +
            "    \"enName\": \"French Polynesia\",\n" +
            "    \"id\": 169,\n" +
            "    \"mobileCode\": \"689\",\n" +
            "    \"twName\": \"法屬波利尼西亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"托克劳是\",\n" +
            "    \"enName\": \"Tokelau Is\",\n" +
            "    \"id\": 170,\n" +
            "    \"mobileCode\": \"690\",\n" +
            "    \"twName\": \"托克勞是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"密克罗尼西亚\",\n" +
            "    \"enName\": \"Micronesia\",\n" +
            "    \"id\": 171,\n" +
            "    \"mobileCode\": \"691\",\n" +
            "    \"twName\": \"密克羅尼西亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马歇尔是\",\n" +
            "    \"enName\": \"Marshall Is\",\n" +
            "    \"id\": 172,\n" +
            "    \"mobileCode\": \"692\",\n" +
            "    \"twName\": \"馬歇爾是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"韩国\",\n" +
            "    \"enName\": \"Korea（Dpr Of）\",\n" +
            "    \"id\": 173,\n" +
            "    \"mobileCode\": \"850\",\n" +
            "    \"twName\": \"韓國\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"柬埔寨\",\n" +
            "    \"enName\": \"Cambodia\",\n" +
            "    \"id\": 174,\n" +
            "    \"mobileCode\": \"855\",\n" +
            "    \"twName\": \"柬埔寨\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"老挝\",\n" +
            "    \"enName\": \"Laos\",\n" +
            "    \"id\": 175,\n" +
            "    \"mobileCode\": \"856\",\n" +
            "    \"twName\": \"老撾\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"孟加拉国\",\n" +
            "    \"enName\": \"Bangladesh\",\n" +
            "    \"id\": 176,\n" +
            "    \"mobileCode\": \"880\",\n" +
            "    \"twName\": \"孟加拉\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马尔代夫\",\n" +
            "    \"enName\": \"Maldive\",\n" +
            "    \"id\": 177,\n" +
            "    \"mobileCode\": \"960\",\n" +
            "    \"twName\": \"馬爾代夫\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"黎巴嫩\",\n" +
            "    \"enName\": \"Lebanon\",\n" +
            "    \"id\": 178,\n" +
            "    \"mobileCode\": \"961\",\n" +
            "    \"twName\": \"黎巴嫩\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"约旦\",\n" +
            "    \"enName\": \"Jordan\",\n" +
            "    \"id\": 179,\n" +
            "    \"mobileCode\": \"962\",\n" +
            "    \"twName\": \"約旦\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"叙利亚\",\n" +
            "    \"enName\": \"Syria\",\n" +
            "    \"id\": 180,\n" +
            "    \"mobileCode\": \"963\",\n" +
            "    \"twName\": \"敘利亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"伊拉克\",\n" +
            "    \"enName\": \"Iraq\",\n" +
            "    \"id\": 181,\n" +
            "    \"mobileCode\": \"964\",\n" +
            "    \"twName\": \"伊拉克\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"科威特\",\n" +
            "    \"enName\": \"Kuwait\",\n" +
            "    \"id\": 182,\n" +
            "    \"mobileCode\": \"965\",\n" +
            "    \"twName\": \"科威特\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"沙特阿拉伯\",\n" +
            "    \"enName\": \"Saudi Arabia\",\n" +
            "    \"id\": 183,\n" +
            "    \"mobileCode\": \"966\",\n" +
            "    \"twName\": \"沙烏地阿拉伯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"也门\",\n" +
            "    \"enName\": \"Yemen\",\n" +
            "    \"id\": 184,\n" +
            "    \"mobileCode\": \"967\",\n" +
            "    \"twName\": \"葉門\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"阿曼\",\n" +
            "    \"enName\": \"Oman\",\n" +
            "    \"id\": 185,\n" +
            "    \"mobileCode\": \"968\",\n" +
            "    \"twName\": \"阿曼\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"阿联酋\",\n" +
            "    \"enName\": \"United Arab Emirates\",\n" +
            "    \"id\": 186,\n" +
            "    \"mobileCode\": \"971\",\n" +
            "    \"twName\": \"阿聯酋\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"以色列\",\n" +
            "    \"enName\": \"Israfi\",\n" +
            "    \"id\": 187,\n" +
            "    \"mobileCode\": \"972\",\n" +
            "    \"twName\": \"以色列\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"巴林\",\n" +
            "    \"enName\": \"Bahrani\",\n" +
            "    \"id\": 188,\n" +
            "    \"mobileCode\": \"973\",\n" +
            "    \"twName\": \"巴林\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"卡塔尔\",\n" +
            "    \"enName\": \"Qatar\",\n" +
            "    \"id\": 189,\n" +
            "    \"mobileCode\": \"974\",\n" +
            "    \"twName\": \"卡塔爾\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"不丹\",\n" +
            "    \"enName\": \"Bhutan\",\n" +
            "    \"id\": 190,\n" +
            "    \"mobileCode\": \"975\",\n" +
            "    \"twName\": \"不丹\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"蒙古\",\n" +
            "    \"enName\": \"Mongolia\",\n" +
            "    \"id\": 191,\n" +
            "    \"mobileCode\": \"976\",\n" +
            "    \"twName\": \"蒙古\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"尼泊尔\",\n" +
            "    \"enName\": \"Nepal\",\n" +
            "    \"id\": 192,\n" +
            "    \"mobileCode\": \"977\",\n" +
            "    \"twName\": \"尼泊爾\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"土库曼斯坦\",\n" +
            "    \"enName\": \"Turkmenistan\",\n" +
            "    \"id\": 193,\n" +
            "    \"mobileCode\": \"993\",\n" +
            "    \"twName\": \"土庫曼斯坦\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"阿塞拜疆\",\n" +
            "    \"enName\": \"Azerbaijan\",\n" +
            "    \"id\": 194,\n" +
            "    \"mobileCode\": \"994\",\n" +
            "    \"twName\": \"阿塞拜疆\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"乔治亚州\",\n" +
            "    \"enName\": \"Georgia\",\n" +
            "    \"id\": 195,\n" +
            "    \"mobileCode\": \"995\",\n" +
            "    \"twName\": \"喬治亞州\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"吉尔吉斯斯坦\",\n" +
            "    \"enName\": \"Kyrgyzstan\",\n" +
            "    \"id\": 196,\n" +
            "    \"mobileCode\": \"996\",\n" +
            "    \"twName\": \"吉爾吉斯斯坦\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"乌兹别克斯坦\",\n" +
            "    \"enName\": \"Uzbekistan\",\n" +
            "    \"id\": 197,\n" +
            "    \"mobileCode\": \"998\",\n" +
            "    \"twName\": \"烏茲別克斯坦\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"巴哈马群岛\",\n" +
            "    \"enName\": \"Bahamas\",\n" +
            "    \"id\": 198,\n" +
            "    \"mobileCode\": \"1242\",\n" +
            "    \"twName\": \"巴哈馬群島\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"巴巴多斯\",\n" +
            "    \"enName\": \"Barbados\",\n" +
            "    \"id\": 199,\n" +
            "    \"mobileCode\": \"1246\",\n" +
            "    \"twName\": \"巴巴多斯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"ANGULLIA IS\",\n" +
            "    \"enName\": \"Angullia Is\",\n" +
            "    \"id\": 200,\n" +
            "    \"mobileCode\": \"1264\",\n" +
            "    \"twName\": \"ANGULLIA IS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"安提瓜岛和巴布达\",\n" +
            "    \"enName\": \"Antigua And Barbuda\",\n" +
            "    \"id\": 201,\n" +
            "    \"mobileCode\": \"1268\",\n" +
            "    \"twName\": \"安提瓜島和巴布達\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"维珍群岛（英属）\",\n" +
            "    \"enName\": \"Virgin Is.(British)\",\n" +
            "    \"id\": 202,\n" +
            "    \"mobileCode\": \"1284\",\n" +
            "    \"twName\": \"維珍群島（英屬）\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"维珍群岛(美属)\",\n" +
            "    \"enName\": \"Virgin Is.(U.S.A)\",\n" +
            "    \"id\": 203,\n" +
            "    \"mobileCode\": \"1340\",\n" +
            "    \"twName\": \"維珍群島(美屬)\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"开曼群岛是\",\n" +
            "    \"enName\": \"Cayman Is\",\n" +
            "    \"id\": 204,\n" +
            "    \"mobileCode\": \"1345\",\n" +
            "    \"twName\": \"開曼群島是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"百慕大群岛是\",\n" +
            "    \"enName\": \"Bermuda Is\",\n" +
            "    \"id\": 205,\n" +
            "    \"mobileCode\": \"1441\",\n" +
            "    \"twName\": \"百慕大群島是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"格林纳达\",\n" +
            "    \"enName\": \"Grenada\",\n" +
            "    \"id\": 206,\n" +
            "    \"mobileCode\": \"1473\",\n" +
            "    \"twName\": \"格林納達\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"特克斯和凯科斯是\",\n" +
            "    \"enName\": \"Turks And Caicos Is\",\n" +
            "    \"id\": 207,\n" +
            "    \"mobileCode\": \"1649\",\n" +
            "    \"twName\": \"特克斯和凱科斯是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"蒙特塞拉特岛是\",\n" +
            "    \"enName\": \"Montserrat Is\",\n" +
            "    \"id\": 208,\n" +
            "    \"mobileCode\": \"1664\",\n" +
            "    \"twName\": \"蒙特塞拉特島是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"马里亚纳是\",\n" +
            "    \"enName\": \"Mariana Is\",\n" +
            "    \"id\": 209,\n" +
            "    \"mobileCode\": \"1670\",\n" +
            "    \"twName\": \"馬里亞納是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"关岛\",\n" +
            "    \"enName\": \"Guam\",\n" +
            "    \"id\": 210,\n" +
            "    \"mobileCode\": \"1671\",\n" +
            "    \"twName\": \"關島\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"墙壁瓦利斯群岛和富图纳群岛\",\n" +
            "    \"enName\": \"Walls And Futuna\",\n" +
            "    \"id\": 211,\n" +
            "    \"mobileCode\": \"1681\",\n" +
            "    \"twName\": \"牆壁瓦利斯群島和富圖納群島\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"多米尼加\",\n" +
            "    \"enName\": \"Dominica(Commomwealthof)\",\n" +
            "    \"id\": 212,\n" +
            "    \"mobileCode\": \"1767\",\n" +
            "    \"twName\": \"多明尼加\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"圣卢西亚\",\n" +
            "    \"enName\": \"ST.Lucia\",\n" +
            "    \"id\": 213,\n" +
            "    \"mobileCode\": \"1784\",\n" +
            "    \"twName\": \"聖盧西亞\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"波多黎各\",\n" +
            "    \"enName\": \"Puerto Rico\",\n" +
            "    \"id\": 214,\n" +
            "    \"mobileCode\": \"1787\",\n" +
            "    \"twName\": \"波多黎各\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"后是\",\n" +
            "    \"enName\": \"Wake Is\",\n" +
            "    \"id\": 215,\n" +
            "    \"mobileCode\": \"1808\",\n" +
            "    \"twName\": \"後是\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"多米尼加代表\",\n" +
            "    \"enName\": \"Dominican Rep\",\n" +
            "    \"id\": 216,\n" +
            "    \"mobileCode\": \"1809\",\n" +
            "    \"twName\": \"多明尼加代表\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"特立尼达和多巴哥\",\n" +
            "    \"enName\": \"Trinidad And Tobago\",\n" +
            "    \"id\": 217,\n" +
            "    \"mobileCode\": \"1868\",\n" +
            "    \"twName\": \"特立尼達和多巴哥\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"ST.CHRISTOPHER和尼维斯\",\n" +
            "    \"enName\": \"ST.Christopher And Nevis Is\",\n" +
            "    \"id\": 218,\n" +
            "    \"mobileCode\": \"1869\",\n" +
            "    \"twName\": \"ST.CHRISTOPHER和尼維斯\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"牙买加\",\n" +
            "    \"enName\": \"Jamaica\",\n" +
            "    \"id\": 219,\n" +
            "    \"mobileCode\": \"1876\",\n" +
            "    \"twName\": \"牙買加\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"列支敦斯登\",\n" +
            "    \"enName\": \"Liechtenstein\",\n" +
            "    \"id\": 220,\n" +
            "    \"mobileCode\": \"4175\",\n" +
            "    \"twName\": \"列支敦斯登\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cnName\": \"埃及\",\n" +
            "    \"enName\": \"Egypt\",\n" +
            "    \"id\": 221,\n" +
            "    \"mobileCode\": \"20\",\n" +
            "    \"twName\": \"埃及\"\n" +
            "  }\n" +
            "]";

    public static JSONArray getTelCountryInfo(){
        JSONArray jsonArray=JSONArray.parseArray(telCounTry);
        return jsonArray;
    }
}
