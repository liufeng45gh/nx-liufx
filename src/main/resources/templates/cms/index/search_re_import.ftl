<!DOCTYPE html>
<html>
<head>
    <title>后台管理</title>
    <#include "../common_header.ftl"/>
</head>
<body style="zoom: 1;">
<div class="b-container">
    <#include "../top_menu.ftl"/>
    <#include "../quick_menu.ftl"/>
    <div id="wrap">
        <div class="outer with-side with-transition" style="min-height: 600px;">
            <#include "left_menu.ftl"/>

            <div id="admin_right">
                <div class="content_box" style="border:none">
                    <div class="position"><span>首页管理</span><span>&gt;</span><span>搜索引擎导入</span><span>&gt;</span><span>操作列表</span></div>

                    <div class="content" style="min-height: 200px;">
                        <table class="list_table" style="font-size:13px;">

                            <tbody>

                            <tr >
                                <td class="t_c"> <button class="btn btn-primary" type="submit" id="btn-news-re-import">从新导入新闻搜索数据</button></td>

                            </tr>
                            <tr >
                                <td class="t_c"> <button class="btn btn-primary" type="submit" id="btn-artist-re-import">从新导入艺术家搜索数据</button></td>

                            </tr>
                            <tr >
                                <td class="t_c"> <button class="btn btn-primary" type="submit" id="btn-appreciate-re-import">从新导入鉴赏搜索数据</button></td>

                            </tr>

                    </tbody>
                </table>

            </div>
            <input type="hidden" id="news-url" value="/cms/news/search/re-import"/>
            <input type="hidden" id="artist-url" value="/cms/artist/search/re-import"/>
              <input type="hidden" id="appreciate-url" value="/cms/appreciate/search/re-import"/>


        </div>
    </div>
</div>




</div>

</div>

<script type="text/javascript">
		//DOM加载完毕执行
		$(document).ready(function(){
			$("#left_menu_search_import").addClass("selected");


		});
	</script>
<script type="text/javascript" charset="UTF-8" src="/cms/script/hfc/index/search_re_import.js"></script>


<div style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255);"></div></body></html>