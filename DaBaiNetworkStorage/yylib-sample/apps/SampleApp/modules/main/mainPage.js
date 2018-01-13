var React = require('react');
var _ = require('lodash');
var {YYClass,YYCol,YYPage,YYMenu,YYMenuItem,YYDropdown,YYIcon,YYModal,YYUpload,YYButton,YYMessage,YYForm,YYFormItem,YYInput} = require('yylib-ui');
var {YYCreatePage} =  require('yylib-business');
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var URL = require('./Url');
require('./css/mainPage.css');
var THIS;
/*账户信息*/
var UserData;
/*账户显示文件夹地址*/
var UserFilesPath;
/*查询用户文件列表数据*/
var queryParam;

/*查询文件列表*/
function queryUserFileDateList(queryParam) {
    ajax.postJSON(URL.GETUSERFILES,queryParam,function (result) {
        var listTable = THIS.findUI('listTable');
        listTable.dataSource = result;
        THIS.refresh();
    })
}

/*新建文件夹检测唯一值名称*/
function newsFolderVerify(filesName) {
    var result = false;
    var listTable = THIS.findUI('listTable');
    var data = listTable.api.getDataSource();
    for (var i = 0; i < data.length; i++){
        if(data[i].type == '0' && data[i].fileName == filesName){
            result = true;
        }
    }
    return result;
}

/*保存新建文件夹*/
function saveNewFolder(queryParam) {
    ajax.postJSON(URL.NEWFOLDER,queryParam,function (result) {

    })
}

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
            queryUserFileDateList(queryParam);
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

/*新建文件夹弹窗*/
const formItemLayout = {
    labelCol: { span: 7 },
    wrapperCol: { span: 12 },
};
var NEWFOLDER = YYClass.create({
    handleCancel(e) {
        console.log(e);
        var NewFolder = THIS.findUI('NewFolder');
        NewFolder.visible = false;
        THIS.refresh();
    },
    handleOk() {
        console.log('点击了确定');
        var NewFolder = THIS.findUI('NewFolder');
        NewFolder.visible = false;
        THIS.refresh();
    },
    handleSubmit: function (e) {
        e.preventDefault();
        this.props.form.validateFields(function (errors, values) {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            console.log('Submit!!!');
            var queryParam = {

            };
            saveNewFolder(queryParam);
            console.log(values);
        });
    },
    userExists: function (rule, value, callback) {
        if (!value) {
            callback();
        } else {
            setTimeout(() => {
                /*命名规范*/
                if(value.indexOf("/")>-1 || value.indexOf("\\")>-1 || value.indexOf(":")>-1
                    || value.indexOf("\"")>-1 ||value.indexOf("*")>-1 || value.indexOf("?")>-1 || value.indexOf("<")>-1
                    || value.indexOf(">")>-1 || value.indexOf("|")>-1|| value.indexOf(".")>-1){
                    callback([new Error('文件名不能包含下列任何字符 ：/\\:*?\"<>|.')]);
                }else if (newsFolderVerify(value)) {
                    /*唯一值校验*/
                    callback([new Error('抱歉，该文件夹已存在。')]);
                } else {
                    callback();
                }
            }, 800);
        }
    },
    handleReset: function (e) {
        e.preventDefault();
        this.props.form.resetFields();
    },
    render:function () {
        var { getFieldProps, isFieldValidating, getFieldError } = this.props.form;
        const nameProps = getFieldProps('folderName', {
            rules: [
                { required: true, whitespace: true, message: '请填写文件夹名称'},
                { validator: this.userExists },
            ],
        });
        return <YYModal title="新建文件夹" visible={this.props.visible}
                        onOk={this.handleOk} onCancel={this.handleCancel}>
            <YYForm horizontal>
                <YYFormItem
                    {...formItemLayout}
                    label="文件夹名称"
                    hasFeedback
                    help={isFieldValidating('folderName') ? '校验中...' : (getFieldError('folderName') || []).join(', ')}>
                    <YYInput {...nameProps}/>
                </YYFormItem>
                <YYFormItem wrapperCol={{ span: 12, offset: 7 }}>
                    <YYButton type="primary" onClick={this.handleSubmit}>创建</YYButton>
                    &nbsp;&nbsp;&nbsp;
                    <YYButton type="ghost" onClick={this.handleReset}>重置</YYButton>
                </YYFormItem>
            </YYForm>
        </YYModal>
    }

});
NEWFOLDER = YYForm.create()(NEWFOLDER);

//页面初始化
var EventHanlder = {
    "download":{
        "onClick":function () {
            console.log('下载');
        }
    },
    "delete":{
        "onClick":function () {
            console.log('删除');
        }
    },
    "addFolder":{
        "onClick":function () {
            var NewFolder = THIS.findUI('NewFolder');
            NewFolder.visible = true;
            THIS.refresh();
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
            queryParam = {
                userId: UserData._id,
                filePath:UserFilesPath,
            }
            queryUserFileDateList(queryParam);
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
MyParser.NewFolder = NEWFOLDER;

var SimplePage = YYClass.create({
    render:function(){
        return <YYCreatePage {...this.props} appCode="A000776" pageCode="P005023" uiEvent={EventHanlder}
                             uiParser={MyParser}/>
    }
});
module.exports = SimplePage;