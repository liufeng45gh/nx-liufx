<table class="table_new" style="width:100%">
    <tbody>
    <tr><th width="20%"></th><td><span style="color:${KEY_RESULT_MESSAGE_COLOR?default("")};">${KEY_RESULT_MESSAGE?default("")}</span></td></tr>
    <tr>
        <th width="20%" style="text-align:right;">标题:</th>
        <td><input id="title_input" class="form-control" name="title" style="display:inline-block;" value="${entity.title?default("")}" /><label id="title_input_info" style="display:inline-block;">* 标题</label></td>
    </tr>
    <tr>
        <th width="20%" style="text-align:right;  ">logo:</th>
        <td>
            <div class="logo_outer">
                <input type="file" class="addLogoInput" id="up_file" style="width:158px;"/>
                <img width="100%" height="100%" src="${entity.logo?default("")}" id="logo_cover"/>
                <input type="hidden" id="logo_hidden" name="logo" value="${entity.logo?default("")}"/>
            </div>
        </td>
    </tr>
    <tr>
        <th width="20%" style="text-align:right;">置顶:</th>
        <td>
            <input id="top_input" class="form-control" name="top" style="display:inline-block;" value="${entity.top?default("0")}" />
        </td>
    </tr>

    <tr>
        <th width="20%" style="text-align:right;">是否公开:</th>
        <td>
            <select id="is_open_input"  class="form-control" style="display:inline-block;width:280px;" name="isOpen" >
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
            <label id="is_open_input_info" style="display:inline-block;">* 是否公开</label>
        </td>
    </tr>


    <tr>
        <th width="20%" style="text-align:right;">发布时间:</th>
        <td><input id="publish_at_input"  class="form-control" name="publishAt" style="display:inline-block;" value="${(entity.publishAt?string("yyyy-MM-dd HH:mm:ss"))!}"/></td>
    </tr>

    <tr>
        <th width="20%" style="text-align:right;">附件:</th>
        <td>
            <a href="${entity.appendixUrl?default("")}" id="appendix_url_href">
                <#if entity.appendixUrl?default("") = "">

                未上传
                <#else>
                已上传
                </#if>
             </a>
            <input id="appendix_input" class="form-control"  type="file" style="display:inline-block;" value="选择附件" />
            <input type="hidden" name="appendixUrl" id="appendix_url" value="${entity.appendixUrl?default("")}"/>
        </td>
    </tr>

    <tr>
        <th width="20%" style="text-align:right;">摘要:</th>
        <td>
            <textarea id="summary_area" cols="130" rows="8" name="summary" >${entity.summary?default("")?html}</textarea>
        </td>
    </tr>

    <tr>
        <th width="20%" style="text-align:right;">详情:</th>
        <td>


            <textarea id="editor" style="width:1024px;height:500px;" name="content">${entity.content?default("")?html}</textarea>

        </td>
    </tr>

    <tr>
        <th></th>
        <td>
            <div class="btn btn-primary" id="submit-btn" >保存</div>
            <input type="hidden" name="id" value="${entity.id?default("")}"/>
        </td>
    </tr>
    </tbody>
</table>

<script>
$(document).ready(function () {
    $('#publish_at_input').datetimepicker({
         showSecond: true,
         timeFormat: 'hh:mm:ss'
    });
});

</script>
