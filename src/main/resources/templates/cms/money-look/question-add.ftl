<!DOCTYPE html>
<html>
<head>
	<title>后台管理</title>
    <#include "../common_header.ftl"/>





    <script type="text/javascript" charset="utf-8" src="/ueditor-1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor-1.4.3.3/_examples/editor_api.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor-1.4.3.3/lang/zh-cn/zh-cn.js"></script>






</head>
<body style="zoom: 1;">
	<div class="b-container">
		<#include "../top_menu.ftl"/>
        <#include "../quick_menu.ftl"/>
        <div id="wrap">
             <div class="outer with-side with-transition" style="min-height: 600px;">
                    <#include "../news/left_menu.ftl"/>

                    <div id="admin_right">
                    			<div class="position"><span>系统</span><span>|</span><span>一元钱看</span><span>|</span><span>新增问题</span></div>
                    			<div class="content form_content" style="width:100%">


                                    <form action="/cms/money-look/question-add" method="post" id="hfc-form">
                                        <#include "question-form-filed.ftl"/>
                                    </form>
                                </div>
                    </div>
             </div>
        </div>
	</div>

	<script type="text/javascript">
		//DOM加载完毕执行
		$(document).ready(function(){
			$("#left_menu_question_add").addClass("selected");
			 var ue = UE.getEditor('editor');
		});
	</script>
    <script type="text/javascript" charset="UTF-8" src="/cms/script/hfc/money-look/question-add.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/cms/script/hfc/news/logo_select.js"></script>

<div style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255);"></div></body></html>