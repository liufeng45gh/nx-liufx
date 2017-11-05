<!DOCTYPE html>
<html>
<head>
    <#include "../c-head.ftl"/>
    <link rel='stylesheet' href='/web/css/news.css' type='text/css' media='screen' />

</head>

<body>
<div class="web-main page-min-width">


    <#include "../c-top.ftl"/>



    <div class="third-block">
        <div class="info-fr wrapper-996">
            <div class="finance-block news-left">
                <div class="news-menu">
                    <#include "menu.ftl"/>

                </div>


                <div class="finance-list news-list" id="news-list">
                    <div class="news-title">公司简介</div>
                    <div class="news-content">
                        北京海富国际文化艺术有限责任公司，是一家从源头上进行艺术家培育、从根本上改变艺术品流通方式和提高艺术品流通效率的多功能综合性投资管理服务集团。创建了“一个中心、两个确保、四大优势、八大平台”的文化金融全产业链“一二四八” 艺术品流通平台，即：以艺术品流通为中心；确保艺术品质量、确保艺术品流通信用；突出海富国内外政商资源优势、海富南方媒体集团资源优势、金融联盟资源优势、王府井国际文化艺术金融中心优势；构建政商资源整合平台、艺术家孵化平台、大数据认证平台、估值鉴定平台、媒体推广平台、展览展示平台、拍卖交易平台、金融服务平台。目前，北京海富国际文化艺术有限责任公司投资60亿人民币在北京最繁华的王府井金融商业区建设了一座面积为5万多平米的艺术品交易大楼。这座建筑，将是包含艺术金融博物馆、展览大厅、交易大厅、多功能文化厅、戏剧舞台、艺术品展销区、国际艺术交流厅、文化艺术讲坛、媒体中心、艺术品金融服务中心、鉴定中心、拍卖中心等和综合办公区在内的综合性服务平台。计划于2017年底前部分投入使用，并将实现年交易额300亿人民币。致力于打造中国乃至世界最高端、影响力最大的文化艺术流通服务平台，从而实现艺术品投资、产业、文化、社会效益最大化和可持续发展目标。

                    </div>

                </div>

                <input type="hidden" id="search-more-url" value="/news/search-list">
                <div class="fill-bottom">&nbsp;</div>

            </div>

            <#include "right_part.ftl"/>


        </div>
    </div>




</div>

<script type="text/javascript">
$(function() {
	$('#menu-item-company-intro').addClass("active");
});

</script>


</body>
</html>