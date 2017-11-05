<table class="table_new" style="width:100%">
    <tbody>
    <tr><th width="20%"></th><td><span style="color:${KEY_RESULT_MESSAGE_COLOR?default("")};">${KEY_RESULT_MESSAGE?default("")}</span></td></tr>
    <tr>
        <th width="20%" style="text-align:right;">名字:</th>
        <td>
            <input id="name_input" class="form-control" name="name" style="display:inline-block;" value="${artist.name?default("")}" />
            <label id="name_input_info" style="display:inline-block;">* 名字</label>
        </td>
    </tr>
    <tr>
        <th width="20%" style="text-align:right;">头像:</th>
        <td>
            <div class="logo_outer">
                <input type="file" class="addLogoInput" id="up_file" style="width:158px;"/>
                <img width="100%" height="100%" src="${artist.avatar?default("")}" id="logo_cover"/>
                <input type="hidden" id="logo_hidden" name="avatar" value="${artist.avatar?default("")}"/>
            </div>
        </td>
    </tr>
    <tr>
        <th width="20%" style="text-align:right;">标签:</th>
        <td>
            <input id="tag_input" class="form-control" name="tag" style="display:inline-block;" value="${artist.tag?default("")}" />
        </td>
    </tr>
    <tr>
        <th width="20%" style="text-align:right;">置顶:</th>
        <td>
            <input id="top_input" class="form-control" name="top" style="display:inline-block;" value="${artist.top?default("0")}" />
        </td>
    </tr>



    <tr>
        <th width="20%" style="text-align:right;">简介:</th>
        <td>
            <textarea id="intro_area" cols="130" rows="8" name="intro" >${artist.intro?default("")?html}</textarea>
        </td>
    </tr>

    <tr>
        <th width="20%" style="text-align:right;">详情:</th>
        <td>


            <textarea id="editor" style="width:1024px;height:500px;" name="detail">${artist.detail?default("")?html}</textarea>

        </td>
    </tr>

    <tr>
        <th></th>
        <td>
            <div class="btn btn-primary" id="submit-btn" >保存</div>
            <input type="hidden" name="id" value="${artist.id?default("")}"/>
        </td>
    </tr>
</tbody>
</table>


