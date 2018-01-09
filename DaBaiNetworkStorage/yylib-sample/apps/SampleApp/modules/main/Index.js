var React = require('react');
var _ = require('lodash');
var {YYCreatePage} =  require('yylib-business');
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var React = require('react');
var { YYCol,YYRow,YYClass,YYPage,YYMenu,YYMenuItem,YYIcon,YYMenuSub
    ,YYPanel,YYDivide,YYToolbar ,YYButton,YYModal,YYUpload,YYMessage
    ,YYInputButton,YYDropdown,YYTable,YYProgress} = require('yylib-ui');
require('./css/index.css');
var URL = require('./Url');

/*账户信息*/
var UserData;
/*账户存储信息*/
var UserFiles;
/*账户显示文件夹地址*/
var UserFilesPath;

/*下拉菜单（文件排序）*/
const menu = (
    <YYMenu>
        <YYMenuItem>
            <a target="_blank" href="http://www.alipay.com/">文件名</a>
        </YYMenuItem>
        <YYMenuItem>
            <a target="_blank" href="http://www.taobao.com/">大小</a>
        </YYMenuItem>
        <YYMenuItem>
            <a target="_blank" href="http://www.tmall.com/">修改日期</a>
        </YYMenuItem>
    </YYMenu>
);


/*工具栏*/
var YYSearchCondition = require('yylib-business/search/YYSearchCondition');
var searchFields = [{
    key:'name',
    title:'姓名',
    oper:['eq','ne','gt','nu','ie'],
    uitype:'text'
},{
    key:'email',
    title:'邮箱',
    oper:['eq','cn','nc'],
    uitype:'text'
},{
    key:'birthday',
    title:'出生日期',
    oper:['lt','gt','ge'],
    uitype:'date'
},{
    key:'sex',
    title:'性别',
    oper:['lt','gt','ge'],
    uitype:'select'
}
]
var onSearch = function (type, condition) {
    console.log('--onSearch---', type, condition);
}

/*表格数据及筛选和排序*/
var columns = [{
    title: '文件名',
    dataIndex: 'name',
    // 指定确定筛选的条件函数
    // 这里是名字中第一个字是 value
    onFilter: (value, record) => record.name.indexOf(value) === 0,
    sorter: (a, b) => a.name.length - b.name.length,
}, {
    title: '大小',
    dataIndex: 'age',
    sorter: (a, b) => a.age - b.age,
}, {
    title: '修改日期',
    dataIndex: 'address',
    filterMultiple: false,
    onFilter: (value, record) => record.address.indexOf(value) === 0,
    sorter: (a, b) => a.address.length - b.address.length,
}];

var data = [{
    key: '1',
    name: '胡斌',
    age: 32,
    address: '南湖区湖底公园1号',
}, {
    key: '2',
    name: '胡彦祖',
    age: 42,
    address: '西湖区湖底公园12号',
}, {
    key: '3',
    name: '李大嘴',
    age: 32,
    address: '南湖区湖底公园123号',
}, {
    key: '4',
    name: '李秀莲大嘴哥',
    age: 32,
    address: '西湖区湖底公园123号',
}];

function onChange(pagination, filters, sorter) {
    // 点击分页、筛选、排序时触发
    console.log('各类参数是', pagination, filters, sorter);
}
/*个人信息的菜单栏*/
const Usermenu = (
    <YYMenu>
        <YYMenuItem>
            <a target="_blank" href="http://www.alipay.com/">个人资料</a>
        </YYMenuItem>
        <YYMenuItem>
            <a target="_blank" href="http://www.taobao.com/">开通VIP</a>
        </YYMenuItem>
        <YYMenuItem>
            <a target="_blank" href="http://www.taobao.com/">系统通知</a>
        </YYMenuItem>
        <YYMenuItem>
            <a target="_blank" href="http://www.tmall.com/">退出</a>
        </YYMenuItem>
    </YYMenu>
);
/*页面初始化*/
var Index = YYClass.create({
    /*导航栏*/
    getInitialState() {
        return {
            current: 'mail',
        };
    },
    handleClick(e) {
        console.log('click ', e);
        this.setState({
            current: e.key,
        });
    },
    /*单面板*/
    getDefaultProps: function () {
        return {
        }
    }
    ,panelChange:function(){
        if(arguments[0] == 'expand'){
            console.log('面板收起');
        }else if(arguments[0] == 'collapse'){
            console.log('面板展开');
        }
    },
    /*上传*/
    showModal:function() {
        this.setState({
            visible: true,
        });
    }
    ,handleOk() {
        console.log('点击了确定');
        this.setState({
            visible: false,
        });
    }
    ,handleCancel(e) {
        console.log(e);
        this.setState({
            visible: false,
        });
    }, onChange: function (info) {
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            YYMessage.success(`${info.file.name} 上传成功。`);
        } else if (info.file.status === 'error') {
            YYMessage.error(`${info.file.name} 上传失败。`);
        }
    },
    //新建文件夹点击事件
    newFiles:function () {
        console.log('新建文件夹');
    }
    ,render: function() {
        return (
            <YYPage className="layout-demo">
                <YYRow>
                    <YYCol span={4}>
                        <a href={URL.MAINPATH}>
                            <img id="logo" src="./images/db_logo.png"/>
                        </a>
                    </YYCol>

                    <YYCol span={17}>
                        <YYPage >
                            <YYMenu onClick={this.handleClick}
                                    selectedKeys={[this.state.current]}
                                    mode="horizontal"
                                    style={{marginTop:35}}
                            >
                                <YYMenuItem key="networkDisk">
                                    <span style={{fontSize:20}}>网盘</span>
                                </YYMenuItem>
                                <YYMenuItem key="share">
                                    <span style={{fontSize:20,marginLeft:10}}>分享</span>
                                </YYMenuItem>
                                <YYMenuItem key="more">
                                    <span style={{fontSize:20,marginLeft:10}}>更多</span>
                                </YYMenuItem>
                            </YYMenu>
                        </YYPage>
                    </YYCol>

                    <YYCol span={3}>
                        <YYPage style={{marginTop:23,float:"left",marginLeft:40}}>
                            <YYDropdown overlay={Usermenu}>
                                <span className="ant-dropdown-link" style={{fontSize:20}} href="#">
                                  欢迎您 :  {UserData.name} <YYIcon type="down" />
                                </span>
                            </YYDropdown>
                        </YYPage>

                    </YYCol>

                </YYRow>
                <YYPage id="leftPage" >
                    <YYMenu theme={this.state.theme}
                            onClick={this.handleClick}
                            style={{ width: 240 }}
                            defaultOpenKeys={['allFile']}
                            selectedKeys={[this.state.current]}
                            mode="inline"
                    >
                        <YYMenuSub key="allFile" title={<span><YYIcon type="cloud-o" /><span>我的网盘</span></span>}>
                            <YYMenuItem key="1"><YYIcon type="folder-open"/>所有文件</YYMenuItem>
                            <YYMenuItem key="2"><YYIcon type="picture"/>图片</YYMenuItem>
                            <YYMenuItem key="3"><YYIcon type="video-camera"/>视频</YYMenuItem>
                            <YYMenuItem key="4"><YYIcon type="file-text"/>文档</YYMenuItem>
                            <YYMenuItem key="5"><YYIcon type="file-unknown"/>种子</YYMenuItem>
                            <YYMenuItem key="6"><YYIcon type="customerservice"/>音乐</YYMenuItem>
                            <YYMenuItem key="7"><YYIcon type="file"/>其他</YYMenuItem>
                        </YYMenuSub>
                        <YYMenuSub key="manage" title={<span><YYIcon type="user" /><span>我的管理</span></span>}>
                            <YYMenuItem key="8"><YYIcon type="share-alt"/>我的分享</YYMenuItem>
                            <YYMenuItem key="9"><YYIcon type="delete"/>回收站</YYMenuItem>
                        </YYMenuSub>
                    </YYMenu>
                    <div id="capacity">
                        <span style={{fontSize:20}}>{UserData.fileSize}GB/{UserData.maxFileSize}GB</span>
                        <YYProgress type="circle" percent={parseFloat(UserData.fileSize)/parseFloat(UserData.maxFileSize)}  />
                    </div>
                </YYPage>


                <YYPage>
                    <YYPanel
                        expand={true}
                        title='网盘文件'
                        enableCollapse={true}
                        expandText='隐藏文件'
                        collapseText='显示文件'
                        onExpandOrCollapse={this.panelChange}
                        headClass=''
                        bodyClass=''>
                        <YYPage style={{background:'#ffffff',padding:10}}>
                            <YYToolbar>
                                <YYButton type="primary" onClick={this.showModal} icon="upload">上传</YYButton>
                                <YYModal title="上传文件" visible={this.state.visible}
                                         onOk={this.handleOk} onCancel={this.handleCancel}>
                                    <YYUpload
                                        name="file"
                                        action={URL.UPLOADFILE}
                                        data={{userName:UserData.name,path:UserFilesPath}}
                                        multiple = {true}
                                        // headers={{authorization: 'authorization-text'}}
                                        onChange={this.onChange}>
                                        <YYButton type="ghost">
                                            <YYIcon type="upload" />点击上传
                                        </YYButton>
                                    </YYUpload>
                                </YYModal>0
                                <YYButton type="error" icon="folder" ghost onClick={this.newFiles}>新建文件夹</YYButton>
                                <YYButton icon="download" ghost>下载</YYButton>
                                <YYDivide/>
                                <YYInputButton className="pull-right" style={{width:300}} buttonIcon="search" buttonClick={this._onSearchClick}/>
                                <YYDropdown overlay={menu}>
                                    <YYButton className="ant-dropdown-link" icon="bars"></YYButton>
                                </YYDropdown>
                                <YYDivide/>
                            </YYToolbar>
                        </YYPage>
                        <YYPage >
                            <YYTable columns={columns} dataSource={data} onChange={onChange}  pagination={false} />
                        </YYPage>
                    </YYPanel>
                </YYPage>
            </YYPage>
        )
    },_onSearchClick:function(text){
        console.log('搜索按钮被点击,搜索关键词:'+text);
    }
});

var MyParser = {};
MyParser.Index = Index;

//页面初始化
var EventHanlder = {
    "P004625":{
        onViewWillMount:function(options){
            console.log('page onViewWillMount',options);
            UserData = this.getRouteParams();
            console.log('UserData',UserData);
            UserFilesPath = UserData.name;
            var data = {
              userId : UserData._id,
              filePath : UserFilesPath,
            };
            ajax.postJSON(URL.GETUSERFILES,data,function (result) {
                UserFiles = result;
            });

        }
        ,onViewDidMount:function(options){
            console.log('page onViewDidMount',options);
        }
        ,onViewWillUpdate:function(options){
            console.log('page onViewWillUpdate',options);
        }
        ,onViewDidUpdate:function(options){
            console.log('page onViewDidUpdate',options);
        }
    }
}
var SimplePage = YYClass.create({
    render:function(){
        return <YYCreatePage {...this.props} appCode="A000776" pageCode="P004625" uiEvent={EventHanlder}
                             uiParser={MyParser}/>
    }
});
module.exports = SimplePage;


