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
                    <div class="news-title">公司架构</div>
                    <div class="news-content">
                        <img src="/web/images/structure.png"/>

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
	$('#menu-item-company-structure').addClass("active");
});

</script>


</body>
</html>