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
                    <div class="position"><span>首页管理</span><span>&gt;</span><span>艺术家</span><span>&gt;</span><span>推荐列表</span></div>

                    <div class="content" style="min-height: 200px;">
                        <table class="list_table" style="font-size:13px;">
                            <thead>
                            <tr style="height:30px;">
                                <th width="140px" class="t_c">艺术家id</th>
                                <th width="80px" class="t_c">置顶</th>
                                <th width="80px" class="t_c">名字</th>
                                <th width="80px">头像</th>
                                <th width="340px">简介</th>

                                <th>
                                    操作

                                </th>

                            </tr>
                            </thead>
                            <tbody>
                            <#list indexRecommendList as recommend>
                                <tr >
                                    <td class="t_c">${recommend.target.id?default("")}</td>

                                    <td class="t_c">${recommend.top?default("")}</td>

                                    <td class="t_c">${recommend.target.name?default("")}</td>

                                    <td>

                                        <img src="${recommend.target.avatar?default("")}" style="width:80px;height:80px;"/>
                                    </td>
                                    <td >${recommend.target.intro?default("")}</td>

                                    <td recommendId="${recommend.id?default("")}">
                                    <a href="#" onclick="return false;" class="to_update">修改置顶</a>
                                    <span>|</span>
                                    <a href="#" onclick="return false;" class="to_delete">删除</a>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>

                    </div>
                    <input type="hidden" id="update-url" value="/cms/index/recommend/artist-update"/>
                    <input type="hidden" id="delete-url" value="/cms/index/recommend/{id}/artist-delete"/>

                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
		//DOM加载完毕执行
		$(document).ready(function(){
			$("#left_menu_artist").addClass("selected");
		});
</script>
<script type="text/javascript" charset="UTF-8" src="/cms/script/hfc/index/artist_list.js"></script>


<div style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255);"></div>
</body>
</html>