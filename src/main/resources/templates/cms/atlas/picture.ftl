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





<div class="position"><span>系统</span><span>|</span><span>海富映像</span><span>|</span><span>内容管理</span></div>
<div class="content form_content" >
    <table class="list_table" style="font-size:13px;">
        <thead>
        <tr style="height:30px;">
            <th width="140px" class="t_c">id</th>
            <th width="80px">图片</th>
            <th width="440px">描述</th>

            <th width="80px">置顶</th>
            <th>
                操作

            </th>

        </tr>
        </thead>
        <tbody>
        <#list pictureList as picture>
            <tr >
                <td class="t_c">${picture.id?default("")}</td>
                <td>
                    <img src="${picture.url?default("")}" style="width:37px;height:37px;"/>
                </td>

                <td>${picture.description?default("")}</td>


                <td class="t_c">${picture.top?default("")}</td>


                <td objectId="${picture.id?default("")}">
                <a href="#" onclick="return false;" class="to_update">修改</a>
                <span>|</span>
                <a href="#" onclick="return false;" class="to_delete">删除</a>



                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <input type="hidden" id="update-url" value="/cms/atlas/picture/{id}/update"/>
    <input type="hidden" id="delete-url" value="/cms/atlas/picture/{id}/delete"/>
</div>

<div class="content form_content" style="width:100%">


    <form action="/cms/atlas/picture/save" method="post" id="hfc-form">
        <#include "picture_form_filed.ftl"/>
    </form>
</div>





<script type="text/javascript" charset="UTF-8" src="/cms/script/hfc/atlas/picture.js"></script>
<script type="text/javascript" charset="UTF-8" src="/cms/script/hfc/news/logo_select.js"></script>

</body></html>