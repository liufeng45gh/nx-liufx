<table class="table_new" style="width:100%">
    <tbody>
    <tr><th width="20%"></th><td><span style="color:${KEY_RESULT_MESSAGE_COLOR?default("")};">${KEY_RESULT_MESSAGE?default("")}</span></td></tr>
    <tr>
        <th width="20%" style="text-align:right;">描述:</th>
        <td>
            <input id="description_input" class="form-control" name="description" style="display:inline-block;" value="${entity.description?default("")}" />
            <label id="description_input_info" style="display:inline-block;">* 描述</label>
        </td>
    </tr>
    <tr>
        <th width="20%" style="text-align:right;">图片:</th>
        <td>
            <div class="logo_outer">
                <input type="file" class="addLogoInput" id="up_file" style="width:158px;"/>
                <img width="100%" height="100%" src="${entity.url?default("")}" id="logo_cover"/>
                <input type="hidden" id="logo_hidden" name="url" value="${entity.url?default("")}"/>
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
        <th></th>
        <td>
            <div class="btn btn-primary" id="submit-btn" >保存</div>
            <input type="hidden" name="id" value="${entity.id?default("")}"/>
            <input type="hidden" name="atlasId" value="${entity.atlasId?default("")}"/>
        </td>
    </tr>
</tbody>
</table>


