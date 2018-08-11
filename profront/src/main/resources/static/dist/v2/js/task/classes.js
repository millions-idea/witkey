/**
 * 类目 韦德 2018年8月10日21:04:47
 */
class CategoryNavigationView extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div id="slide-cat">
                <ul>
                    <li className='category-title'>商品类目：</li>
                    <li><a href='/task' className='act'>全部类目</a></li>
                    {
                        this.props.list.map((item, index) => {
                            return <li key={index}>
                                <a href={'/task?categoryId=' + item.category_id}>{item.name}</a>
                            </li>
                        })
                    }
                </ul>
                <ul>
                    <li>商品排序：</li>
                    <li>
                        <a href="/task?orderBy=0&order=1" name="orderBy" className="act">
                            最新 ↓ </a>
                    </li>
                    <li>
                        <a href="/task?orderBy=1&order=1" name="orderBy"  className="">
                            价值 ↓ </a>
                    </li>
                    <li>
                        <a href="/task?orderBy=2&order=2" name="orderBy"  className="">
                            奖励 ↓ </a>
                    </li>
                    <li>
                        <form method="post" action="http://001.topaaa.com/yptask/?catid=4" className="yptasksearch">
                            <input name="itemid" className="c32" id="taskinfo" placeholder="试用编号搜索..."
                                   type="text"></input>
                                <input type="submit" id="taskid" defaultValue=""></input>
                        </form>
                    </li>
                    <li className="fr"><a id="active-view">切换视图</a></li>
                    <li className="fr"><a id="autorefresh" className="tips" data-hasqtip="366" oldtitle="点击自动刷新" title=""
                                      aria-describedby="qtip-366">自动刷新</a></li>
                </ul>
            </div>
        );
    }
}

/***
 * 商品 韦德 2018年8月10日21:04:43
 */
class ProductDataListView extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div id="product-items">
                {
                    this.props.list.map((item, index) => {
                        return <div key={index} className={((index+1) / 5 % 1) == 0 ? 'c4 act' : 'c4'}>
                                <div className="c41">
                                    <div className="c42">
                                        <span className="addtime">发布时间：{item.add_date}</span>
                                        <img src={'/dist/img/' + item.platform_icon} align="absmiddle" width="16" height="16" data-hasqtip="367" oldtitle={item.platform_name}></img>
                                        &nbsp;&nbsp;试用编号：{item.product_id}
                                    </div>

                                    <div className="c43">
                                        <div className="my1">
                                            <img
                                                src={item.image_url}
                                                className="c46"
                                                data-qtip-img={item.image_url}
                                                data-qtip-img-width="0" data-qtip-img-height="300" data-hasqtip="691"
                                                oldtitle={item.product_name} title="" aria-describedby="qtip-691"></img>
                                                <div className="none">{item.product_name}</div>
                                        </div>
                                        <div className="my2">
                                            <ul>
                                                <li>
                                                    发布商家：<span className="f_red">{item.merchant_name}</span>
                                                </li>
                                                <li>
                                                    <span className="shiyong_hide">试用</span>金额：<span className="f_red"
                                                                                                 data-hasqtip="369"
                                                                                                 oldtitle="试用的下单金额"
                                                                                                 title="">{item.price}</span> 元
                                                </li>
                                                <li>
                                                    <span className="shiyong_hide">悬赏</span>奖励：<span className="f_red"
                                                                                                 data-hasqtip="370"
                                                                                                 oldtitle="完成试用后，您能获得的试用奖励"
                                                                                                 title="">{item.return_price}</span> 元
                                                </li>
                                            </ul>
                                            <div className="requirements">
                                                {
                                                    item.attributes == null ? "" : item.attributes.map((attr,index) => {
                                                        return <em key={index} className={attr.className}  oldtitle={attr.desc} title="">{attr.name}</em>
                                                    })
                                                }
                                            </div>
                                        </div>
                                        <div className="my3">
                                            <a className="qrw_btn button border-blue" href="javascript:void(0)" ischeck="0" data-itemid={item.product_id} name="apply">申请试用</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        })
                    }
            </div>
        )
    }
}

/**
 * 买号 韦德 2018年8月11日00:09:03
 */
class BuyAccountDataTableView extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div id="apply-items" className="my-table">
                <table className="layui-table my-table">
                    <colgroup>
                        <col width="150"/>
                        <col width="150"/>
                        <col width="200"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>等级</th>
                        <th>用户名</th>
                        <th>请选择</th>
                    </tr>
                    </thead>
                    <tbody id="buy-accounts-items">
                    {
                        this.props.list.map((item,index)=>{
                            return <tr key={index}>
                                <td><span className={'xin' + item.level}></span></td>
                                <td><em className="iyellow">{item.username}</em></td>
                                <td><button className="layui-btn layui-btn-primary layui-btn-sm" data-id={item.account_id}><i className="layui-icon"></i></button></td>
                            </tr>
                        })
                    }
                    </tbody>
                </table>
                <div className="layui-row layui-col-md12 layui-center my-buy-block">
                    <a href="#" className="my-buy-link">添加买号</a>
                </div>
            </div>
        );
    }
}

/**
 * 试用分类 韦德 2018年8月11日14:48:02
 */
class TryTypeSelectView extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        return (
            <React.Fragment>
                <select id="catid_1" key={'current-type-' + this.props.currentType} defaultValue={this.props.currentType}>
                    {
                        this.props.list.map((item,index) => {
                            return <option key={item.id} value={item.id}>
                                {item.type_name}
                            </option>
                        })
                    }
                </select>
                <span id="dcatid" className="f_red"></span>
            </React.Fragment>
        )
    }
}

/**
 * 卖家账号 韦德 2018年8月11日15:15:42
 */
class SellAccountSelectView extends React.Component {
    constructor(props){
        super(props);
    }

    render(){
        return (
            <div id="sell-account-items">
                <select name="post_fields[tname]" id="tname" >
                    {
                        this.props.list.map((item,index) => {
                            return <option key={index} defaultValue={item.account_id}>{item.name}</option>
                        })
                    }
                </select> <span id="dtname" className="f_red"></span>
                <span className="note">就是您想提升信誉的掌柜，申请试用的朋友用来确认您的身份，<a
                    href="/member/taobaohao.php?isshangjia=1"
                    target="_blank">【点击管理掌柜】</a></span>
            </div>
        )
    }
}

/**
 * 商品分类 韦德 2018年8月11日15:35:45
 */
class CategorySelectListView extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        return (
            <div id="category-selector-items">
                <select name="post_fields[goods_cat]" id="goods_cat" defaultValue='1'>
                    <option value="">请选择</option>
                    {
                        this.props.list.map((item,index)=>{
                            return <option key={index} value={item.category_id}>{item.name}</option>
                        })
                    }
                </select>
                <span className="f_red" id="dgoods_cat"></span><span className="note"></span>
            </div>
        )
    }
}

/**
 * 试用页商品属性 韦德 2018年8月11日16:49:04
 */
class TryAttributesView extends React.Component{
    constructor(props){
        super(props)
    }

    /**
     * 动态渲染样式
     * @param style
     * @param attr
     * @param option
     * @param index
     */
    switchStyle = (style, attr, option, index) => {
        let component;
        switch(style){
            case 0:
                component = this.radioButton(attr, option, index);
                break;
            case 2:
                component = this.textareaEditor(attr, option, index);
                break;
            case 3:
                component = this.imageUpload(attr, option, index);
                break;
        }
        return component;
    }


    /**
     * 单选框样式
     * @param option
     * @param attr
     * @param index
     * @returns {*}
     */
    radioButton = (attr, option, index) => {
        return (
            <React.Fragment>
                <input type="radio" name={"attr_" + option.attr_id} value={option.option_id} id={option.option_id} defaultChecked={option.default_value.length <= 0} data-autohide="comment" />
                <label>{option.value}</label>
                {
                    this.appendPriceLabel(attr, option)
                }
            </React.Fragment>
        )
    };


    /**
     * 加价标签
     * @param attr
     * @param option
     * @returns {*}
     */
    appendPriceLabel = (attr, option) => {
        let component;

        if(attr.append_return_price != null && option.default_value.length <= 0){
            component = (
                <span className="note">选中增加 <b>{attr.append_return_price}</b> 元奖励</span>
            )
        }else{
            component = (
                ""
            )
        }

        return component;
    }


    /**
     * 编辑框样式
     * @param option
     * @param attr
     * @param index
     * @returns {*}
     */
    textareaEditor = (attr, option, index) => {
        return (
            <React.Fragment>
                <textarea className="my-textarea" name="post_fields[msg]" rows="2" cols="80" maxLength="200"></textarea>
            </React.Fragment>
        )
    };


    /**
     * 上传图像组件
     * @param attr
     * @param option
     * @param index
     * @returns {*}
     */
    imageUpload = (attr, option, index) => {
        return (
            <React.Fragment>
                <div className="img_upload">
                    <div className="img_upload_item">
                        <input shaitu="1" type="hidden" value="" name="post_fields[shaitu]" id="thumb_shaitu_1"></input>
                        <span className="my-image-upload-span">
                            <img src="http://001.topaaa.com/skin/ypmili/image/waitpic.png" id="showthumb_shaitu_1" alt=""  height="100" width="100" data-hasqtip="15" oldtitle="预览图片" title="" />
                        </span>
                        <span>
                            <img src="/member/image/img_upload.gif"  height="12" width="12" data-hasqtip="16" oldtitle="上传" title="" />
                                &nbsp;&nbsp;
                                <img src="/member/image/img_select.gif"  height="12" width="12" data-hasqtip="17" oldtitle="预览" title="" />
                                    &nbsp;&nbsp;
                                    <img src="/member/image/img_delete.gif" height="12" width="12" data-hasqtip="18" oldtitle="删除" title="" />
                        </span>
                    </div>
                </div>
                <div className="my-image-upload-button">
                    <span className="ele_add">增加</span>
                    <span className="ele_del">删除</span>
                </div>
                <span className="f_red" id="dshaitu"></span>
                <span className="note"></span>
            </React.Fragment>
        )
    };

    render(){
        return (
            <React.Fragment>
                {
                    this.props.list.map((attr,attrIndex) => {
                        return <tr key={attr.attr_id} id={"attr_" + attr.attr_id} >
                                <td className="tl">
                                    <span className="title" oldtitle={attr.desc}>{attr.name}</span>
                                </td>
                                <td className="tr">
                                    <span className="my-td-span">
                                    {
                                        attr.options.map((option,optionIndex) => {
                                            return this.switchStyle(option.style, attr, option,optionIndex);
                                        })
                                    }
                                    </span>
                                </td>
                            </tr>
                    })
                }
            </React.Fragment>
        )
    }
}