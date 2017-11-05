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
            <#include "../news/left_menu.ftl"/>

            <div id="admin_right">
                <div class="content_box" style="border:none">
                    <div class="position"><span>内容管理</span><span>&gt;</span><span>研究报告</span><span>&gt;</span><span>列表</span></div>
                    <div class="operating">
                        <div class="search f_l">
                            <form  action="" method="get">
                                标题
                                <input class="easy" name="title" type="text" size="50" value="${title?default("")}">

                                <button class="btn" type="submit"><span class="sch">搜 索</span></button>
                            </form>
                        </div>
                    </div>
                    <div class="content" style="min-height: 200px;">
                        <table class="list_table" style="font-size:13px;">
                            <thead>
                            <tr style="height:30px;">
                                <th width="140px" class="t_c">id</th>
                                <th width="80px">logo</th>
                                <th width="440px">标题</th>

                                <th width="80px" class="t_c">置顶</th>


                                <th>
                                    操作

                                </th>

                            </tr>
                            </thead>
                            <tbody>
                            <#list researchReportList as researchReport>
                                <tr >
                                    <td class="t_c">${researchReport.id?default("")}</td>

                                    <td>
                                        <img src="${researchReport.logo?default("")}" style="width:37px;height:37px;"/>
                                    </td>

                                    <td>${researchReport.title?default("")}</td>


                                    <td class="t_c">${researchReport.top?default("")}</td>


                                    <td objectId="${researchReport.id?default("")}">
                                    <a href="#" onclick="return false;" class="to_update">修改</a>
                                    <span>|</span>
                                    <a href="#" onclick="return false;" class="to_delete">删除</a>
                                    <span>|</span>
                                    <a href="#" onclick="">查看</a>
                                    <span>|</span>
                                    <a href="#" onclick="return false;" class="to_index">推荐到首页</a>


                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>

                    </div>
                    <input type="hidden" id="update-url" value="/cms/research-report/{id}/update"/>
                    <input type="hidden" id="delete-url" value="/cms/research-report/{id}/delete"/>
                    <input type="hidden" id="index-url" value="/cms/index/recommend/research-add"/>


                    ${pageDiv}
                </div>
            </div>
        </div>




    </div>

</div>

<script type="text/javascript">
		//DOM加载完毕执行
		$(document).ready(function(){
			$("#left_menu_research_report_list").addClass("selected");
		});
	</script>
<script type="text/javascript" charset="UTF-8" src="/cms/script/hfc/research/list.js"></script>


<div style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255);"></div></body></html>