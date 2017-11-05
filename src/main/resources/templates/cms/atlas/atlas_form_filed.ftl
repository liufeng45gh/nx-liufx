<table class="table_new" style="width:100%">
    <tbody>
    <tr><th width="20%"></th><td><span style="color:${KEY_RESULT_MESSAGE_COLOR?default("")};">${KEY_RESULT_MESSAGE?default("")}</span></td></tr>
    <tr>
        <th width="20%" style="text-align:right;">标题:</th>
        <td>
            <input id="title_input" class="form-control" name="title" style="display:inline-block;" value="${atlas.title?default("")}" />
            <label id="title_input_info" style="display:inline-block;">* 标题</label>
        </td>
    </tr>
    <tr>
        <th width="20%" style="text-align:right;">logo:</th>
        <td>
            <div class="logo_outer">
                <input type="file" class="addLogoInput" id="up_file" style="width:158px;"/>
                <img width="100%" height="100%" src="${atlas.logo?default("")}" id="logo_cover"/>
                <input type="hidden" id="logo_hidden" name="logo" value="${atlas.logo?default("")}"/>
            </div>
        </td>
    </tr>

    <tr>
        <th width="20%" style="text-align:right;">置顶:</th>
        <td>
            <input id="top_input" class="form-control" name="top" style="display:inline-block;" value="${atlas.top?default("0")}" />
        </td>
    </tr>







    <tr>
        <th></th>
        <td>
            <div class="btn btn-primary" id="submit-btn" >保存</div>
            <input type="hidden" name="id" value="${atlas.id?default("")}"/>
        </td>
    </tr>
</tbody>
</table>


