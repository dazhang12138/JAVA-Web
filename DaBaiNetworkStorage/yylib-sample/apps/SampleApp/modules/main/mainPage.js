var React = require('react');
var _ = require('lodash');
var {YYClass,YYCol,YYPage,YYMenu,YYMenuItem,YYDropdown,YYIcon,YYModal,YYUpload,YYButton,YYMessage} = require('yylib-ui');
var {YYCreatePage} =  require('yylib-business');
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var URL = require('./Url');
var THIS;
/*账户信息*/
var UserData;
/*账户显示文件夹地址*/
var UserFilesPath;

var menuItem_modify="menuItem_modify";//行按钮修改
var menuItem_save="menuItem_save";//行按钮保存
var menuItem_delete="menuItem_delete";//行按钮删除

/*页面初始化*/
/*logo*/
var LOGO = YYClass.create({
    render: function() {
        return (
            <YYCol span={4}>
                <a href={URL.MAINPATH}>
                    <img style={{marginLeft:20,marginTop:20}} id="logo" src="./images/db_logo.png"/>
                </a>
            </YYCol>
        )}
});

/*菜单导航栏*/
var MENU = YYClass.create({
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
    render:function () {
       return(
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
       )
    }
});

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

/*顶部个人信息*/
var INFORMATION = YYClass.create({
   render:function () {
       return(
           <YYCol span={3}>
               <YYPage style={{marginTop:23,float:"left",marginLeft:40}}>
                   <YYDropdown overlay={Usermenu}>
                                <span className="ant-dropdown-link" style={{fontSize:20}} href="#">
                                  欢迎您 :  {UserData.name} <YYIcon type="down" />
                                </span>
                   </YYDropdown>
               </YYPage>
           </YYCol>
       )
   }
});

/*上传弹窗*/
var UPLOADPOPUPWINDOW = YYClass.create({
    onChange: function (info) {
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            YYMessage.success(`${info.file.name} 上传成功。`);
        } else if (info.file.status === 'error') {
            YYMessage.error(`${info.file.name} 上传失败。`);
        }
    },
    handleCancel(e) {
        console.log(e);
        var uploadPopupWindow = THIS.findUI('uploadPopupWindow');
        uploadPopupWindow.visible = false;
        THIS.refresh();
    },
    handleOk() {
        console.log('点击了确定');
        var uploadPopupWindow = THIS.findUI('uploadPopupWindow');
        uploadPopupWindow.visible = false;
        THIS.refresh();
    },
   render:function () {
       return <YYModal title="上传文件" visible={this.props.visible}
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
           </YYModal>
   }
});

//页面初始化
var EventHanlder = {
    "NewFolder":{
        "onClick":function () {
            console.log('新建文件夹');
        }
    },
    "uploadFile":{
        "onClick":function () {
            var uploadPopupWindow = THIS.findUI('uploadPopupWindow');
            uploadPopupWindow.visible = true;
            THIS.refresh();
        }
    },
    "P005023":{
        onViewWillMount:function(options){
            console.log('page onViewWillMount',options);
            THIS = this;
            /*获取登录数据*/
            UserData = this.getRouteParams();
            UserFilesPath = UserData.name;
            /*默认选中的树节点*/
            var leftMenu = THIS.findUI('leftMenu');
            leftMenu.selectedKeys = ['1515737467367_5842'];

            var queryParam = {
                userId: UserData._id,
                filePath:UserFilesPath,
            }
            ajax.postJSON(URL.GETUSERFILES,queryParam,function (result) {
                var listTable = THIS.findUI('listTable');
                listTable.dataSource = result;
                THIS.refresh();
            })
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

var MyParser = {};
MyParser.logo = LOGO;
MyParser.menu = MENU;
MyParser.information = INFORMATION;
MyParser.uploadPopupWindow = UPLOADPOPUPWINDOW;

var SimplePage = YYClass.create({
    render:function(){
        return <YYCreatePage {...this.props} appCode="A000776" pageCode="P005023" uiEvent={EventHanlder}
                             uiParser={MyParser}/>
    }
});
module.exports = SimplePage;